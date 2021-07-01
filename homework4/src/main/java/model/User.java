package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class which stores all entered user registration information
 *
 * @version 1.1.1
 * @author Vadim Rataiko
 */
public class User {

    /** User's first name */
    private final String firstName;

    /** User's last name */
    private final String lastName;

    /** User's patronymic */
    private final String patronymic;

    /** User's username */
    private final String username;

    /** User's password */
    private final String password;

    /** User's birth date */
    private final LocalDate dateOfBirth;

    /**
     * Constructor with parameters
     *
     * @param firstName  User's first name
     * @param lastName User's last name
     * @param patronymic User's patronymic
     * @param username User's username
     * @param password User's password
     * @param dateOfBirthString User's birth date
     */
    public User(String firstName, String lastName, String patronymic, String username, String password, String dateOfBirthString) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.username = username;
        this.password = password;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            dateOfBirth = LocalDate.parse(dateOfBirthString, formatter);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid date of birth");
        }
    }

    /**
     * Getter for firstName field
     *
     * @return String with user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for lastName field
     *
     * @return String with user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for patronymic field
     *
     * @return String with user's patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Getter for username field
     *
     * @return String with user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for password field
     *
     * @return String with user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for dateOfBirth field
     *
     * @return LocalDate with user's birth date
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}