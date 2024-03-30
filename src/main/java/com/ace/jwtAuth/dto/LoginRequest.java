package com.ace.jwtAuth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
