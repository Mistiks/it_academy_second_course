package view.api;

import model.Message;
import model.User;
import java.util.List;

/**
 * Interface which provides methods for working with messages
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
public interface IMessageService {

    /**
     * Retrieves all messages for current user
     *
     * @param currentUser User logged in application
     * @return all messages for current user
     */
    List<Message> get(User currentUser);

    /**
     * Saves message, sent to user
     *
     * @param message Message object, that will be saved
     */
    void addMessage(Message message);
}
