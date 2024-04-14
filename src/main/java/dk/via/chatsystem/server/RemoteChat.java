package dk.via.chatsystem.server;

import dk.via.chatsystem.model.Message;
import dk.via.chatsystem.model.User;
import dk.via.chatsystem.shared.Chat;

import java.beans.PropertyChangeListener;

import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RemoteChat implements Chat {
    private final ArrayList<User> users;
    private final RemotePropertyChangeSupport<Message> support;

    public RemoteChat() {
        this.users = new ArrayList<>();
        this.support = new RemotePropertyChangeSupport<>();
    }

    @Override
    public void sendMessage(Message message) throws RemoteException {
        Logger.getInstance().logMessage(new User(message.getSentBy()), message.getContent(), message.getSentAt());
        support.firePropertyChange("receive_message", null,
                new Message(message.getContent(), message.getSentBy()));
    }

    @Override
    public void addUser(String username) throws RemoteException {
        var newUser = new User(username);
        this.users.add(newUser);
        support.firePropertyChange("receive_user", null,
                new Message("", newUser.getUsername()));
    }

    @Override
    public void removeUser(String username) throws RemoteException {
        var user = new User(username);
        this.users.remove(user);
        support.firePropertyChange("remove_user", null, new Message("", user.getUsername()));
    }

    @Override
    public ArrayList<User> getUsers() throws RemoteException {
        return this.users;
    }

    @Override
    public void addPropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException {
        this.support.addPropertyChangeListener(listener);
        System.out.println(listener);
    }

    @Override
    public void removePropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException {
        this.support.removePropertyChangeListener(listener);
    }
}
