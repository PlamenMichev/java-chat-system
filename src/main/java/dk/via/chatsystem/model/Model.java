package dk.via.chatsystem.model;

import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model {
    void sendMessage(Message message) throws RemoteException;
    void addUser(String username) throws IOException;
    ArrayList<User> getUsers();
    void setCurrentUser(User user);
    User getCurrentUser();
    void removeUser(String username) throws RemoteException;
    void addPropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException;
    void removePropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException;
}
