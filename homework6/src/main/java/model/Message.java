package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class which stores entered message from one user to another
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
@Entity(name = "Message")
@Table(name = "messages", schema = "homework3")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** Message sender */
    @Column(name = "sender", nullable = false)
    private  String sender;

    /** Message recipient*/
    @Column(name = "recipient", nullable = false)
    private  String recipient;

    /** Message text */
    @Column(name = "text", nullable = false)
    private  String text;

    /** Time when message was sent*/
    @Column(name = "time" , nullable = false)
    private  LocalDateTime time;

    /**
     * Constructor with parameters
     *
     * @param sender Message sender
     * @param recipient Message recipient
     * @param text Message text
     * @param time Time when message was sent
     */
    public Message(String sender, String recipient, String text, LocalDateTime time) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.time = time;
    }

    /** Default constructor without parameters */
    public Message() {}

    /**
     * Getter of sender field
     *
     * @return String with sender value
     */
    public String getSender() {
        return sender;
    }

    /**
     * Getter of recipient field
     *
     * @return String with recipient value
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Getter of text field
     *
     * @return String with text value
     */
    public String getText() {
        return text;
    }

    /**
     * Getter of time field
     *
     * @return LocalDateTime with time value
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Gets id of message
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for id field
     *
     * @param id value to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter for sender field
     *
     * @param sender value to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Setter for recipient field
     *
     * @param recipient value to set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Setter for text field
     *
     * @param text value to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Setter for time field
     *
     * @param time value to set
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}