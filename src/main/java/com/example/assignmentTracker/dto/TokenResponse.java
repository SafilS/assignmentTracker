package com.example.assignmentTracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {
    public String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}
