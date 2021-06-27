package view;

import model.User;
import storage.api.IUserRepository;
import view.api.IUserService;

/**
 * Class for user registration and user search.
 *
 * @author Vadim Rataiko
 * @version 1.2
 */
public class UserService implements IUserService {

    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves user with provided login
     *
     * @param login username for search
     * @return User object with provided login if user exist, null if doesn't
     */
    @Override
    public User get(String login) {
        return this.repository.getByUsername(login);
    }

    /**
     * Register user if all fields are filled, and username is unique
     *
     * @param user User object with user registration data
     */
    @Override
    public void signUp(User user) {
        this.validationForSignUp(user);
        this.repository.save(user);
    }

    /**
     * Checks if all fields are filled with data and username is unique
     * Throws IllegalArgument Exception if not
     *
     * @param user User object with user registration data
     */
    private void validationForSignUp(User user) {

        if (this.nullOrEmpty(user.getUsername()) || this.nullOrEmpty(user.getPassword())
                || this.nullOrEmpty(user.getFirstName()) || this.nullOrEmpty(user.getLastName())
                || this.nullOrEmpty(user.getPatronymic()) || this.get(user.getUsername()) != null) {
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
}
