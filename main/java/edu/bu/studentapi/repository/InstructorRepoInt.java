package edu.bu.studentapi.repository;

import edu.bu.studentapi.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepoInt extends JpaRepository<Instructor, Long> {
}
