package com.simple.shop.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class JwtUtils {

    private static final String CLAIM_KEY_USERNAME = "username";
    private static final String CLAIM_KEY_CREATED = "created";

    private final String secret;

    public JwtUtils(String secret) {
        this.secret = secret;
    }

    public String generate(String login) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, login);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generate(claims);
    }

    public Boolean validate(String token) {
        return !isTokenExpired(token);
    }

    public Optional<String> getUsername(String token) {
        return getClaimValue(token, CLAIM_KEY_USERNAME, String.class);
    }

    private <T> Optional<T> getClaimValue(String token, String field, Class<T> clazz) {
        return getClaimsFromToken(token).map(claims -> claims.get(field, clazz));
    }

    private Optional<Claims> getClaimsFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.ofNullable(claims);
        } catch (Exception e) {
            log.error("Error occurred while parsing JWT: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getClaimValue(token, Claims.EXPIRATION, Date.class).orElse(null);
        return expiration != null && expiration.before(new Date());
    }

    private String generate(Map<String, Object> claims) {
        Date expire = Date.from(LocalDateTime.now().plusDays(7L).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}