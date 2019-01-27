package com.simple.shop.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("app")
public class AppConfig {

    private JwtConfig jwt;

    @Data
    public static class JwtConfig {
        private String secret;
    }
}
