package edu.bu.studentapi.repository;

import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.model.CourseRegistration;
import edu.bu.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistrationRepoInt extends JpaRepository<CourseRegistration, Long> {
  CourseRegistration findByStudentAndCourse(Student student, Course course);

}

