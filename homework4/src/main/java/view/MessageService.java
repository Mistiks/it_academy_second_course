package view;

import model.Message;
import model.User;
import storage.ChatStorage;
import storage.api.IChatStorage;
import view.api.IMessageService;

import java.util.List;

public class MessageService implements IMessageService {
    private final static MessageService instance = new MessageService();

    private final IChatStorage chatStorage;

    private MessageService() {
        this.chatStorage = ChatStorage.getInstance();
    }

    @Override
    public List<Message> get(User currentUser) {
        return this.chatStorage.get(currentUser.getUsername());
    }

    @Override
    public void addMessage(String loginRecipient, Message message) {
        this.chatStorage.addMessage(loginRecipient, message);
    }

    public static MessageService getInstance() {
        return instance;
    }
}
