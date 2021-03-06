package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * Testing class for Message objects
 *
 * @version 1.1
 * @author Vadim Rataiko
 */
public class MessageTest {

    /** Instance of LocalDateTime class. Used as one of constructor parameters */
    private LocalDateTime time = LocalDateTime.now();

    /** Variable of Message class, methods of which will be tested */
    private Message message;

    /** Creates new class with filled data for all tests */
    @BeforeEach
    public void setUp() {
        message = new Message("Sender", "Recipient", "hello", time);
    }

    /** Checks correct getting of sender field */
    @Test
    public void getSender() {
        Assertions.assertEquals("Sender", message.getSender());
    }

    /** Checks correct getting of recipient field */
    @Test
    public void getRecipient() {
        Assertions.assertEquals("Recipient", message.getRecipient());
    }

    /** Checks correct getting of text field */
    @Test
    public void getText() {
        Assertions.assertEquals("hello", message.getText());
    }

    /** Checks correct getting of time field */
    @Test
    public void getTime() {
        Assertions.assertEquals(time, message.getTime());
    }
}