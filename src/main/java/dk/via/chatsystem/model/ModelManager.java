package dk.via.chatsystem.model;

import dk.via.chatsystem.client.ChatClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ModelManager implements Model, PropertyChangeListener {
    private final ChatClient client;
    private final PropertyChangeSupport support;

    public ModelManager(ChatClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
        client.addPropertyChangeListener(this);
    }

    @Override
    public void sendMessage(String senderUsername, String messageContent) {
        client.sendMessage(senderUsername, messageContent, new Date());
    }

    @Override
    public void addUser(String username) {

    }

    @Override
    public ArrayList<User> getUsers() {
        return null;
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
