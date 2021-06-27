package view;

import model.User;
import org.junit.jupiter.api.Test;
import storage.api.IUserRepository;
import view.api.IAuthService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for AuthService object
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
public class AuthServiceTest {

    /** Instance of AuthService class */
    private IAuthService authService;

    /** Instance of UserService class */
    private UserService userService;

    /** Instance of IUserRepository interface */
    private IUserRepository repository;

    /**
     * Default constructor with parameters
     *
     * @param authService Instance of AuthService class
     * @param userService Instance of UserService class
     * @param repository Instance of IUserRepository interface
     */
    public AuthServiceTest(AuthService authService, UserService userService, IUserRepository repository) {
        this.authService = authService;
        this.userService = userService;
        this.repository = repository;
    }

    /** Tests authentication(String login, String password) method. */
    @Test
    public void authenticationTest() {
        assertNull(authService.authentication("Test", "Test"));
        userService.signUp(new User("Test", "Test" , "Test",
                "Test", "123" ,"10/10/2010"));
        assertNull(authService.authentication("Test", "Test"));
        assertNotNull(authService.authentication("Test", "123"));
        long result = repository.deleteByUsername("Test");
        assertEquals(1, result);
    }
}