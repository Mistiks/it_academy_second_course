package storage;

import model.User;
import storage.api.IUserStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserStorage implements IUserStorage {
    private static final UserStorage instance = new UserStorage();

    private final Map<String, User> users = new HashMap<>();

    private UserStorage() {
    }

    @Override
    public User get(String login) {
        return this.users.get(login);
    }

    @Override
    public void add(User user) {
        if(this.users.containsKey(user.getUsername())){
            throw new IllegalArgumentException("Пользователь уже сущуствует");
        }
        this.users.put(user.getUsername(), user);
    }

    @Override
    public Collection<User> getAll() {
        return this.users.values();
    }

    public static UserStorage getInstance() {
        return instance;
    }
}
