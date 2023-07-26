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
 * This class will be used to represent the instructor table in our database
 */
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long instructorID;
    @NotNull
    private String instructorName;

}
