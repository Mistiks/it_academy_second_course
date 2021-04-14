package dao;

import dao.api.ITableAccess;
import model.Airport;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class-realisation of ITableAccess interface for airports_data table in database
 *
 * @author Vadim Rataiko
 * @version  1.0
 */
public class AirportDAO implements ITableAccess<Airport> {

    /** Instance of AirportDAO object */
    private final static AirportDAO instance = new AirportDAO();

    /** Instance of DataSource object */
    private DataSource ds;

    /** Default constructor that defines DataSource object */
    private AirportDAO() {
        try {
            this.ds = DataSourceCreatorDemo.getInstance();
        } catch (SQLException | PropertyVetoException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves list of airports
     *
     * @param parameters parameters array. Could be null depending on realisation
     * @return List with data about all airports in Russia
     */
    @Override
    public List<Airport> getList(String[] parameters) {
        List<Airport> listAirport = new ArrayList<>();
        String sql = "SELECT airport_code, airport_name::json ->> 'ru' as airport_name, " +
                "city::json ->> 'ru' as city, coordinates, timezone " +
                "FROM airports_data ORDER BY city;";

        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                String code = result.getString("airport_code");
                String name = result.getString("airport_name");
                String city = result.getString("city");
                String coordinates = result.getString("coordinates");
                String timezone = result.getString("timezone");
                Airport airport = new Airport(code, name, city, coordinates, timezone);

                listAirport.add(airport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listAirport;
    }

    /**
     * Receives count of rows that satisfy prepared SELECT request
     *
     * @param parameters parameters array. Could be null depending on realisation
     * @return number of selected rows
     */
    @Override
    public int getRowCount(String[] parameters) {
        int numberOfRows = 0;
        String sql = "SELECT COUNT(*)\n" + "FROM airports_data";

        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                numberOfRows = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numberOfRows;
    }

    /**
     * Taking data from field in database that satisfy prepared SELECT request
     *
     * @param name field for search
     * @return answer from database
     */
    @Override
    public List<String> getField(String name) {
        List<String> listField = new ArrayList<>();
        String sql;

        if (name.equals("airport_name") || name.equals("city")) {
            sql = "SELECT " + name + "::json ->> 'ru' as " + name +
                    " FROM airports_data ORDER BY city;";
        } else {
            sql = "SELECT " + name + " as " + name  +
                    " FROM airports_data ORDER BY city;";
        }

        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                String field = result.getString(name);
                listField.add(field);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listField;
    }

    /**
     * Receives instance of AirportDAO class
     *
     * @return AirportDAO class
     */
    public static AirportDAO getInstance() {
        return instance;
    }
}