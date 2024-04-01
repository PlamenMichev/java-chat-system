package dk.via.chatsystem.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Message {
    private String content;
    private String sentBy;
    private long sentAt;

    public Message(String content, String sentBy) {
        this.content = content;
        this.sentBy = sentBy;
        this.sentAt = new Date().getTime();
    }

    public Message(String content, String sentBy, long sentAt) {
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

    public long getSentAt() {
        return sentAt;
    }

    @Override
    public String toString() {
        Date date = new Date(this.sentAt);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = sdf.format(date);
        return '[' + formattedDate  + "] " + this.sentBy + ": " + this.content;
    }

    public String toDetailedString() {
        Date date = new Date(this.sentAt);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = sdf.format(date);
        return '[' + formattedDate  + "] " + this.sentBy + ": " + this.content;
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
