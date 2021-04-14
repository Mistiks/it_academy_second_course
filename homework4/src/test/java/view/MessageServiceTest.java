package view;

import model.Message;
import model.User;
import org.junit.jupiter.api.Test;
import storage.dao.MessageDAO;
import storage.dao.UserDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for MessageService object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class MessageServiceTest {

    /** Instance of MessageService object */
    private final MessageService instance = MessageService.getInstance();

    /** Instance of MessageDAO object */
    private final MessageDAO messageInstance = MessageDAO.getInstance();

    /** Instance of UserDAO object */
    private final UserDAO userInstance = UserDAO.getInstance();

    /**
     * Testing all functionality from class
     * User "test" shouldn't have messages before starting test.
     */
    @Test
    public void classTest() {
        User user = new User("test", "test", "test", "test", "test", LocalDate.now());
        userInstance.add(user);
        instance.addMessage("test", new Message("test", "test", "test", LocalDateTime.now()));
        assertEquals(1, instance.get(user).size());
        messageInstance.deleteMessage("test");
        assertEquals(0, instance.get(user).size());
        userInstance.deleteUser("test");
    }
}