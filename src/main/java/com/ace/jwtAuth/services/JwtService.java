package com.ace.jwtAuth.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Objects;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
