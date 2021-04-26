package dao.postgresql;

import dao.api.ITableAccess;
import dao.connectors.DataSourceCreatorDemo;
import model.dto.Flight;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class-realisation of ITableAccess interface for flights table in database
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class FlightDao implements ITableAccess<Flight> {

    /** Instance of FlightDAO object */
    private final static FlightDao instance = new FlightDao();

    /** Instance of DataSource object */
    private DataSource ds;

    /** Default constructor that defines DataSource object */
    private FlightDao() {
        try {
            this.ds = DataSourceCreatorDemo.getInstance();
        } catch (SQLException | PropertyVetoException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receives data about flights from departure to arrival airport in concrete day.
     * parameters[0] - date of flight, parameters[1] - departure airport
     * parameters[2] - arrival airport, parameters[3] - amount of rows per page
     * parameters[4] - page
     *
     * @param parameters parameters array. Could be null depending on realisation
     * @return answer from database with flight list
     */
    @Override
    public List<Flight> getList(String[] parameters) {
        List<Flight> listFlights = new ArrayList<>();
        String sql = "SELECT flight_no, scheduled_departure::timestamp, scheduled_arrival::timestamp, " +
                "from_airport.airport_name::json ->> 'ru' as departure_airport, \n" +
                "to_airport.airport_name::json ->> 'ru' as arrival_airport, status, model::json ->> 'ru' " +
                "as model\n" + "FROM flights\n" +
                "JOIN aircrafts_data ON flights.aircraft_code = aircrafts_data.aircraft_code\n" +
                "JOIN airports_data from_airport ON flights.departure_airport = from_airport.airport_code\n" +
                "JOIN airports_data to_airport ON flights.arrival_airport = to_airport.airport_code\n" +
                "WHERE DATE(flights.scheduled_departure) = ? AND " +
                "from_airport.airport_name::json ->> 'ru' = ? \n" +
                "AND to_airport.airport_name::json ->> 'ru' = ?\n" +
                "ORDER BY scheduled_departure\n LIMIT ? OFFSET ?";

        String postgresqlDate = parameters[0].substring(6,10) + "-" + parameters[0].substring(0,2) + "-" +
                parameters[0].substring(3,5);
        LocalDate localDate = LocalDate.parse(postgresqlDate);
        int limit = Integer.parseInt(parameters[3]);
        int offset = (Integer.parseInt(parameters[4]) - 1) * limit;

        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, localDate);
            statement.setString(2, parameters[1]);
            statement.setString(3, parameters[2]);
            statement.setInt(4, limit);
            statement.setInt(5, offset);
            System.out.println(statement);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    String flight_no = result.getString("flight_no");
                    String scheduled_departure = result.getString("scheduled_departure");
                    String scheduled_arrival = result.getString("scheduled_arrival");
                    String departure_airport = result.getString("departure_airport");
                    String arrival_airport = result.getString("arrival_airport");
                    String status = result.getString("status");
                    String model = result.getString("model");
                    Flight flight = new Flight(flight_no, scheduled_departure, scheduled_arrival,
                            departure_airport, arrival_airport, status, model);

                    listFlights.add(flight);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listFlights;
    }

    /**
     * Receives count of rows that satisfy prepared SELECT request
     * parameters[0] - date of flight, parameters[1] - departure airport
     * parameters[2] - arrival airport
     *
     * @param parameters parameters array
     * @return number of selected rows
     */
    public int getRowCount(String[] parameters) {
        int numberOfRows = 0;
        String sql = "SELECT COUNT(*)\n" + "FROM flights\n" +
                "JOIN aircrafts_data ON flights.aircraft_code = aircrafts_data.aircraft_code\n" +
                "JOIN airports_data from_airport ON flights.departure_airport = from_airport.airport_code\n" +
                "JOIN airports_data to_airport ON flights.arrival_airport = to_airport.airport_code\n" +
                "WHERE DATE(flights.scheduled_departure) = ? " +
                "AND from_airport.airport_name::json ->> 'ru' = ? \n" +
                "AND to_airport.airport_name::json ->> 'ru' = ?";

        String postgresqlDate = parameters[0].substring(6,10) + "-" + parameters[0].substring(0,2) + "-" +
                parameters[0].substring(3,5);
        LocalDate localDate = LocalDate.parse(postgresqlDate);

        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, localDate);
            statement.setString(2, parameters[1]);
            statement.setString(3, parameters[2]);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    numberOfRows = result.getInt(1);
                }
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
        String sql = "SELECT " + name + " as " + name +
                " FROM flights ORDER BY scheduled_departure;";

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
     * Receives instance of FlightDAO class
     *
     * @return FlightDAO class
     */

    public static FlightDao getInstance() {
        return instance;
    }
}