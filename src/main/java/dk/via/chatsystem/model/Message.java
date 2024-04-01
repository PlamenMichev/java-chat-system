package dk.via.chatsystem.model;

import java.util.Date;

public class Message {
    private String content;
    private String sentBy;
    private Date sentAt;

    public Message(String content, String sentBy, Date sentAt) {
        this.content = content;
        this.sentBy = sentBy;
        this.sentAt = sentAt;
    }

    public String getContent() {
        return content;
    }

    public String getSentBy() {
        return sentBy;
    }

    public Date getSentAt() {
        return sentAt;
    }

    @Override
    public String toString() {
        return sentBy + ": " + content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Message)) {
            return false;
        }

        return content.equals(((Message)obj).content) && sentBy.equals(((Message)obj).sentBy) && sentAt.equals(((Message)obj).sentAt);
    }
}
