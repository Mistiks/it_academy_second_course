package core.entity;

import java.time.LocalDate;

/**
 * Class which stores all user registration information
 *
 * @version 1.0
 * @author Vadim Rataiko
 */
public class User {

    /** User's first name */
    private String firstName;

    /** User's last name */
    private String lastName;

    /** User's patronymic */
    private String patronymic;

    /** User's username */
    private String username;

    /** User's password */
    private String password;

    /** User's birth date */
    private LocalDate dateOfBirth;

    /**
     * Constructor with parameters
     *
     * @param firstName  User's first name
     * @param lastName User's last name
     * @param patronymic User's patronymic
     * @param username User's username
     * @param password User's password
     * @param dateOfBirth User's birth date
     */
    public User(String firstName, String lastName, String patronymic, String username, String password, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
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
     * Setter for firstName field
     *
     * @param firstName value which will be set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * Setter for lastName field
     *
     * @param lastName value which will be set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * Setter for patronymic field
     *
     * @param patronymic value which will be set
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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
     * Setter for username field
     *
     * @param username value which will be set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Setter for password field
     *
     * @param password value which will be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for dateOfBirth field
     *
     * @return LocalDate with user's birth date
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setter for dateOfBirth field
     *
     * @param dateOfBirth value which will be set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}