package storage.dao;

import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserDAO object
 *
 * @author Vadim Rataiko
 * @version 1.0.1
 */
public class UserDaoTest {

    /** Instance of UserDAO object */
    private final UserDao instance = UserDao.getInstance();

    /** Testing get() method. */
    @Test
    public void getTest() {
        instance.add(new User("first", "last" , "patronymic",
                "classTest", "123" , "10/10/2010"));
        Assertions.assertEquals("first", instance.get("classTest").getFirstName());
        instance.deleteUser("classTest");
        assertNull(instance.get("classTest"));
    }

    /**
     * Testing add(User user) and deleteUser(String login) methods.
     */
    @Test
    public void addDeleteTest() {
        instance.add(new User("Test", "Test" , "Test",
                "addTest", "123" , "10/10/2010"));
        assertNotNull(instance.get("addTest"));
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.add(new User("Test", "Test" , "Test",
                        "addTest", "123" , "10/10/2010")));
        instance.deleteUser("addTest");
        assertNull(instance.get("addTest"));
    }
}