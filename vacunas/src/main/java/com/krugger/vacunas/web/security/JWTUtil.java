package com.krugger.vacunas.web.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        userDetails.getAuthorities().forEach(e -> {
            claims.put("role", e.getAuthority());
        });
        claims.put("sub", userDetails.getUsername());
        claims.put("iat", new Date());
        claims.put("exp", new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10));

        return Jwts.builder()
                .setClaims(claims)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiresAt().before(new Date());
    }

    private DecodedJWT getClaims(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        DecodedJWT jwt = JWT.decode(token);
        return jwt;
    }
}
