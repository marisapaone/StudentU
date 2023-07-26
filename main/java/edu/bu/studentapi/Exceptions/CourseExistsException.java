package edu.bu.studentapi.Exceptions;

import java.io.Serializable;

public class CourseExistsException extends Exception {

    public CourseExistsException(String message) {
        super(message);
    }
}
