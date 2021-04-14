package view;

import model.Message;
import model.User;
import storage.api.IChatStorage;
import storage.dao.MessageDAO;
import view.api.IMessageService;
import java.util.List;

/**
 * Class that provides functionality of working with messages in application.
 * Implements IMessageService
 *
 * @author Ilya Shadryn
 * @version 1.0
 */
public class MessageService implements IMessageService {

    /** Instance of MessageService object */
    private final static MessageService instance = new MessageService();

    /** Instance of IChatStorage interface */
    private final IChatStorage chatStorage;

    /** Private constructor that defines implementation of IChatStorage interface */
    private MessageService() {
        this.chatStorage = MessageDAO.getInstance();
    }

    /**
     * Retrieves all messages for current user
     *
     * @param currentUser User logged in application
     * @return all messages for current user
     */
    @Override
    public List<Message> get(User currentUser) {
        return this.chatStorage.get(currentUser.getUsername());
    }

    /**
     * Saves message, sent to user
     *
     * @param loginRecipient message recipient
     * @param message Message object, that will be saved
     */
    @Override
    public void addMessage(String loginRecipient, Message message) {
        this.chatStorage.addMessage(loginRecipient, message);
    }

    /**
     * Receives instance of MessageService object
     *
     * @return MessageService object
     */
    public static MessageService getInstance() {
        return instance;
    }
}
