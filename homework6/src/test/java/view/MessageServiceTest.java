package view;

import model.Message;
import model.User;
import org.junit.jupiter.api.Test;
import storage.api.IMessageRepository;
import storage.api.IUserRepository;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for MessageService object
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
public class MessageServiceTest {

    /** Instance of IMessageRepository interface */
    private IMessageRepository repository;

    /** Instance of UserService class */
    private UserService userService;

    /** Instance of MessageService class */
    private MessageService messageService;

    /** Instance of IUserRepository interface */
    private IUserRepository userRepository;

    /**
     * Default constructor with parameters
     *
     * @param repository Instance of IMessageRepository interface
     * @param userService Instance of UserService class
     * @param messageService Instance of MessageService class
     * @param userRepository Instance of IUserRepository interface
     */
    public MessageServiceTest(IMessageRepository repository, UserService userService,
                              MessageService messageService, IUserRepository userRepository) {
        this.repository = repository;
        this.userService = userService;
        this.messageService = messageService;
        this.userRepository = userRepository;
    }

    /**
     * Testing all functionality from class
     * User "test" shouldn't have messages before starting test.
     */
    @Test
    public void classTest() {
        User user = new User("test", "test", "test", "test", "test", "10/10/2010");
        userService.signUp(user);
        Message message = new Message("test", "test", "test", LocalDateTime.now());
        messageService.addMessage(message);
        assertEquals(1, messageService.get(user).size());
        repository.deleteByRecipient("test");
        assertEquals(0, messageService.get(user).size());
        userRepository.deleteByUsername("test");
    }
}