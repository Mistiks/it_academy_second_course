package web.servlets.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class for ENUM JUnit testing
 *
 * @version 1.0
 * @author Vadim Rataiko
 */
public class StorageTest {

    /** Method which checking SESSION value in ENUM */
    @Test
    public void testEnumSession() {
        Assertions.assertEquals("SESSION", String.valueOf(Storage.SESSION));
    }

    /** Method which checking COOKIES value in ENUM */
    @Test
    public void testEnumCookies() {
        Assertions.assertEquals("COOKIES", String.valueOf(Storage.COOKIES));
    }
}