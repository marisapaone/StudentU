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

public class Gpa {

    @Id
    @NotNull
    private Long studentID;
    private double calculateGpa;
    @OneToMany
    List<Course> courses;

}
