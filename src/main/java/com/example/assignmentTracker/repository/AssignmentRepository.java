package com.example.assignmentTracker.repository;

import com.example.assignmentTracker.model.Assignment;
import com.example.assignmentTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findByTeacher(User teacher);
}
