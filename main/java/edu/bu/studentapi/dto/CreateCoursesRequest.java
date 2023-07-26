package edu.bu.studentapi.dto;

import edu.bu.studentapi.model.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
/**
 * DTO for when a list of classes are sent in
 */
public class CreateCoursesRequest {
    private List<Course> classList;
}
