package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Testing class for User objects
 *
 * @version 1.0
 * @author Vadim Rataiko
 */
public class UserTest {

    /** Instance of LocalDate class. Used as one of constructor parameters */
    private LocalDate localDate = LocalDate.now();

    /** Variable of User class, methods of which will be tested */
    private User user;

    /** Creates new class with filled data for all tests */
    @BeforeEach
    public void setUp() {
        user = new User("firstName", "lastName", "patronymic",
                "username", "password", localDate);
    }

    /** Checks correct getting of firstName field */
    @Test
    public void getFirstName() {
        Assertions.assertEquals("firstName", user.getFirstName());
    }

    /** Checks correct setting of firstName field */
    @Test
    public void setFirstName() {
        user.setFirstName("Test");
        Assertions.assertEquals("Test", user.getFirstName());
    }

    /** Checks correct getting of lastName field */
    @Test
    public void getLastName() {
        Assertions.assertEquals("lastName", user.getLastName());
    }

    /** Checks correct setting of lastName field */
    @Test
    public void setLastName() {
        user.setLastName("Test");
        Assertions.assertEquals("Test", user.getLastName());
    }

    /** Checks correct getting of patronymic field */
    @Test
    public void getPatronymic() {
        Assertions.assertEquals("patronymic", user.getPatronymic());
    }

    /** Checks correct setting of patronymic field */
    @Test
    public void setPatronymic() {
        user.setPatronymic("Test");
        Assertions.assertEquals("Test", user.getPatronymic());
    }

    /** Checks correct getting of username field */
    @Test
    public void getUsername() {
        Assertions.assertEquals("username", user.getUsername());
    }

    /** Checks correct setting of username field */
    @Test
    public void setUsername() {
        user.setUsername("Test");
        Assertions.assertEquals("Test", user.getUsername());
    }

    /** Checks correct getting of password field */
    @Test
    public void getPassword() {
        Assertions.assertEquals("password", user.getPassword());
    }

    /** Checks correct setting of password field */
    @Test
    public void setPassword() {
        user.setPassword("Test");
        Assertions.assertEquals("Test", user.getPassword());
    }

    /** Checks correct getting of dateOfBirth field */
    @Test
    public void getDateOfBirth() {
        Assertions.assertEquals(localDate, user.getDateOfBirth());
    }

    /** Checks correct setting of dateOfBirth field */
    @Test
    public void setDateOfBirth() {
        user.setDateOfBirth(LocalDate.MAX);
        Assertions.assertEquals(LocalDate.MAX, user.getDateOfBirth());
    }

    /** Checks String formatting of dateOfBirth field */
    @Test
    public void getDateOfBirthStringTest() {
        user.setDateOfBirth(LocalDate.of(2020, 1, 8));
        Assertions.assertEquals("2020-01-08", user.getDateOfBirthToString());
    }
}