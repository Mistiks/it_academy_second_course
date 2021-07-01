package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class which stores all entered user registration information
 *
 * @version 1.1
 * @author Vadim Rataiko
 */
@Entity(name = "User")
@Table(name = "users", schema = "homework3")
public class User implements Serializable {

    /** User's first name */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /** User's last name */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /** User's patronymic */
    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    /** User's username */
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    /** User's password */
    @Column(name = "password", nullable = false)
    private String password;

    /** User's birth date */
    @Column(name = "birth_date", nullable = false)
    private LocalDate dateOfBirth;


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

    /** Default constructor without parameters */
    public User() {}

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

    /**
     * Setter for firstName field
     *
     * @param firstName value to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter for lastName field
     *
     * @param lastName value to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter for patronymic field
     *
     * @param patronymic value to set
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Setter for username field
     *
     * @param username value to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter for password field
     *
     * @param password value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for dateOfBirth field
     *
     * @param dateOfBirth value to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}