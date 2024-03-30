package com.ace.jwtAuth.services.impl;

import com.ace.jwtAuth.dto.LoginRequest;
import com.ace.jwtAuth.dto.LoginResponse;
import com.ace.jwtAuth.dto.RefreshTokenRequest;
import com.ace.jwtAuth.dto.SignupRequest;
import com.ace.jwtAuth.entities.Role;
import com.ace.jwtAuth.entities.User;
import com.ace.jwtAuth.repository.UserRepository;
import com.ace.jwtAuth.services.AuthService;
import com.ace.jwtAuth.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User signup(SignupRequest request) {
        Optional<User> userExist = getUser(request.email());

        if (userExist.isPresent()) {
            throw new RuntimeException("User Already registered");
        }
        User user = new User();
        user.setEmail(request.email());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    private LoginResponse issueToken(User user) {
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        LoginResponse response = new LoginResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setUser(user);
        return response;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = getUser(request.email()).orElseThrow(() -> new IllegalArgumentException("Invalid Email Or Password"));
        return issueToken(user);
    }

    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String userEmail = jwtService.extractUsername(request.token());
        User user = getUser(userEmail).orElseThrow(() -> new IllegalArgumentException("Invalid Email Or Password"));
        if (!jwtService.isTokenValid(request.token(), user)) {
            throw new RuntimeException("Unauthorized");
        }
        return issueToken(user);
    }
}
