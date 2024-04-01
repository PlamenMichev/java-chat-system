package dk.via.chatsystem.model;

import java.util.Date;

public class Message {
    private String content;
    private String sentBy;
    private long sentAt;

    public Message(String content, String sentBy) {
        this.content = content;
        this.sentBy = sentBy;
        this.sentAt = new Date().getTime();
    }

    public String getContent() {
        return content;
    }

    public String getSentBy() {
        return sentBy;
    }

    public long getSentAt() {
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

        return content.equals(((Message)obj).content) && sentBy.equals(((Message)obj).sentBy);
    }
}
