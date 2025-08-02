package com.example.assignmentTracker.service;

import com.example.assignmentTracker.configuration.JwtUtil;
import com.example.assignmentTracker.dto.LoginDTO;
import com.example.assignmentTracker.dto.RegisterDTO;
import com.example.assignmentTracker.dto.TokenResponse;
import com.example.assignmentTracker.model.User;
import com.example.assignmentTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterDTO dto) {
        User user = new User();
        user.setUserName(dto.userName);
        user.setPassword(passwordEncoder.encode(dto.password));
        user.setRole(dto.role);

        System.out.println(dto.userName+" "+dto.password+" "+dto.role);
        userRepo.save(user);
        return "User Registered";
    }

    public TokenResponse login(LoginDTO dto) {
        User user = userRepo.findByUserName(dto.userName);
        if (!passwordEncoder.matches(dto.password, user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);
        return new TokenResponse(token);
    }
}

