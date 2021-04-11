package view.api;

import model.Message;
import model.User;
import java.util.List;

public interface IMessageService {
    List<Message> get(User currentUser);
    void addMessage(String loginRecipient, Message message);
}
