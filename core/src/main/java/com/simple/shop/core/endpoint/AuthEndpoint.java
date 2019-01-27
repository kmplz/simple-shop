package com.simple.shop.core.endpoint;

import com.simple.shop.core.config.AppConfig;
import com.simple.shop.core.domain.api.Response;
import com.simple.shop.core.domain.auth.SignInRequest;
import com.simple.shop.core.domain.auth.SignUpRequest;
import com.simple.shop.core.exception.ServiceException;
import com.simple.shop.core.security.JwtUtils;
import com.simple.shop.core.service.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthEndpoint {

    private final AuthenticationManager manager;
    private final JwtUtils jwt;
    private final UsersService usersService;

    public AuthEndpoint(AppConfig config, AuthenticationManager authenticationManager, UsersService usersService) {
        this.jwt = new JwtUtils(config.getJwt().getSecret());
        this.manager = authenticationManager;
        this.usersService = usersService;
    }

    @PostMapping("/sign-up")
    public Response signUp(@RequestBody @Valid SignUpRequest request) {
        return Response.from(() -> usersService.signUp(request));
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

    private UsernamePasswordAuthenticationToken getCredentials(SignInRequest request) {
        return new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
    }
}
