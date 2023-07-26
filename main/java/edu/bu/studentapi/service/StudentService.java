package edu.bu.studentapi.service;


import edu.bu.studentapi.model.*;
import edu.bu.studentapi.repository.*;

import edu.bu.studentapi.model.Grade;
import edu.bu.studentapi.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {

    @Autowired
    private StudentRepoInt studentRepoInt;
    @Autowired
    private CourseRepoInt courseRepoInt;
    @Autowired
    private GradeRepoInt gradeRepoInt;




    public Student getStudent(Long studentID){
        return studentRepoInt.getStudentBystudentID(studentID);
    }

    public Student saveStudent(Student student){
        studentRepoInt.save(student);
        return student;
    }

    public List<Grade> getGrades(Long studentID,boolean returnAllGrades,Long courseID){
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

    public Double calculateGpa(Long studentID){
        List<Grade> gradeList = gradeRepoInt.getAllGradesByStudentID(studentID);
       Double total = 0.0;
       int count = 0;
       Double gpa = 0.0;

        for (Grade grade : gradeList){
            total = (grade.getGradeReceived()+total);
            count++;

        }
        gpa = total/count;
        return gpa;

    }

}
