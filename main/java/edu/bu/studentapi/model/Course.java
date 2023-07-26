package edu.bu.studentapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull
  private Long courseID;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(referencedColumnName = "instructorID")
  @NotNull
  private Instructor instructor;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(referencedColumnName = "periodID")
  @NotNull
  private Period period;
  @ManyToMany
  private List<Student> studentList;
  private String courseName;
  private String times;

  private double creditHours;
  private double gradePoints;

}


