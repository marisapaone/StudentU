package edu.bu.studentapi.service;

import edu.bu.studentapi.Exceptions.CourseDoesNotExistException;
import edu.bu.studentapi.Exceptions.CourseExistsException;
import edu.bu.studentapi.dto.CreateCoursesRequest;
import edu.bu.studentapi.dto.Message;
import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.repository.CourseRepoInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepoInt courseRepoInt;

    @Autowired
    public CourseService(CourseRepoInt courseRepoInt) {
        this.courseRepoInt = courseRepoInt;
    }

    public Long createCourse(Course course) throws CourseExistsException {
        //Retrieves all classes and stores in coursees,
        //checks if there is a class that shares the name with the potential new class
        List<Course> courses = retrieveCourses();
        boolean courseExists = false;

        for (Course course1 : courses) {
            if (course1.getCourseName().equals(course.getCourseName())) {
                 courseExists = true;
            }
        }
        if (courseExists != true){
            courseRepoInt.save(course);
            System.out.println("Created class " + course.getCourseName());
            return course.getCourseID();
        }
        else{
            throw new CourseExistsException("Course already exists");
        }
    }

    public Course deleteCourse(Long courseID) throws CourseDoesNotExistException {
        Course course = courseRepoInt.getCourseByCourseID(courseID);

        if (course != null){
            courseRepoInt.delete(course);
            System.out.println("Deleted class " + course.getCourseName());
            return course;
        }
        else{
            throw new CourseDoesNotExistException("Course does not exist");
        }

    }

    public void createCourses(CreateCoursesRequest createCoursesRequest){
        for(Course course : createCoursesRequest.getClassList()){
            courseRepoInt.save(course);
            System.out.println("Created class " + course.getCourseName());
        }
    }

    public List<Course> retrieveCourses(){
        return courseRepoInt.findAll();
    }


    public Course searchForCourse(String className){
        List<Course> existingClasses = retrieveCourses();
        boolean classExists = false;

        Course course = null;

        for (Course class1 : existingClasses) {
            if (class1.getCourseName().equals(className)) {
                classExists = true;
                System.out.println("Class found: " + class1.toString());
                return class1;
            }
        }
        if (classExists == false){
            System.out.println("No classes match this class name");
        }
        return course;
    }

}
