package dk.via.chatsystem.client;

import dk.via.chatsystem.model.User;

import java.beans.PropertyChangeListener;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Date;

public interface ChatClient extends Closeable {
    void sendMessage(String senderUsername, String messageContent, Date sentAt);
    void addUser(String username);
    ArrayList<User> getUsers();
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
