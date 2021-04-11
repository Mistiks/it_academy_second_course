package view;

import model.User;
import storage.UserStorage;
import storage.api.IUserStorage;
import view.api.IUserService;

import java.util.Collection;

public class UserService implements IUserService {
    private final static UserService instance = new UserService();

    private final IUserStorage userStorage;

    private UserService() {
        this.userStorage = UserStorage.getInstance();
    }

    @Override
    public User get(String login) {
        return this.userStorage.get(login);
    }

    @Override
    public void signUp(User user) {
        this.validationForSignUp(user);
        this.userStorage.add(user);
    }

    private void validationForSignUp(User user){

        if (this.nullOrEmpty(user.getUsername())){
            throw new IllegalArgumentException();
        }

        if (this.nullOrEmpty(user.getPassword())){
            throw new IllegalArgumentException();
        }

        if (this.nullOrEmpty(user.getFirstName())){
            throw new IllegalArgumentException();
        }

        if (this.nullOrEmpty(user.getLastName())){
            throw new IllegalArgumentException();
        }

        if (this.nullOrEmpty(user.getPatronymic())){
            throw new IllegalArgumentException();
        }

        if (this.nullOrEmpty(user.getDateOfBirthString())){
            throw new IllegalArgumentException();
        }
    }

    private boolean nullOrEmpty(String val){
        return val == null || val.isEmpty();
    }

    @Override
    public Collection<User> getAll() {
        return this.userStorage.getAll();
    }

    public static UserService getInstance() {
        return instance;
    }
}
