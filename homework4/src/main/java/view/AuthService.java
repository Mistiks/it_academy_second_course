package view;

import model.User;
import view.api.IAuthService;
import view.api.IUserService;

import java.util.Objects;

public class AuthService implements IAuthService {
    private final static AuthService instance = new AuthService();

    private final IUserService userService;

    private AuthService() {
        this.userService = UserService.getInstance();
    }

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

    public static AuthService getInstance() {
        return instance;
    }
}
