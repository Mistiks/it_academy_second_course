package storage;

import model.User;
import storage.api.IUserStorage;
import java.util.HashMap;
import java.util.Map;

/**
 * Class-implementation of IUserStorage. Uses HashMap as storage of users
 *
 * @author Ilya Shadryn
 * @author Vadim Rataiko
 * @version 1.0
 */
public class UserStorage implements IUserStorage {

    /** Instance of UserStorage class */
    private static final UserStorage instance = new UserStorage();

    /** User's storage */
    private final Map<String, User> users = new HashMap<>();

    /** Private default constructor */
    private UserStorage() {
    }

    /**
     * Retrieves user with provided login
     *
     * @param login login of user for search
     * @return User with provided login. Could be null if user doesn't exist
     */
    @Override
    public User get(String login) {
        return this.users.get(login);
    }

    /**
     * Adds user in storage. Throws exception if user with provided username
     * already exists
     *
     * @param user user for adding
     */
    @Override
    public void add(User user) {
        if (this.users.containsKey(user.getUsername())) {
            throw new IllegalArgumentException("Пользователь уже сущуствует");
        }

        this.users.put(user.getUsername(), user);
    }

    /**
     * Removes user from storage
     *
     * @param login username to delete from storage
     */
    public void deleteUser(String login) {
        this.users.remove(login);
    }

    /**
     * Retrieves instance of UserStorage object
     *
     * @return UserStorage object
     */
    public static UserStorage getInstance() {
        return instance;
    }
}
