package storage;

import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserStorage object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class UserStorageTest {

    /**
     * Instance of UserStorage object
     */
    private final UserStorage instance = UserStorage.getInstance();

    /**
     * Testing get() method.
     */
    @Test
    public void getTest() {
        instance.add(new User("first", "last", "patronymic",
                "localTest", "123", LocalDate.now()));
        Assertions.assertEquals("first", instance.get("localTest").getFirstName());
        instance.deleteUser("localTest");
        assertNull(instance.get("localTest"));
    }

    /**
     * Testing add(User user) and deleteUser(String login) methods.
     */
    @Test
    public void addDeleteTest() {
        instance.add(new User("Test", "Test", "Test",
                "Test", "123", LocalDate.now()));
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.add(new User("Test", "Test", "Test",
                        "Test", "123", LocalDate.now())));
        assertNotNull(instance.get("Test"));
        instance.deleteUser("Test");
        assertNull(instance.get("Test"));
    }
}