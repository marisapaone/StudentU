package edu.bu.studentapi.repository;

import edu.bu.studentapi.model.Grade;
import edu.bu.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepoInt extends JpaRepository<Student, Long> {
    public Student getStudentBystudentID(Long id);
}
