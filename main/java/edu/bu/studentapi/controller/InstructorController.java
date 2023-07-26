package edu.bu.studentapi.controller;

import edu.bu.studentapi.dto.Message;
import edu.bu.studentapi.model.Grade;
import edu.bu.studentapi.model.Instructor;
import edu.bu.studentapi.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instructor")
public class InstructorController {

    private InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/instructor")
    public ResponseEntity<String> createInstructor(@RequestBody Instructor instructor){
        try {
            instructorService.saveInstructor(instructor);
            return ResponseEntity.ok().body("Instructor: " + instructor.getInstructorName() + "was created.");
        }
        catch (Exception e){
            Message message = new Message (HttpStatus.BAD_REQUEST,  "Instructor Already Exists" );
            return new ResponseEntity<>(message.getMessageContent(), message.getHttpStatus());
        }
    }

    @GetMapping("/grades")
    public ResponseEntity<List<Grade>> viewGrades(@RequestParam("studentID") Long studentID,
                                                  @RequestParam(value = "returnAllGrades", defaultValue = "false") boolean returnAllGrades,
                                                  @RequestParam(value = "courseID", required = false) Long courseID)
    {
        List<Grade> grades = instructorService.getGrades(studentID, returnAllGrades, courseID);
        return ResponseEntity.ok().body(grades);
    }

    @PostMapping("/grades")
    public ResponseEntity<String> inputGrade(@RequestBody Grade grade){
        try{
            instructorService.inputGrade(grade.getStudentID(), grade.getCourseID(), grade.getGradeReceived());
            return ResponseEntity.ok().body("Grade " + grade.getGradeReceived()+ " was posted to " + grade.getStudentID() + " for course " + grade.getCourseID());
        }
        catch(Exception e){
            Message message = new Message (HttpStatus.BAD_REQUEST,  "Student is not enrolled in this course." );
            return new ResponseEntity<>(message.getMessageContent(), message.getHttpStatus());
        }
    }

}
