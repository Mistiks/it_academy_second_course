package dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for FlightDAO object
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class FlightDaoTest {

    /** Instance of AirportDao object */
    private final FlightDao instance = FlightDao.getInstance();

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
    }

    /** Testing getFieldTest method */
    @Test
    public void getFieldTest() {
        Assertions.assertEquals(214867, instance.getField("flight_no").size());
    }
}