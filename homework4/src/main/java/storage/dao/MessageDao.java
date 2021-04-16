package storage.dao;

import model.Message;
import storage.api.IChatStorage;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Class-connector to messages table in database
 *
 * @author Vadim Rataiko
 * @since 1.0
 */
public class MessageDao implements IChatStorage {

    /** Instance of MessageDAO object */
    private final static MessageDao instance = new MessageDao();

    /** Instance of DataSource object */
    private DataSource ds;

    /** Default constructor that defines DataSource object */
    private MessageDao() {
        try {
            this.ds = DataSourceCreatorDemo.getInstance();
        } catch (SQLException | PropertyVetoException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all messages from database for user with provided login
     *
     * @param login username for search in database
     * @return messages sent to user
     */
    @Override
    public List<Message> get(String login) {
        List<Message> listMessage = new ArrayList<>();
        String sql = "SELECT * FROM homework3.messages\n" +
                "WHERE messages.recipient = ? ORDER BY time";

        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    String sender = result.getString("sender");
                    String recipient = result.getString("recipient");
                    String text = result.getString("text");
                    LocalDateTime time = ((result.getObject("time", OffsetDateTime.class))).toLocalDateTime();
                    Message message = new Message(sender, recipient, text, time);

                    listMessage.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listMessage;
    }

    /**
     * Sends record about message in database
     * Id is auto-incremented by sequence in database
     *
     * @param login message recipient username
     * @param message Message object which fields will be saved in database
     */
    @Override
    public void addMessage(String login, Message message) {
        String sql = "INSERT INTO homework3.messages(sender, recipient, text, time) " +
                "VALUES(?, ?, ?, ?) RETURNING id";

        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setString(2, message.getRecipient());
            statement.setString(3, message.getText());

            ZoneId zoneId = ZoneId.systemDefault();
            statement.setObject(4, message.getTime().atZone(zoneId).toOffsetDateTime());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes all messages for provided recipient.
     * Now used only for testing purposes.
     *
     * @param recipient searching parameter
     */
    public void deleteMessage(String recipient) {
        String sql = "DELETE FROM homework3.messages WHERE messages.recipient = ?";

        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, recipient);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves instance of MessageDAO class
     *
     * @return MessageDao class
     */
    public static MessageDao getInstance() {
        return instance;
    }
}