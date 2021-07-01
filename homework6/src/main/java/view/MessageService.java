package view;

import model.Message;
import model.User;
import storage.api.IMessageRepository;
import view.api.IMessageService;
import java.util.List;

/**
 * Class that provides functionality of working with messages in application.
 * Implements IMessageService
 *
 * @author Vadim Rataiko
 * @version 1.2
 */
public class MessageService implements IMessageService {

    private final IMessageRepository repository;

    public MessageService(IMessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all messages for current user
     *
     * @param currentUser User logged in application
     * @return all messages for current user
     */
    @Override
    public List<Message> get(User currentUser) {
        return this.repository.findAllByRecipient(currentUser.getUsername());
    }

    /**
     * Saves message, sent it to user.
     * Throws IllegalArgumentException if fields with recipient or text are empty
     *
     * @param message Message object, that will be saved
     */
    @Override
    public void addMessage(Message message) {
        if (nullOrEmpty(message.getRecipient()) || nullOrEmpty(message.getText())) {
            throw new IllegalArgumentException();
        } else {
            this.repository.save(message);
        }
    }

    /**
     * Checks if string equals null or empty
     *
     * @param val string to check
     * @return true if string isn't empty, false if empty or null
     * @since 1.2
     */
    private boolean nullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }
}
