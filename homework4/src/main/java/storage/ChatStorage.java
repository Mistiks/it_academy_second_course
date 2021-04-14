package storage;

import model.Message;
import storage.api.IChatStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class which stores messages in HashMap variable
 *
 *  @author Ilya Shadryn
 *  @author Vadim Rataiko
 *  @version 1.0
 */
public class ChatStorage implements IChatStorage {

    /** Instance of ChatStorage class */
    private final static ChatStorage instance = new ChatStorage();

    /** Storage of messages */
    private final Map<String, List<Message>> chats = new HashMap<>();

    /**
     * Retrieves messages for user with provided login
     *
     * @param login user's login
     * @return list with messages for user with provided login
     */
    @Override
    public List<Message> get(String login) {
        return this.chats.get(login);
    }

    /**
     * Add message to storage
     *
     * @param login recipient login
     * @param message Message object which will be saved
     */
    @Override
    public void addMessage(String login, Message message) {
        List<Message> chat;
        if(this.chats.containsKey(login)){
            chat = this.chats.get(login);
        } else {
            chat = new ArrayList<>();
            this.chats.put(login, chat);
        }
        chat.add(message);
    }

    /**
     * Deletes all messages for provided recipient.
     * Now used only for testing purposes.
     *
     * @param recipient searching parameter
     */
    public void deleteMessage(String recipient) {
        chats.remove(recipient);
    }

    /**
     * Retrieves instance of ChatStorage object
     *
     * @return ChatStorage object
     */
    public static ChatStorage getInstance() {
        return instance;
    }
}