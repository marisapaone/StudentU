package edu.bu.studentapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * This class will be used to represent the schedule table in our database
 */
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;
    @ManyToMany(cascade = CascadeType.ALL)
    private HashMap<Period, Course> courses;
    @NotNull
    private Boolean enrolledForLunch;
    @NotNull
    private Boolean enrolledForStudyHall;
}
