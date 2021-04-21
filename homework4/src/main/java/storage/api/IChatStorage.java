package storage.api;

import model.Message;
import java.util.List;

/** Interface that provides api for access to message storage
 *
 * @author Ilya Shadryn
 * @author Vadim Rataiko
 * @version 1.0
 */
public interface IChatStorage {

    /**
     * Retrieves all messages for user with provided login
     *
     * @param login login of user for search
     * @return list with all messages for user
     */
    List<Message> get(String login);

    /**
     * Adds message to storage
     *
     * @param login user's login for adding
     * @param message message that will be added
     */
    void addMessage(String login, Message message);

    /**
     * Deletes all messages for provided recipient.
     * Now used only for testing purposes.
     *
     * @param recipient searching parameter
     */
    void deleteMessage(String recipient);
}