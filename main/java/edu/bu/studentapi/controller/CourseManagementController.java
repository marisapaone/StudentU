package edu.bu.studentapi.controller;

import edu.bu.studentapi.dto.Message;
import edu.bu.studentapi.model.Student;
import edu.bu.studentapi.service.CourseManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courseManagement")
public class CourseManagementController {
  private CourseManagementService courseManagementService;

  @Autowired
  public CourseManagementController(CourseManagementService courseManagementService){
    this.courseManagementService = courseManagementService;
  }

  @PutMapping("/register")
  public ResponseEntity<String> registerClass(@RequestParam Long studentID, @RequestParam Long courseID) throws Exception {
    try {
      courseManagementService.registerClass(studentID, courseID);
      return ResponseEntity.ok().body("Student with ID: " + studentID + " has registered class with ID: " + courseID);
    }
    catch (Exception e){
      Message message = new Message (HttpStatus.BAD_REQUEST,  "This student is already registered for this class" );
      return new ResponseEntity<>(message.getMessageContent(), message.getHttpStatus());
    }

  }

  @PutMapping("/drop")
  public ResponseEntity<String> dropClass(@RequestParam Long studentID, @RequestParam Long courseID) throws Exception {
    try{
      courseManagementService.dropClass(studentID, courseID);
      return ResponseEntity.ok().body("Student with ID: " + studentID + " has dropped class with ID: " + courseID);
    }
    catch(Exception e){
      Message message = new Message (HttpStatus.NOT_FOUND,  "This student was not registered for this class" );
      return new ResponseEntity<>(message.getMessageContent(), message.getHttpStatus());
    }
  }


}


