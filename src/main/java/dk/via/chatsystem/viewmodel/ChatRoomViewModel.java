package dk.via.chatsystem.viewmodel;

import dk.via.chatsystem.client.MessageType;
import dk.via.chatsystem.model.Model;
import dk.via.chatsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ChatRoomViewModel implements PropertyChangeListener {
    private final Model client;
    private ObservableList<User> users;

    public ChatRoomViewModel(Model client) {
        this.client = client;
        this.users = FXCollections.observableArrayList(client.getUsers());
        client.addPropertyChangeListener(this);
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        var type = evt.getPropertyName();

        if (type.equals(MessageType.NEW_USER)) {
            var user = (User) evt.getNewValue();
            System.out.println("New user: " + user.getUsername());
            if (this.users.stream().noneMatch(u -> u.getUsername().equals(user.getUsername())))
            {
                this.users.add(user);
            }
        }
    }
}
