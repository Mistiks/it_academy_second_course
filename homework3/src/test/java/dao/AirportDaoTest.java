package dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for AirportDAO object
 *
 * @author Vadim Rataiko
 * @version 1.0.1
 */
public class AirportDaoTest {

    /** Instance of AirportDao object */
    private final AirportDao instance = AirportDao.getInstance();

    /** Right result variable */
    private final int expectedCount = 104;

    /** Language parameter for test */
    String[] language = {"ru"};

    /** Testing getList method */
    @Test
    public void getListTest() {
        Assertions.assertEquals(this.expectedCount, instance.getList(language).size());
    }

    /** Testing getRowCount method */
    @Test
    public void getRowCountTest() {
        Assertions.assertEquals(this.expectedCount, instance.getRowCount(language));
    }

    /** Testing getFieldTest method */
    @Test
    public void getFieldTest() {
        Assertions.assertEquals(this.expectedCount, instance.getField("city").size());
        Assertions.assertEquals(this.expectedCount, instance.getField("timezone").size());
    }
}