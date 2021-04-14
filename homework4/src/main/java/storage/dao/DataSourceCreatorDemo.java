package storage.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class for creating connection to database
 *
 * @author Ilya Shadryn
 * @version  1.0
 */
public class DataSourceCreatorDemo {

    /** Instance of DataSourceCreatorDemo object */
    private static DataSourceCreatorDemo instance;

    /** Instance of ComboPooledDataSource */
    private ComboPooledDataSource cpds;

    /**
     * Default constructor which defines ComboPooledDataSource object parameters
     *
     * @throws IOException failed or interrupted I/O operations
     * @throws SQLException provides information on a database access error or other errors
     * @throws PropertyVetoException when a proposed change to a property represents an unacceptable value
     */
    private DataSourceCreatorDemo() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/edu");
        cpds.setUser("postgres");
        cpds.setPassword("1642002059201Aa");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    /**
     * Receives instance of DataSource object
     *
     * @return DataSource object
     * @throws IOException failed or interrupted I/O operations
     * @throws SQLException provides information on a database access error or other errors
     * @throws PropertyVetoException when a proposed change to a property represents an unacceptable value
     */
    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (instance == null) {
            synchronized (DataSourceCreatorDemo.class) {
                instance = new DataSourceCreatorDemo();
            }
        }
        return instance.cpds;
    }
}
