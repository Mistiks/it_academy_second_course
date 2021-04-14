package model.entity;

import java.time.LocalDateTime;

/**
 * Class which stores message from one user to another
 *
 * @version 1.0
 * @author Vadim Rataiko
 */
public class Message {

    /** Message sender */
    private String sender;

    /** Message recipient*/
    private String recipient;

    /** Message text */
    private String text;
    /** Time when message was sent*/
    LocalDateTime time;

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
     * Setter of sender field
     *
     * @param sender value which will be set
     */
    public void setSender(String sender) {
        this.sender = sender;
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
     * Setter of recipient field
     *
     * @param recipient value which will be set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
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
     * Setter of text field
     *
     * @param text value which will be set
     */
    public void setText(String text) {
        this.text = text;
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
     * Setter of time field
     *
     * @param time value which will be set
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}