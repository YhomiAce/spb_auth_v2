package com.ace.jwtAuth.dto;


public record SignupRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
