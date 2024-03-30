package com.ace.jwtAuth.controller;

import com.ace.jwtAuth.dto.LoginRequest;
import com.ace.jwtAuth.dto.LoginResponse;
import com.ace.jwtAuth.dto.RefreshTokenRequest;
import com.ace.jwtAuth.dto.SignupRequest;
import com.ace.jwtAuth.entities.User;
import com.ace.jwtAuth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest request){
        return new ResponseEntity<>(authService.signup(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok().body(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}
