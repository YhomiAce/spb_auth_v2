package com.ace.jwtAuth.services;

import com.ace.jwtAuth.dto.LoginRequest;
import com.ace.jwtAuth.dto.LoginResponse;
import com.ace.jwtAuth.dto.RefreshTokenRequest;
import com.ace.jwtAuth.dto.SignupRequest;
import com.ace.jwtAuth.entities.User;

public interface AuthService {
    User signup(SignupRequest request);

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(RefreshTokenRequest request);
}
