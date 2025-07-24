package com.example.assignmentTracker.dto;

import com.example.assignmentTracker.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    public String userName;
    public String password;
    public Role role;
}
