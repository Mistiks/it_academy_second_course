package dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for AirportDAO object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class AirportDAOTest {

    /** Instance of AirportDAO object */
    private final AirportDAO instance = AirportDAO.getInstance();

    /** Right result variable */
    private final int expectedCount = 104;

    /** Testing getList method */
    @Test
    public void getListTest() {
        Assertions.assertEquals(this.expectedCount, instance.getList(null).size());
    }

    /** Testing getRowCount method */
    @Test
    public void getRowCountTest() {
        Assertions.assertEquals(this.expectedCount, instance.getRowCount(null));
    }

    /** Testing getFieldTest method */
    @Test
    public void getFieldTest() {
        Assertions.assertEquals(this.expectedCount, instance.getField("city").size());
        Assertions.assertEquals(this.expectedCount, instance.getField("airport_name").size());
    }
}