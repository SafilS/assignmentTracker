package com.example.assignmentTracker.service;

import com.example.assignmentTracker.model.Assignment;
import com.example.assignmentTracker.model.Role;
import com.example.assignmentTracker.model.User;
import com.example.assignmentTracker.repository.AssignmentRepository;
import com.example.assignmentTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepo;

    @Autowired
    private UserRepository userRepo;

    // Create a new assignment (TEACHER only)
    public Assignment create(Assignment assignment, String teacherUsername) {
        User teacher = userRepo.findByUserName(teacherUsername);

        if (teacher==null ||teacher.getRole() != Role.TEACHER) {
            throw new AccessDeniedException("Only teachers can create assignments");
        }

        assignment.setTeacher(teacher);
        return assignmentRepo.save(assignment);
    }

    // Get all assignments (both roles)
    public List<Assignment> getAll() {
        return assignmentRepo.findAll();
    }

    // Optional: Get assignments created by current teacher
    public List<Assignment> getByTeacher(String teacherUsername) {
        User teacher = userRepo.findByUserName(teacherUsername);
        return assignmentRepo.findByTeacher(teacher);
    }
}

