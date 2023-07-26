package edu.bu.studentapi.controller;

import edu.bu.studentapi.dto.Message;
import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.model.Grade;
import edu.bu.studentapi.model.Student;
import edu.bu.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        try {
            studentService.saveStudent(student);
            return ResponseEntity.ok().body("Student: " + student.getLastName() + ", " + student.getFirstName() + " was created");
        }
        catch (Exception e){
            Message message = new Message (HttpStatus.BAD_REQUEST,  "Student Already Exists" );
            return new ResponseEntity<>(message.getMessageContent(), message.getHttpStatus());
        }
    }

    @GetMapping("/grades")
    public ResponseEntity<List<Grade>> viewGrades(@RequestParam("studentID") Long studentID,
                                                  @RequestParam(value = "returnAllGrades", defaultValue = "false") boolean returnAllGrades,
                                                  @RequestParam(value = "courseID", required = false) Long courseID)
    {
        List<Grade> grades = studentService.getGrades(studentID, returnAllGrades, courseID);
        return ResponseEntity.ok().body(grades);
    }


    @PostMapping("/calculate")
    public ResponseEntity<String> calculateGpa(@RequestParam("studentID") Long studentID) throws Exception {
        try {
            Double gpa = studentService.calculateGpa(studentID);
            return ResponseEntity.ok().body("GPA calculated: " + gpa);
        }
        catch (Exception e){
            Message message = new Message (HttpStatus.NOT_FOUND,  "These Courses Don't Exist" );
            return new ResponseEntity<>(message.getMessageContent(), message.getHttpStatus());
        }
    }




}
