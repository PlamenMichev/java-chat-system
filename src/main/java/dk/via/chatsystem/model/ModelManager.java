package dk.via.chatsystem.model;

import dk.via.chatsystem.shared.Chat;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements Model {
    private final Chat client;
    private User currentUser;

    public ModelManager(Chat client) throws RemoteException {
        this.client = client;
    }

    @Override
    public void sendMessage(Message message) throws RemoteException {
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
    public void removeUser(String username) throws RemoteException {
        if (username == null || username.isBlank()) return;
        client.removeUser(username);

        if (username.equals(currentUser.getUsername())) {
            currentUser = null;
        }
    }

    @Override
    public void addPropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException {
        this.client.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException {
        this.client.removePropertyChangeListener(listener);
    }
}
