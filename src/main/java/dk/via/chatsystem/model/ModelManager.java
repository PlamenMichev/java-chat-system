package dk.via.chatsystem.model;

import dk.via.chatsystem.client.ChatClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ModelManager implements Model, PropertyChangeListener {
    private final ChatClient client;
    private final PropertyChangeSupport support;
    private User currentUser;

    public ModelManager(ChatClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
        client.addPropertyChangeListener(this);
    }

    @Override
    public void sendMessage(Message message) {
        client.sendMessage(message);
    }

    @Override
    public void addUser(String username) throws IOException {
        if (username == null || username.isBlank()) return;
        if (client.getUsers().stream().anyMatch(user -> user.getUsername().equals(username))) return;
        client.addUser(username);
    }

    @Override
    public ArrayList<User> getUsers() {
        try {
            return client.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }
}
