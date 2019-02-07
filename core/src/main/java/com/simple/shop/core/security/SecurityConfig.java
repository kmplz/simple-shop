package com.simple.shop.core.security;

import com.simple.shop.core.config.AppConfig;
import com.simple.shop.core.domain.User;
import com.simple.shop.core.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UserDetailsService userDetailsService = getApplicationContext().getBean(UserDetailsService.class);
        OncePerRequestFilter filter = jwtFilter(userDetailsService);
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public UserDetailsService userDetailsService(ApplicationContext ctx) {
        UsersService service = ctx.getBean(UsersService.class);
        return username -> {
            Optional<User> user = service.getByLogin(username);
            if (user.isPresent()) {
                return new UserDetailsImpl(user.get());
            } else {
                throw new UsernameNotFoundException("User " + user + " not found");
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtils jwtUtils() {
        AppConfig config = getApplicationContext().getBean(AppConfig.class);
        return new JwtUtils(config.getJwt().getSecret());
    }

    private OncePerRequestFilter jwtFilter(UserDetailsService userDetailsService) {
        JwtUtils jwt = getApplicationContext().getBean(JwtUtils.class);
        return new OncePerRequestFilter() {
            @Override
            public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                String type = "Bearer";
                String token = request.getHeader("Authorization");
                if (token == null || StringUtils.isEmpty(token.trim()) || !token.startsWith(type)) {
                    chain.doFilter(request, response);
                } else {
                    token = token.replaceAll(type, "").trim();
                    Optional<String> user = jwt.getUsername(token);
                    if (jwt.validate(token) && user.isPresent()) {
                        try {
                            UserDetails userDetails = userDetailsService.loadUserByUsername(user.get());
                            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                              userDetails.getUsername(),null, userDetails.getAuthorities()
                            );
                            SecurityContextHolder.getContext().setAuthentication(auth);
                        } catch (Exception e) {
                            chain.doFilter(request, response);
                            return;
                        }
                    }
                    chain.doFilter(request, response);
                }
            }
        };
    }
}
