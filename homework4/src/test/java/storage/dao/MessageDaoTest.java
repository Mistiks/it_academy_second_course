package storage.dao;

import model.Message;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for MessageDAO object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class MessageDaoTest {

    /** Instance of MessageDao object */
    private final MessageDao instance = MessageDao.getInstance();

    /**
     * Testing all functionality from class
     * User "test" shouldn't have messages before starting test.
     */
    @Test
    public void classTest() {
        instance.addMessage("test", new Message("test", "test", "test", LocalDateTime.now()));
        assertEquals(1, instance.get("test").size());
        instance.deleteMessage("test");
        assertEquals(0, instance.get("test").size());
    }
}