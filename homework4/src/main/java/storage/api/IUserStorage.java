package storage.api;

import model.User;

/** Interface that provides api for access to user storage
 *
 * @author Ilya Shadryn
 * @author Vadim Rataiko
 * @version 1.0
 */
public interface IUserStorage {

    /**
     * Retrieves user from storage with provided login
     *
     * @param login login of user for search
     * @return user with provided login, null if user doesn't exist
     */
    User get(String login);

    /**
     * Adds user to storage
     *
     * @param user user for adding
     */
    void add(User user);

    /**
     * Removes user from database
     *
     * @param login username to delete in database
     */
    void deleteUser(String login);
}