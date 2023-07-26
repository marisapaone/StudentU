package edu.bu.studentapi.repository;

import edu.bu.studentapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepoInt extends JpaRepository<Course, Long> {
  public Course getCourseByCourseID(Long id);
}

