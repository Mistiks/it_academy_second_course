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
 * @version 1.1
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
     * Saves message, sent to user
     *
     * @param message Message object, that will be saved
     */
    @Override
    public void addMessage(Message message) {
        this.repository.save(message);
    }
}
