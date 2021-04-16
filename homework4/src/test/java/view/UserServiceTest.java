package view;

import model.User;
import org.junit.jupiter.api.Test;
import storage.dao.UserDao;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for UserService object
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
public class UserServiceTest {

    /** Instance of UserService object */
    private final UserService instance = UserService.getInstance();

    /** Instance of UserDao object */
    private final UserDao userInstance = UserDao.getInstance();

    /**
     * Testing all functionality from class
     * User "test" shouldn't exist before starting test.
     */
    @Test
    public void classTest() {
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "test" , "test",
                        "test", "" , "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "test" , "test",
                        "", "test" , "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "test" , "test",
                        null, "test" , "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("", "test" , "test",
                        "test", "test" , "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "" , "test",
                        "test", "test" , "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "test" , "",
                        "test", "test" , "10/10/2010")));
        instance.signUp(new User("test", "test" , "test",
                "test", "123" , "10/10/2010"));
        assertEquals("test", instance.get("test").getFirstName());
        userInstance.deleteUser("test");
    }
}
