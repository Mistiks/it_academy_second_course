package view.api;

import model.User;

public interface IAuthService {
    User authentication(String login, String password);
}
