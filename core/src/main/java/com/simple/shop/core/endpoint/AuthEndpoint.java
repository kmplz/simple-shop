package com.simple.shop.core.endpoint;

import com.simple.shop.core.config.AppConfig;
import com.simple.shop.core.domain.api.Response;
import com.simple.shop.core.domain.auth.SignInRequest;
import com.simple.shop.core.domain.auth.SignUpRequest;
import com.simple.shop.core.exception.ServiceException;
import com.simple.shop.core.security.JwtUtils;
import com.simple.shop.core.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthEndpoint {

    private final AuthenticationManager manager;
    private final JwtUtils jwt;
    private final UsersService usersService;

    @PostMapping("/sign-up")
    public Response signUp(@RequestBody @Valid SignUpRequest request) {
        usersService.signUp(request);
        return Response.ok(jwt.generate(request.getLogin()));
    }

    @PostMapping("/sign-in")
    public Response signIn(@RequestBody @Valid SignInRequest request) {
        Authentication authentication = manager.authenticate(getCredentials(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String login = request.getLogin();
        return usersService.getByLogin(request.getLogin())
                .map(user -> Response.ok(jwt.generate(login)))
                .orElseThrow(() -> new ServiceException.LoginNotFoundException(login));
    }

    @GetMapping("/me")
    public Response me() {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usersService.getByLogin(login)
                .map(Response::ok)
                .orElseThrow(() -> new ServiceException.LoginNotFoundException(login));
    }

    @GetMapping("/roles")
    public Response getRoles() {
        return Response.ok(usersService.getRoles());
    }

    private UsernamePasswordAuthenticationToken getCredentials(SignInRequest request) {
        return new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
    }
}
