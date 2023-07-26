package edu.bu.studentapi.service;

import edu.bu.studentapi.model.Grade;
import edu.bu.studentapi.repository.GradeRepoInt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetGradesTest {
    @InjectMocks
    private StudentService studentService;

    @InjectMocks
    private InstructorService instructorService;

    @Mock
    private GradeRepoInt mockGradeRepoInt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    public void testStudentGetAllGrades() {
        // Create test data
        Long studentID = 12345L;
        boolean returnAllGrades = true;
        Long courseID = 1l;
        List<Grade> gradeList = Arrays.asList(
                new Grade(1l, studentID, 1l, 69.8),
                new Grade(1l, studentID, 2l, 89.8),
                new Grade(1l, studentID, 3l, 95.8)
        );

        when(mockGradeRepoInt.getAllGradesByStudentID(studentID)).thenReturn(gradeList);

        List<Grade> result = studentService.getGrades(studentID, returnAllGrades, courseID);

        assertEquals(mockGradeRepoInt.getAllGradesByStudentID(studentID), result);
    }

    @Test
    public void testStudentGetGrades() {
        // Create test data
        Long studentID = 12345L;
        boolean returnAllGrades = false;
        Long courseID = 1l;
        Grade grade1 = new Grade(1l, studentID, 1l, 69.8);
        Grade grade2 = new Grade(1l, studentID, 2l, 89.8);
        Grade grade3 = new Grade(1l, studentID, 3l, 95.8);

        List<Grade> gradeList = Arrays.asList(grade1, grade2, grade3);

        List<Grade> classGradeList = Arrays.asList(grade1);

        when(mockGradeRepoInt.getAllGradesByStudentID(studentID)).thenReturn(gradeList);

        List<Grade> result = studentService.getGrades(studentID, returnAllGrades, courseID);

        assertEquals(classGradeList, result);
    }

    @Test
    public void testInstructorGetAllGrades() {
        // Create test data
        Long studentID = 12345L;
        boolean returnAllGrades = true;
        Long courseID = 1l;
        List<Grade> gradeList = Arrays.asList(
                new Grade(1l, studentID, 1l, 69.8),
                new Grade(1l, studentID, 2l, 89.8),
                new Grade(1l, studentID, 3l, 95.8)
        );

        when(mockGradeRepoInt.getAllGradesByStudentID(studentID)).thenReturn(gradeList);

        List<Grade> result = instructorService.getGrades(studentID, returnAllGrades, courseID);

        assertEquals(mockGradeRepoInt.getAllGradesByStudentID(studentID), result);
    }

    @Test
    public void testInstructorGetGrades() {
        // Create test data
        Long studentID = 12345L;
        boolean returnAllGrades = false;
        Long courseID = 1l;
        Grade grade1 = new Grade(1l, studentID, 1l, 69.8);
        Grade grade2 = new Grade(1l, studentID, 2l, 89.8);
        Grade grade3 = new Grade(1l, studentID, 3l, 95.8);

        List<Grade> gradeList = Arrays.asList(grade1, grade2, grade3);

        List<Grade> classGradeList = Arrays.asList(grade1);

        when(mockGradeRepoInt.getAllGradesByStudentID(studentID)).thenReturn(gradeList);

        List<Grade> result = instructorService.getGrades(studentID, returnAllGrades, courseID);

        assertEquals(classGradeList, result);
    }
}
