package edu.bu.studentapi.Exceptions;

public class CourseDoesNotExistException extends Exception{
    public CourseDoesNotExistException(String message) {
        super(message);
    }
}
