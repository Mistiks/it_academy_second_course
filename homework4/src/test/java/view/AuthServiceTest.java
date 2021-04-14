package view;

import model.User;
import org.junit.jupiter.api.Test;
import storage.dao.UserDAO;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for AuthService object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class AuthServiceTest {

    /** Instance of AuthService object */
    private final AuthService instance = AuthService.getInstance();

    /** Instance of UserDAO object */
    private final UserDAO userInstance = UserDAO.getInstance();

    /** Tests authentication(String login, String password) method. */
    @Test
    public void authenticationTest() {
        assertNull(instance.authentication("Test", "Test"));
        userInstance.add(new User("Test", "Test" , "Test",
                "Test", "123" , LocalDate.now()));
        assertNull(instance.authentication("Test", "Test"));
        assertNotNull(instance.authentication("Test", "123"));
        userInstance.deleteUser("Test");
    }
}