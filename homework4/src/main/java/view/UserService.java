package view;

import model.User;
import storage.api.IUserStorage;
import storage.dao.UserDao;
import view.api.IUserService;

/**
 * Class that represents api for user registration and user search.
 * Implements IUserService.
 *
 * @author Ilya Shadryn
 * @version 1.0
 */
public class UserService implements IUserService {

    /** Instance of UserService object */
    private final static UserService instance = new UserService();

    /** Instance of IUserStorage interface */
    private final IUserStorage userStorage;

    /** Private constructor that defines implementation of IUserStorage interface */
    private UserService() {
        this.userStorage = UserDao.getInstance();
    }

    /**
     * Retrieves user with provided login
     *
     * @param login username for search
     * @return User object with provided login if user exist, null if doesn't
     */
    @Override
    public User get(String login) {
        return this.userStorage.get(login);
    }

    /**
     * Register user if all fields are filled, and username is unique
     *
     * @param user User object with user registration data
     */
    @Override
    public void signUp(User user) {
        this.validationForSignUp(user);
        this.userStorage.add(user);
    }

    /**
     * Checks if all fields are filled with data.
     * Throws IllegalArgument Exception if not
     *
     * @param user User object with user registration data
     */
    private void validationForSignUp(User user) {

        if (this.nullOrEmpty(user.getUsername()) || this.nullOrEmpty(user.getPassword())
                || this.nullOrEmpty(user.getFirstName()) || this.nullOrEmpty(user.getLastName())
                || this.nullOrEmpty(user.getPatronymic())) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if string equals null or empty
     *
     * @param val string to check
     * @return true if string isn't empty, false if empty or null
     */
    private boolean nullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }

    /**
     * Receives instance of UserService object
     *
     * @return UserService object
     */
    public static UserService getInstance() {
        return instance;
    }
}
