package edu.bu.studentapi.service;

import edu.bu.studentapi.model.Course;
import edu.bu.studentapi.model.Grade;
import edu.bu.studentapi.model.Instructor;
import edu.bu.studentapi.model.Student;
import edu.bu.studentapi.repository.CourseRepoInt;
import edu.bu.studentapi.repository.GradeRepoInt;
import edu.bu.studentapi.repository.InstructorRepoInt;
import edu.bu.studentapi.repository.StudentRepoInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InstructorService {

    private InstructorRepoInt instructorRepoInt;

    private GradeRepoInt gradeRepoInt;

    private CourseRepoInt courseRepoInt;

    private StudentRepoInt studentRepoInt;

    @Autowired
    public InstructorService(InstructorRepoInt instructorRepoInt, GradeRepoInt gradeRepoInt,
                             CourseRepoInt courseRepoInt, StudentRepoInt studentRepoInt) {
        this.instructorRepoInt = instructorRepoInt;
        this.gradeRepoInt = gradeRepoInt;
        this.courseRepoInt = courseRepoInt;
        this.studentRepoInt = studentRepoInt;
    }

    public Instructor saveInstructor(Instructor instructor){
         instructorRepoInt.save(instructor);
        return instructor;
    }

    public List<Grade> getGrades(Long studentID, boolean returnAllGrades, Long courseID){
        List<Grade> gradeList = gradeRepoInt.getAllGradesByStudentID(studentID);

        if(returnAllGrades == true)
        {
            return gradeList;
        }
        else
        {
            List<Grade> courseGradeList = new ArrayList<Grade>();
            for(int i = 0; i< gradeList.size(); i++)
            {
                Grade grade = gradeList.get(i);
                Long gradeClassID = grade.getCourseID();
                if(gradeClassID == courseID)
                {
                    courseGradeList.add(grade);
                }
            }
            return courseGradeList;
        }
    }


    public void inputGrade(Long studentID, Long courseID, Double gradeRecieved) throws Exception{

        List<Student> studentList = courseRepoInt.getCourseByCourseID(courseID).getStudentList();
        Student student = studentRepoInt.getStudentBystudentID(studentID);

        if (studentList.contains(student)){
            Grade grade = new Grade();
            grade.setGradeReceived(gradeRecieved);
            grade.setStudentID(studentID);
            grade.setCourseID(courseID);
            gradeRepoInt.save(grade);
        }
        else{
            throw new Exception("Student not enrolled in this course");
        }
    }






}
