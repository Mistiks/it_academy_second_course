package view;

import model.User;
import org.junit.jupiter.api.Test;
import storage.dao.UserDao;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for AuthService object
 *
 * @author Vadim Rataiko
 * @version 1.0.1
 */
public class AuthServiceTest {

    /** Instance of AuthService object */
    private final AuthService instance = AuthService.getInstance();

    /** Instance of UserDao object */
    private final UserDao userInstance = UserDao.getInstance();

    /** Tests authentication(String login, String password) method. */
    @Test
    public void authenticationTest() {
        assertNull(instance.authentication("Test", "Test"));
        userInstance.add(new User("Test", "Test" , "Test",
                "Test", "123" ,"10/10/2010"));
        assertNull(instance.authentication("Test", "Test"));
        assertNotNull(instance.authentication("Test", "123"));
        userInstance.deleteUser("Test");
    }
}