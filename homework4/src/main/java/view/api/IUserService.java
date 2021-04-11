package view.api;

import model.User;
import java.util.Collection;

public interface IUserService {
    User get(String login);
    void signUp(User user);
    Collection<User> getAll();
}
