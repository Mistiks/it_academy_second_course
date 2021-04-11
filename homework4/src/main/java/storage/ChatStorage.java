package storage;

import model.Message;
import storage.api.IChatStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatStorage implements IChatStorage {
    private final static ChatStorage instance = new ChatStorage();

    private final Map<String, List<Message>> chats = new HashMap<>();

    @Override
    public List<Message> get(String login) {
        return this.chats.get(login);
    }

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

    public static ChatStorage getInstance() {
        return instance;
    }
}