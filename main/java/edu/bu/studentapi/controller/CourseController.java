package edu.bu.studentapi.controller;

import edu.bu.studentapi.dto.CreateCoursesRequest;
import edu.bu.studentapi.dto.Message;
import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.service.CourseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create/course")
    public ResponseEntity<String> createCourse(@RequestBody Course course) throws Exception {
        try{
            courseService.createCourse(course);
            return ResponseEntity.ok().body("Your course " + course.getCourseName() + ", was created");
        }
        catch(Exception e){
            Message message = new Message (HttpStatus.BAD_REQUEST, "Course Already Exists" );
            return new ResponseEntity<>(message.getMessageContent(), message.getHttpStatus());
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> retrieveAllCourses(){
        return ResponseEntity.ok().body(courseService.retrieveCourses());
    }

    @DeleteMapping("/delete/course")
    public ResponseEntity<String> deleteCourse(@RequestParam Long courseID) throws Exception{
        try{
            Course course = courseService.deleteCourse(courseID);
            return ResponseEntity.ok().body("Your course : " + course.getCourseName() + ", was deleted.");
        }
        catch (Exception e){
            Message message = new Message (HttpStatus.NOT_FOUND,  "Course Doesn't Exist" );
            return new ResponseEntity<>(message.getMessageContent(), message.getHttpStatus());
        }
    }

    @GetMapping("/search/course")
    public ResponseEntity<String> searchForCourse(@RequestBody String courseName){
        try {
            Course course1 = courseService.searchForCourse(courseName);
            return ResponseEntity.ok().body("Your course: " + course1.getCourseName() + ", exists in the system.");
        }
        catch (Exception e){
            Message message = new Message (HttpStatus.NOT_FOUND,  "Course Doesn't Exist" );
            return new ResponseEntity<>(message.getMessageContent(), message.getHttpStatus());
        }
    }


}
