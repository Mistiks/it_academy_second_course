package core.entity;

import java.time.LocalDateTime;

public class Message {
    private String sender;
    private String recipient;
    private String text;
    LocalDateTime time;

    public Message(String sender, String recipient, String text, LocalDateTime time) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
