package edu.bu.studentapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * This class will be used to represent the student table in our database
 */
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long studentID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String birthDate;
    private String socialSecurity;

}
