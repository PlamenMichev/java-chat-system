package dk.via.chatsystem.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface Model {
    void sendMessage(String senderUsername, String messageContent);
    void addUser(String username);
    ArrayList<User> getUsers();
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
