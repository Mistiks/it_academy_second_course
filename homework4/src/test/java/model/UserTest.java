package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing class for User object
 *
 * @version 1.1
 * @author Vadim Rataiko
 */
public class UserTest {

    /** Variable of User class, methods of which will be tested */
    private User user;

    /** Creates new class with filled data for all tests */
    @BeforeEach
    public void setUp() {
        user = new User("firstName", "lastName", "patronymic",
                "username", "password", "10/10/2010");
    }

    /** Checks correct getting of firstName field */
    @Test
    public void getFirstName() {
        Assertions.assertEquals("firstName", user.getFirstName());
    }

    /** Checks correct getting of lastName field */
    @Test
    public void getLastName() {
        Assertions.assertEquals("lastName", user.getLastName());
    }

    /** Checks correct getting of patronymic field */
    @Test
    public void getPatronymic() {
        Assertions.assertEquals("patronymic", user.getPatronymic());
    }

    /** Checks correct getting of username field */
    @Test
    public void getUsername() {
        Assertions.assertEquals("username", user.getUsername());
    }

    /** Checks correct getting of password field */
    @Test
    public void getPassword() {
        Assertions.assertEquals("password", user.getPassword());
    }

    /** Checks correct getting of dateOfBirth field */
    @Test
    public void getDateOfBirth() {
        Assertions.assertEquals(2010, user.getDateOfBirth().getYear());
        Throwable thrown = assertThrows(IllegalArgumentException.class,
                () -> new User("test", "test" , "test",
                        "test", "password", "not a date"));
    }
}