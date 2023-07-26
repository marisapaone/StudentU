package edu.bu.studentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * This class will be used to output error messages or other needed information to our consumers.
 */
public class Message {
    private HttpStatus httpStatus;
    private String messageContent;
}
