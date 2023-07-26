package edu.bu.studentapi.controller;

import edu.bu.studentapi.service.CourseManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class CourseManagementControllerTest {

  @Mock
  private CourseManagementService courseManagementService;

  @InjectMocks
  private CourseManagementController controller;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }
  @Test
  public void testRegisterClass_Success() throws Exception {
    Long studentID = 1L;
    Long classID = 1L;

    ResponseEntity<String> response = controller.registerClass(studentID, classID);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Student with ID: 1 has registered class with ID: 1", response.getBody());
    verify(courseManagementService).registerClass(studentID, classID);
  }



  @Test
  public void testDropClass_Success() throws Exception {
    Long studentID = 1L;
    Long classID = 1L;

    ResponseEntity<String> response = controller.dropClass(studentID, classID);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Student with ID: 1 has dropped class with ID: 1", response.getBody());
    verify(courseManagementService).dropClass(studentID, classID);
  }


}

