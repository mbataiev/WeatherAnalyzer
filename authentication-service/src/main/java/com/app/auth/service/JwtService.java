package com.app.auth.service;

import com.app.auth.exception.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    public void validateToken(final String token) {
        try {
            log.debug("Validating token -> {}", token);
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        } catch (Exception e) {
            log.error("Error while token validation with token -> {}", token);
            throw new JwtException(String.format("Token validation error with token -> %s", token), e);
        }
    }


    public String generateToken(String userName, String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName, email);
    }

    private String createToken(Map<String, Object> claims, String userName, String email) {
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .claim("email", email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        log.debug("Generated token -> {}", token);
        return token;
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
