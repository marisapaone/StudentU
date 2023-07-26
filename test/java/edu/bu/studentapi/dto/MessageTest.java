package edu.bu.studentapi.dto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageTest {

    @Test
    void testHttpStatus() {
        Message message = createMessage();
        Message message1 = new Message();
        message1.setHttpStatus(HttpStatus.CREATED);
        assertEquals(HttpStatus.CREATED, message.getHttpStatus());
        assertEquals(HttpStatus.CREATED, message1.getHttpStatus());
    }


    @Test
    void getMessageContent() {
        Message message = createMessage();
        Message message1 = new Message();
        message1.setMessageContent("goodbye");
        assertEquals("hello", message.getMessageContent());
        assertEquals("goodbye", message1.getMessageContent());
    }



    private Message createMessage(){
        return new Message(
                HttpStatus.CREATED, "hello"
        );
    }
}