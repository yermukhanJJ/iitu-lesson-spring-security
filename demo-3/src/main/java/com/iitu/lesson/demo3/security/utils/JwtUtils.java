package com.iitu.lesson.demo3.security.utils;

import com.iitu.lesson.demo3.config.properties.JwtProperties;
import com.iitu.lesson.demo3.domain.model.User;
import com.iitu.lesson.demo3.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProperties jwtProperties;

    public String generateJwtToken(@NonNull Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return getToken(userPrincipal.getUser());
    }

    public String getUserNameFromJwtToken(@NonNull String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public String generateJwtTokenFromUserDetails(@NonNull User user) {
        return getToken(user);
    }

    private String getToken(@NonNull User user) {
        return Jwts.builder().setSubject((user.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtProperties.getTtl()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public boolean validateJwtToken(@NonNull String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
