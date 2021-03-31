package core.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class with JUnit tests for class Person
 *
 * @version 1.0.1
 * @author Vadim Rataiko
 */
public class PersonTest {

    /** Variable of Person class, methods of which will be tested */
    private Person person;

    /**
     * Creates new class with filled data for all tests
     *
     * @since 1.0.1
     */
    @BeforeEach
    public void setUp() {
        person = new Person("First", "Name", 20);
    }

    /** Checks correct value getting from field that contains first name */
    @Test
    public void testGetFirstName() {
        Assertions.assertEquals("First", person.getFirstName());
    }

    /** Checks correct value setting in field that contains first name */
    @Test
    public void testSetFirstName() {
        person.setFirstName("TestFirst");
        Assertions.assertEquals("TestFirst", person.getFirstName());
    }

    /** Checks correct value getting from field that contains last name */
    @Test
    public void testGetLastName() {
        Assertions.assertEquals("Name", person.getLastName());
    }

    /** Checks correct value setting in field that contains last name */
    @Test
    public void testSetLastName() {
        person.setLastName("TestName");
        Assertions.assertEquals("TestName", person.getLastName());
    }

    /** Checks correct value getting from field that contains age */
    @Test
    public void testGetAge() {
        Assertions.assertEquals(20, person.getAge());
    }

    /** Checks correct value setting in field that contains age */
    @Test
    public void testSetAge() {
        person.setAge(25);
        Assertions.assertEquals(25, person.getAge());
    }
}