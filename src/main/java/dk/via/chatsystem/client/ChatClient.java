package dk.via.chatsystem.client;

import dk.via.chatsystem.model.Message;
import dk.via.chatsystem.model.User;

import java.beans.PropertyChangeListener;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public interface ChatClient extends Closeable {
    void sendMessage(Message message);
    void addUser(String username);
    ArrayList<User> getUsers() throws IOException;
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
