package edu.bu.studentapi.service;


import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.model.Gpa;
import edu.bu.studentapi.model.Student;
import edu.bu.studentapi.repository.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

  @Mock
  private StudentRepoInt studentRepoInt;

  @Mock
  private CourseRepoInt courseRepoInt;

  @InjectMocks
  private StudentService studentService;

  private Student student;
  private Course course;

  @Mock
  private GpaRepoInt mockGpaRepoInt;


  @BeforeEach
  public void setup() {
    student = new Student();
    student.setStudentID(1L);

    course = new Course();
    course.setCourseID(1L);
    course.setStudentList(new ArrayList<>());
  }



  @Test
  public double testCalculateGpa() {
    Course course1 = Mockito.mock(Course.class);
    Mockito.when(course1.getCreditHours()).thenReturn(4.0);
    Mockito.when(course1.getGradePoints()).thenReturn(3.2);

    Course course2 = Mockito.mock(Course.class);
    Mockito.when(course2.getCreditHours()).thenReturn(3.0);
    Mockito.when(course2.getGradePoints()).thenReturn(2.2);

    Course course3 = Mockito.mock(Course.class);
    Mockito.when(course3.getCreditHours()).thenReturn(3.0);
    Mockito.when(course3.getGradePoints()).thenReturn(3.9);
    verify(mockGpaRepoInt, times(3)).save(Mockito.any());
    // create course list
    List<Course> courses = Arrays.asList(course1, course2, course3);


    // Create an instance of the Gpa
    Gpa gpa = new Gpa();

    // Call the method under test
    double Gpa = studentService.calculateGpa(student.getStudentID());

    // Assert the result
    assertEquals(3.0,  0.01);


    return Gpa;
  }


  private void assertEquals(double v, double gpa) {
  }


}