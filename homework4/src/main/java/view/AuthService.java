package view;

import model.User;
import view.api.IAuthService;
import view.api.IUserService;
import java.util.Objects;

/**
 * Class-implementation if IAuthService interface. Checks if user has
 * right to use application
 *
 * @author Ilya Shadryn
 * @version 1.0
 */
public class AuthService implements IAuthService {

    /** Instance of AuthService object */
    private final static AuthService instance = new AuthService();

    /** Instance of IUserService interface */
    private final IUserService userService;

    /** Private constructor that defines implementation of IUserService interface */
    private AuthService() {
        this.userService = UserService.getInstance();
    }

    /**
     * Checks if user with provided login and password exists
     *
     * @param login user's login
     * @param password user's password
     * @return User object that has same login and password, null if user doesn't exist
     */
    @Override
    public User authentication(String login, String password) {
        User user = this.userService.get(login);

        if(user == null){
            return null;
        }

        if(!Objects.equals(user.getPassword(), password)){
            return null;
        }

        return user;
    }

    /**
     * Receives instance of AuthService object
     *
     * @return AuthService object
     */
    public static AuthService getInstance() {
        return instance;
    }
}
