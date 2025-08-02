package com.example.assignmentTracker.repository;

import com.example.assignmentTracker.model.Assignment;
import com.example.assignmentTracker.model.Submission;
import com.example.assignmentTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findByStudent(User student);

    List<Submission> findByAssignment(Assignment assignment);

    boolean existsByStudentAndAssignmentId(User student, Integer id);
}
