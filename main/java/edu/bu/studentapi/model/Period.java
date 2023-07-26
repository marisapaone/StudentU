package edu.bu.studentapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * This class will be used to represent the period table in our database
 */
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long periodId;
    @NotNull
    private String periodName;
}
