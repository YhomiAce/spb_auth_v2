package com.ace.jwtAuth.dto;

import com.ace.jwtAuth.entities.User;
import lombok.Data;

@Data
public class LoginResponse {
    private User user;
    private String accessToken;
    private String refreshToken;
}
