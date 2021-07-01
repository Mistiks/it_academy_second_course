package storage.api;

import model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * JPA specific extension of Repository for class Message
 *
 * @author Vadim Rataiko
 * @version 1.0.1
 */
public interface IMessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByRecipient(String recipient);
}
