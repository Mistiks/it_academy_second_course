package storage.api;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA specific extension of Repository for class User
 *
 * @author Vadim Rataiko
 * @version 1.0.1
 */
public interface IUserRepository extends JpaRepository<User, Integer> {

    User getByUsername(String login);
}