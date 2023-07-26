package edu.bu.studentapi.service;

import edu.bu.studentapi.Exceptions.CourseDoesNotExistException;
import edu.bu.studentapi.Exceptions.CourseExistsException;
import edu.bu.studentapi.dto.CreateCoursesRequest;
import edu.bu.studentapi.model.Instructor;
import edu.bu.studentapi.model.Period;
import edu.bu.studentapi.model.Student;
import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.repository.CourseRepoInt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepoInt mockCourseRepoInt;


    @Test
    void createCourseTest() throws Exception {
        //creates our fake data
        Course course = createFakeCourse(2L);
        //sets the behavior of the interface (we don't want to actually call off to the database)
        when(mockCourseRepoInt.save(Mockito.any())).thenReturn(course);
        //calls the method we are testing
        courseService.createCourse(course);
        //seeing if our line was hit that would have created the data
        verify(mockCourseRepoInt, times(1)).save(Mockito.any());
    }

    @Test
    void courseExistsScenario() throws Exception {
        //creating fake data
        Course course = createFakeCourse(2L);
        Course course1 = createFakeCourse(3L);
        Course course2 = new Course(5L, new Instructor(2L, "Mr. Fox"),
                new Period(3L, "third" ), null, "Chemistry",
                "7:00AM-8:00AM", 3.0, 6.0);


        //setting findAll in retrieveCourses to a list of course and course1
        when(mockCourseRepoInt.findAll()).thenReturn(Arrays.asList(course,course1));
        //course already exists in the list so a courseExistsException is thrown
        CourseExistsException ex = assertThrows(CourseExistsException.class,
                ()-> courseService.createCourse(course), "Course already exists");
        //course2 doesn't exist in the list, so this will hit the branch when the courseName
        //doesn't equal any of the existing courseNames.
        courseService.createCourse(course2);


    }



    @Test
    void createCoursesTest() {
        //creates our fake data
        Course course = createFakeCourse(2L);
        Course course1 = createFakeCourse(3L);
        CreateCoursesRequest createCoursesRequest = new CreateCoursesRequest();
        createCoursesRequest.setClassList(Arrays.asList(course, course1));
        //sets the behavior of the interface (we don't want to actually call off to the database)
        when(mockCourseRepoInt.save(Mockito.any())).thenReturn(course);
        //calls the method we are testing
        courseService.createCourses(createCoursesRequest);
        //seeing if our line was hit that would have created the data
        verify(mockCourseRepoInt, times(2)).save(Mockito.any());
    }


    @Test
    void deleteCourseTest() throws Exception {
        //creating a course to then delete it
        Course course = createFakeCourse(2L);
        Course course1 = createFakeCourse(3L);
        Course course2 = new Course(5L, new Instructor(2L, "Mr. Fox"),
                new Period(3L, "third" ), null, "Chemistry",
                "7:00AM-8:00AM", 3.0, 6.0);

        //setting findAll in retrieveCourses to a list of course and course1
       // when(mockCourseRepoInt.findAll()).thenReturn(Arrays.asList(course,course1));
        when(mockCourseRepoInt.getCourseByCourseID(Mockito.any())).thenReturn(course);
        //deleting course from the above arraylist
        courseService.deleteCourse(course.getCourseID());

        //verifying that something was deleted
        verify(mockCourseRepoInt, times(1)).delete(Mockito.any());
        //course2 doesn't exist in the list we made above so trying to delete it will throw a CourseDoesNotExistException
        //CourseDoesNotExistException ex = assertThrows(CourseDoesNotExistException.class,
        //        ()-> courseService.deleteCourse(course2.getCourseID()), "Course does not exist");

    }

    @Test
    void searchForCourseTest() throws Exception {
        //creating fake data
        Course course = createFakeCourse(2L);
        //setting findAll in retrieveCourses to a list of just course
        when(mockCourseRepoInt.findAll()).thenReturn(Arrays.asList(course));
        //test searching for course (exists in the list we just made)
        courseService.searchForCourse(course.getCourseName());
        //test searching for a course that isn't there
        courseService.searchForCourse("Lunch");

    }


    private Course createFakeCourse(Long classId){
        ArrayList<Student> students = createFakeStudentList();
        Instructor instructor = new Instructor(2L ,"Bob");
        Period period = new Period(1L, "First");
        //creates a student class to be used for testing
        Course course1 = new Course(
                classId, instructor,
                period,students, "Biology", "8:00", 4.00, 80.00
        );
        return course1;
    }

    private ArrayList<Student> createFakeStudentList(){
        //creates a student to use for testing
        Student student = new Student(
                1L, "Bob" , "Jim",
                "test@gmail.com","11-20-2000",
                "123-421-234"
        );
        Student student2 = new Student(
                1L, "Bob" , "Jim",
                "test@gmail.com","11-20-2000",
                "123-421-234"
        );
        ArrayList<Student> studentArrayList = new ArrayList<>();
        studentArrayList.add(student2);
        studentArrayList.add(student);
        return studentArrayList;
    }


}