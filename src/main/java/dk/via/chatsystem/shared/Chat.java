package dk.via.chatsystem.shared;
import dk.via.chatsystem.model.Message;
import dk.via.chatsystem.model.User;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Chat extends Remote {
    void sendMessage(Message message) throws RemoteException;
    void addUser(String username) throws RemoteException;
    void removeUser(String username) throws RemoteException;
    ArrayList<User> getUsers() throws RemoteException;
    void addPropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException;
    void removePropertyChangeListener(RemotePropertyChangeListener<Message> listener) throws RemoteException;
}
