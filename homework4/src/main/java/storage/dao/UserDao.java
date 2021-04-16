package storage.dao;

import model.User;
import storage.api.IUserStorage;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class-connector to users table in database
 *
 * @author Vadim Rataiko
 * @version 1.0.1
 */
public class UserDao implements IUserStorage {

    /** Instance of UserDAO object */
    private final static UserDao instance = new UserDao();

    /** Instance of DataSource object */
    private DataSource ds;

    /** Default constructor that defines DataSource object */
    private UserDao() {
        try {
            this.ds = DataSourceCreatorDemo.getInstance();
        } catch (SQLException | PropertyVetoException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves user from database in case when record with same login exists
     *
     * @param login username for search in database
     * @return object of class User. Could be null if user doesn't exist
     */
    @Override
    public User get(String login) {

        if (!isUserExist(login)) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        User user = null;
        String sql = "SELECT * FROM homework3.users WHERE users.username = ?";

        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    String firstName = result.getString("first_name");
                    String lastName = result.getString("last_name");
                    String patronymic = result.getString("patronymic");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    LocalDate birthDate = result.getDate("birth_date").toLocalDate();
                    String date = birthDate.format(formatter);
                    user = new User(firstName, lastName, patronymic, username, password, date);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    /**
     * Checks if username exists in database
     *
     * @param login username for search in database
     * @return true if username exists, false if username doesn't exist or caught exception
     */
    private boolean isUserExist(String login) {
        String sql = "SELECT COUNT(*) FROM homework3.users\n" +
                "WHERE users.username = ?";

        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);

            try (ResultSet result = statement.executeQuery()) {
                result.next();

                return result.getInt("count") == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Adds record about user in database
     *
     * @param user User object which data will be saved in database
     */
    @Override
    public void add(User user) {

        if (isUserExist(user.getUsername())) {
            throw new IllegalArgumentException("Пользователь уже существует");
        }

        String sql = "INSERT INTO homework3.users VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPatronymic());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setDate(6, java.sql.Date.valueOf(user.getDateOfBirth()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("User adding error");
        }
    }

    /**
     * Removes user from database
     *
     * @param login username to delete in database
     */
    public void deleteUser(String login) {
        String sql = "DELETE FROM homework3.users WHERE users.username = ?";

        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves instance of UserDAO class
     *
     * @return UserDao class
     */
    public static UserDao getInstance() {
        return instance;
    }
}