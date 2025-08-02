package com.example.assignmentTracker.controller;

import com.example.assignmentTracker.model.Assignment;
import com.example.assignmentTracker.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired private AssignmentService assignmentService;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping
    public Assignment create(@RequestBody Assignment dto, Principal principal) {
        return assignmentService.create(dto, principal.getName());
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_STUDENT')")
    @GetMapping
    public List<Assignment> getAll() {
        return assignmentService.getAll();
    }
}

