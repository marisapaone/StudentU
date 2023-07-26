package edu.bu.studentapi.repository;

import edu.bu.studentapi.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepoInt extends JpaRepository<Grade, Long> {
    public Grade getGradeBystudentID(Long id);
    public List<Grade> getAllGradesByStudentID(Long id);
}
