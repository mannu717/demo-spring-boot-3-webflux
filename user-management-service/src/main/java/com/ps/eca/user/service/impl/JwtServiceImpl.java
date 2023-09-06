package com.ps.eca.user.service.impl;

import com.ps.eca.user.config.JwtConfig;
import com.ps.eca.user.dto.TokenDto;
import com.ps.eca.user.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtConfig jwtConfig;

    public JwtServiceImpl(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public TokenDto generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        Date expiration = new Date(System.currentTimeMillis() + jwtConfig.getExpiration());
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, getSecretKey(jwtConfig.getSecret())).compact();

        return TokenDto.builder()
                .token(token)
                .expiration(expiration)
                .build();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(getSecretKey(jwtConfig.getSecret())).parseClaimsJws(token);
            // Token is valid
            return Boolean.TRUE;
        } catch (Exception e) {
            // Token is invalid
            return Boolean.FALSE;
        }
    }

    private Key getSecretKey(String key) {
        return Keys.hmacShaKeyFor(key.getBytes());
    }
}
