package edu.bu.studentapi.service;

import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.model.CourseRegistration;
import edu.bu.studentapi.model.Grade;
import edu.bu.studentapi.model.Student;
import edu.bu.studentapi.repository.CourseRegistrationRepoInt;
import edu.bu.studentapi.repository.CourseRepoInt;
import edu.bu.studentapi.repository.StudentRepoInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CourseManagementService {

  private StudentRepoInt studentRepoInt;
  private CourseRepoInt courseRepoInt;

  private CourseRegistrationRepoInt courseRegistrationRepoInt;


  @Autowired
  public CourseManagementService(StudentRepoInt studentRepoInt, CourseRepoInt courseRepoInt, CourseRegistrationRepoInt courseRegistrationRepoInt) {
    this.studentRepoInt = studentRepoInt;
    this.courseRepoInt = courseRepoInt;
    this.courseRegistrationRepoInt = courseRegistrationRepoInt;
  }

  public void registerClass(Long studentID, Long classID) throws Exception {
    Student student = retrieveStudent(studentID);
    Course course = retrieveClass(classID);

    if (isStudentRegistered(student, course)) {
      throw new Exception("Student already registered for this class");
    }

    CourseRegistration courseRegistration = new CourseRegistration();
    List<Student> list = course.getStudentList();
    list.add(student);
    course.setStudentList(list);
    courseRegistration.setStudent(student);
    courseRegistration.setCourse(course);
    courseRegistrationRepoInt.save(courseRegistration);
  }


    public void dropClass (Long studentID, Long classID) throws Exception {
      Student student = retrieveStudent(studentID);
      Course course = retrieveClass(classID);

      CourseRegistration courseRegistration = courseRegistrationRepoInt.findByStudentAndCourse(student, course);
      if (courseRegistration == null) {
        throw new Exception("Student is not registered for this class");
      }
      courseRegistrationRepoInt.delete(courseRegistration);
    }


    private Course retrieveClass (Long classID) throws Exception {
      Course course = courseRepoInt.getCourseByCourseID(classID);
      if (course == null) {
        throw new Exception("Class does not exist");
      }
      return course;
    }

  private Student retrieveStudent(Long studentID) throws Exception {
    Student student = studentRepoInt.getStudentBystudentID(studentID);
    if(student == null) {
      throw new Exception("Student does not exist");
    }
    return student;
  }

    private boolean isStudentRegistered (Student student, Course course){
      return courseRegistrationRepoInt.findByStudentAndCourse(student, course) != null;
    }



}

