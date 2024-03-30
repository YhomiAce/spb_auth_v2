package com.ace.jwtAuth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    @GetMapping
    public ResponseEntity<Map<String, Object>> sayHello(Authentication authentication, Principal principal) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello Admin");
        response.put("user", authentication.getPrincipal());
        response.put("userDetail", principal.getName());
        return ResponseEntity.ok(response);
    }
}
