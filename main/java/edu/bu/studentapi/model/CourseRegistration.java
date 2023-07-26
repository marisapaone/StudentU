package edu.bu.studentapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourseRegistration {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id", referencedColumnName = "studentID", nullable = false)
  private Student student;

  @ManyToOne
  @JoinColumn(name = "course_id", referencedColumnName = "courseID", nullable = false)
  private Course course;

}


