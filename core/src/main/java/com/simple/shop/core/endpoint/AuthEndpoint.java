package com.simple.shop.core.endpoint;

import com.simple.shop.core.config.AppConfig;
import com.simple.shop.core.domain.api.Response;
import com.simple.shop.core.domain.auth.SignInRequest;
import com.simple.shop.core.domain.auth.SignUpRequest;
import com.simple.shop.core.domain.User;
import com.simple.shop.core.security.JwtUtils;
import com.simple.shop.core.service.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

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
        Optional<User> user = usersService.getByLogin(request.getLogin());
        if (user.isPresent()) {
            return Response.ok(jwt.generate(user.get().getLogin()));
        } else {
            throw new UsernameNotFoundException("User " + request.getLogin() + " not found");
        }
    }

    private UsernamePasswordAuthenticationToken getCredentials(SignInRequest request) {
        return new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
    }
}
