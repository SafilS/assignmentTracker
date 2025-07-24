package com.example.assignmentTracker.controller;

import com.example.assignmentTracker.dto.LoginDTO;
import com.example.assignmentTracker.dto.RegisterDTO;
import com.example.assignmentTracker.dto.TokenResponse;
import com.example.assignmentTracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginDTO dto) {
        return authService.login(dto);
    }
}

