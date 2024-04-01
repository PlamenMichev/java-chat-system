package dk.via.chatsystem.model;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public interface Model {
    void sendMessage(Message message);
    void addUser(String username) throws IOException;
    ArrayList<User> getUsers();
    void setCurrentUser(User user);
    User getCurrentUser();
    void removeUser(String username);
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
