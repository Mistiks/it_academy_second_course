package dao.hibernate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for FlightHibernate object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class FlightHibernateTest {

    /** Instance of FlightHibernate object */
    private final FlightHibernate instance = FlightHibernate.getInstance();

    /** Testing getList method */
    @Test
    public void getListTest() {
        String[] testArray = {"06/13/2017", "Домодедово", "Пулково", "25" ,"1"};
        Assertions.assertEquals(4, instance.getList(testArray).size());
    }

    /** Testing getRowCount method */
    @Test
    public void getRowCountTest() {
        String[] testArray = {"06/13/2017", "Чебоксары", "Шереметьево"};
        Assertions.assertEquals(1, instance.getRowCount(testArray));
        testArray = new String[]{"06/13/2017", "Not existing airport", "Шереметьево"};
        Assertions.assertEquals(0, instance.getRowCount(testArray));
    }
}