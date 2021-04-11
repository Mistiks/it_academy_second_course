package dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Test class for DataSourceCreatorDemoTest object
 *
 * @author Vadim Rataiko
 * @since 1.0
 */
public class DataSourceCreatorDemoTest {

    /** Instance of DataSource object */
    private DataSource instance;

    /** Testing getInstance method */
    @Test
    public void getInstanceTest() {

        try {
            this.instance = DataSourceCreatorDemo.getInstance();
            Assertions.assertEquals(0, instance.getLoginTimeout());
        } catch (IOException | SQLException | PropertyVetoException e) {
            Assertions.fail("Exception appeared");
            e.printStackTrace();
        }
    }
}