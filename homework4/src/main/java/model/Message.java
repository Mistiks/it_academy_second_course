package model;

import java.time.LocalDateTime;

/**
 * Class which stores entered message from one user to another
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
public class Message {

    /** Message sender */
    private final String sender;

    /** Message recipient*/
    private final String recipient;

    /** Message text */
    private final String text;

    /** Time when message was sent*/
    private final LocalDateTime time;

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
}