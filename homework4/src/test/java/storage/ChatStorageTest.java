package storage;

import model.Message;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for ChatStorage object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class ChatStorageTest {

    /** Instance of ChatStorage object */
    private final ChatStorage instance = ChatStorage.getInstance();

    /**
     * Testing all functionality from class
     * User "test" shouldn't have messages before starting test.
     */
    @Test
    public void classTest() {
        Message message = new Message("test", "test", "test", LocalDateTime.now());
        instance.addMessage("test", message);
        assertEquals(1, instance.get("test").size());
        instance.addMessage("test", message);
        assertEquals(2, instance.get("test").size());
        instance.deleteMessage("test");
        assertNull(instance.get("test"));
    }
}