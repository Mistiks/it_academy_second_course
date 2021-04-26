package dao.hibernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for AirportHibernate object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class AirportHibernateTest {

    /** Instance of AirportHibernate object */
    private final AirportHibernate instance = AirportHibernate.getInstance();

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
        Assertions.assertEquals(this.expectedCount, instance.getField("en").size());
    }
}