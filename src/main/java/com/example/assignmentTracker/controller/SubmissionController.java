package com.example.assignmentTracker.controller;

import com.example.assignmentTracker.model.Submission;
import com.example.assignmentTracker.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;


    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping
    public Submission submit(@RequestBody Submission dto, Principal principal) {
        return submissionService.submit(dto, principal.getName());
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/student")
    public List<Submission> getMySubmissions(Principal principal) {
        return submissionService.getByStudent(principal.getName());
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/assignment/{id}")
    public List<Submission> getAssignmentById(@PathVariable int id){
        return submissionService.getByAssignmentId(id);
    }

}

