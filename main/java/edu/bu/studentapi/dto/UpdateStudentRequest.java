package edu.bu.studentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * DTO for the Update Student Request.
 */
public class UpdateStudentRequest {
    private Long student_id;
    private String email;
    private Date birthdate;
}
