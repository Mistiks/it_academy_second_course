package core.dto;

/**
 * Dto class for homework needs
 *
 * @author Vadim Rataiko
 * @since 0.0.0
 */
public class Person {

    /** Variable which stores person first name */
    private String firstName;

    /** Variable which stores person last name */
    private String lastName;

    /** Variable which stores person age*/
    private int age;

    /** Constructor with parameters
     *
     * @param firstName person first name
     * @param lastName person last name
     * @param age person age
     */
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Getter for first name
     *
     * @return person first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for person first name
     *
     * @param firstName person first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for last name
     *
     * @return person last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for last name
     *
     * @param lastName person last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for person age
     *
     * @return person age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter for person age
     *
     * @param age person age
     */
    public void setAge(int age) {
        this.age = age;
    }
}