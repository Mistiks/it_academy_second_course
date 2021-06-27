package view;

import model.User;
import org.junit.jupiter.api.Test;
import storage.api.IUserRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for UserService object
 *
 * @author Vadim Rataiko
 * @version 1.2
 */
public class UserServiceTest {

    /**
     * Instance of UserService class
     */
    private UserService userService;

    /**
     * Instance of IUserRepository interface
     */
    private IUserRepository userRepository;

    /**
     * Default constructor with parameters
     *
     * @param userService    Instance of UserService class
     * @param userRepository Instance of IUserRepository interface
     */
    public UserServiceTest(UserService userService, IUserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * Testing all functionality from class
     * User "test" shouldn't exist before starting test.
     */
    @Test
    public void classTest() {
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> userService.signUp(new User("test", "test", "test",
                        "test", "", "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> userService.signUp(new User("test", "test", "test",
                        "", "test", "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> userService.signUp(new User("test", "test", "test",
                        null, "test", "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> userService.signUp(new User("", "test", "test",
                        "test", "test", "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> userService.signUp(new User("test", "", "test",
                        "test", "test", "10/10/2010")));
        thrown = assertThrows(IllegalArgumentException.class,
                () -> userService.signUp(new User("test", "test", "",
                        "test", "test", "10/10/2010")));
        userService.signUp(new User("test", "test", "test",
                "test", "123", "10/10/2010"));
        assertEquals("test", userService.get("test").getFirstName());
        userRepository.deleteByUsername("test");
    }
}
