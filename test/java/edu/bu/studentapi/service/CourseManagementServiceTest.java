package edu.bu.studentapi.service;

import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.model.CourseRegistration;
import edu.bu.studentapi.model.Student;
import edu.bu.studentapi.repository.CourseRegistrationRepoInt;
import edu.bu.studentapi.repository.CourseRepoInt;
import edu.bu.studentapi.repository.StudentRepoInt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseManagementServiceTest {

  @Mock
  private StudentRepoInt studentRepoInt;

  @Mock
  private CourseRepoInt courseRepoInt;

  @Mock
  private CourseRegistrationRepoInt courseRegistrationRepoInt;

  @InjectMocks
  private CourseManagementService service;

  private Student student;
  private Course course;
  private CourseRegistration courseRegistration;

  @BeforeEach
  public void setUp() {
    student = new Student();
    course = new Course();
    courseRegistration = new CourseRegistration();
    courseRegistration.setStudent(student);
    courseRegistration.setCourse(course);
  }

  @Test
  public void testRegisterClass() throws Exception {

    List<Student> list = new ArrayList<>();
    list.add(student);
    course.setStudentList(list);
    when(studentRepoInt.getStudentBystudentID(any())).thenReturn(student);
    when(courseRepoInt.getCourseByCourseID(any())).thenReturn(course);
    when(courseRegistrationRepoInt.findByStudentAndCourse(any(), any())).thenReturn(null);


    service.registerClass(1L, 1L);

    verify(courseRegistrationRepoInt, times(1)).save(any(CourseRegistration.class));
  }

  @Test
  public void testDropClass() throws Exception {
    when(studentRepoInt.getStudentBystudentID(any())).thenReturn(student);
    when(courseRepoInt.getCourseByCourseID(any())).thenReturn(course);
    when(courseRegistrationRepoInt.findByStudentAndCourse(any(), any())).thenReturn(courseRegistration);

    service.dropClass(1L, 1L);

    verify(courseRegistrationRepoInt, times(1)).delete(courseRegistration);
  }

  @Test
  public void testDropClass_NotRegistered() throws Exception {

    List<Student> list = new ArrayList<>();
    list.add(student);
    course.setStudentList(list);
    when(studentRepoInt.getStudentBystudentID(any())).thenReturn(student);
    when(courseRepoInt.getCourseByCourseID(any())).thenReturn(course);
    when(courseRegistrationRepoInt.findByStudentAndCourse(any(), any())).thenReturn(null);
    service.registerClass(1L, 1L);

    verify(courseRegistrationRepoInt, times(1)).save(any(CourseRegistration.class));
    try {
      service.dropClass(1L, 1L);
    } catch (Exception e) {
      verify(courseRegistrationRepoInt, never()).delete(any(CourseRegistration.class));
    }
  }

  @Test
  public void testRegisterClass_AlreadyRegistered() throws Exception {
    when(studentRepoInt.getStudentBystudentID(any())).thenReturn(student);
    when(courseRepoInt.getCourseByCourseID(any())).thenReturn(course);
    when(courseRegistrationRepoInt.findByStudentAndCourse(any(), any())).thenReturn(courseRegistration);

    try {
      service.registerClass(1L, 1L);
    } catch (Exception e) {
      verify(courseRegistrationRepoInt, never()).save(any(CourseRegistration.class));
    }
  }

}
