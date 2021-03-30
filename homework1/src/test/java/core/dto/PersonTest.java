package core.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class with JUnit tests for class Person
 *
 * @version 1.0
 * @author Vadim Rataiko
 */
public class PersonTest {

    /** Method which checks correct value getting from field that contains first name */
    @Test
    public void testGetFirstName() {
        Person person = new Person("First", "Name", 20);
        Assertions.assertEquals("First", person.getFirstName());
    }

    /** Method which checks correct value setting in field that contains first name */
    @Test
    public void testSetFirstName() {
        Person person = new Person("First", "Name", 20);
        person.setFirstName("TestFirst");
        Assertions.assertEquals("TestFirst", person.getFirstName());
    }

    /** Method which checks correct value getting from field that contains last name */
    @Test
    public void testGetLastName() {
        Person person = new Person("First", "Name", 20);
        Assertions.assertEquals("Name", person.getLastName());
    }

    /** Method which checks correct value setting in field that contains last name */
    @Test
    public void testSetLastName() {
        Person person = new Person("First", "Name", 20);
        person.setLastName("TestName");
        Assertions.assertEquals("TestName", person.getLastName());
    }

    /** Method which checks correct value getting from field that contains age */
    @Test
    public void testGetAge() {
        Person person = new Person("First", "Name", 20);
        Assertions.assertEquals(20, person.getAge());
    }

    /** Method which checks correct value setting in field that contains age */
    @Test
    public void testSetAge() {
        Person person = new Person("First", "Name", 20);
        person.setAge(25);
        Assertions.assertEquals(25, person.getAge());
    }
}