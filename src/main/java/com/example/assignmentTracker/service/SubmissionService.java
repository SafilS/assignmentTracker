package com.example.assignmentTracker.service;

import com.example.assignmentTracker.model.Assignment;
import com.example.assignmentTracker.model.Role;
import com.example.assignmentTracker.model.Submission;
import com.example.assignmentTracker.model.User;
import com.example.assignmentTracker.repository.AssignmentRepository;
import com.example.assignmentTracker.repository.SubmissionRepository;
import com.example.assignmentTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AssignmentRepository assignmentRepo;

    public Submission submit(Submission submission, String studentUsername) {
        User student = userRepo.findByUserName(studentUsername);

        if (student.getRole() != Role.ROLE_STUDENT) {
            throw new AccessDeniedException("Only students can submit assignments");
        }

        Assignment assignment = assignmentRepo.findById(submission.getAssignment().getId())
                .orElseThrow(() -> new NoSuchElementException("Assignment not found"));

        boolean alreadySubmitted = submissionRepo.existsByStudentAndAssignmentId(student, assignment.getId());
        if (alreadySubmitted) {
            throw new IllegalStateException("You have already submitted this assignment.");
        }

        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setSubmittedAt(LocalDateTime.now());

        return submissionRepo.save(submission);
    }

    public List<Submission> getByStudent(String studentUsername) {
        User student = userRepo.findByUserName(studentUsername);

        return submissionRepo.findByStudent(student);
    }

    public List<Submission> getByAssignmentId(int assignmentId) {
        Assignment assignment = assignmentRepo.findById(assignmentId)
                .orElseThrow(() -> new NoSuchElementException("Assignment not found"));

        return submissionRepo.findByAssignment(assignment);
    }
}

