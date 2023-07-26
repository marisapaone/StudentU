package edu.bu.studentapi.repository;

import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.model.Gpa;
import edu.bu.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpaRepoInt extends JpaRepository <Student, Long> {

    Gpa getGpaByStudentID(Long id);
}
