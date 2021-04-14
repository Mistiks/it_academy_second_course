package view;

import model.User;
import org.junit.jupiter.api.Test;
import storage.dao.UserDAO;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for UserService object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class UserServiceTest {

    /** Instance of UserService object */
    private final UserService instance = UserService.getInstance();

    /** Instance of UserDAO object */
    private final UserDAO userInstance = UserDAO.getInstance();

    /**
     * Testing all functionality from class
     * User "test" shouldn't exist before starting test.
     */
    @Test
    public void classTest() {
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "test" , "test",
                        "test", "" , LocalDate.now())));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "test" , "test",
                        "", "test" , LocalDate.now())));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "test" , "test",
                        null, "test" , LocalDate.now())));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("", "test" , "test",
                        "test", "test" , LocalDate.now())));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "" , "test",
                        "test", "test" , LocalDate.now())));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> instance.signUp(new User("test", "test" , "",
                        "test", "test" , LocalDate.now())));
        instance.signUp(new User("test", "test" , "test",
                "test", "123" , LocalDate.now()));
        assertEquals("test", instance.get("test").getFirstName());
        userInstance.deleteUser("test");
    }
}
