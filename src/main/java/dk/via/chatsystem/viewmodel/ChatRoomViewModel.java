package dk.via.chatsystem.viewmodel;

import dk.via.chatsystem.client.MessageType;
import dk.via.chatsystem.model.Message;
import dk.via.chatsystem.model.Model;
import dk.via.chatsystem.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ChatRoomViewModel implements PropertyChangeListener {
    private final Model client;
    private StringProperty message;
    private StringProperty chatBox;
    private ObservableList<User> users;

    public ChatRoomViewModel(Model client) {
        this.client = client;
        this.users = FXCollections.observableArrayList(client.getUsers());
        this.chatBox = new SimpleStringProperty("");
        this.message = new SimpleStringProperty("");
        client.addPropertyChangeListener(this);
    }

    public void sendMessage() {
        client.sendMessage(new Message(message.get(), this.client.getCurrentUser().getUsername()));
    }

    public void bindMessage(StringProperty property) {
        property.bindBidirectional(message);
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public String getCurrentUser() {
        return client.getCurrentUser().getUsername();
    }

    public void bindChatBox(StringProperty property) {
        property.bindBidirectional(chatBox);
    }

    public void updateChatBox(String message) {
        chatBox.set(chatBox.get() + message + "\n");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        var type = evt.getPropertyName();

        if (type.equals("receive_user")) {
            var user = (User) evt.getNewValue();
            if (this.users.stream().noneMatch(u -> u.getUsername().equals(user.getUsername())))
            {
                this.users.add(user);
                updateChatBox(user.getUsername() + " has joined the chat");
            }
        }

        if (type.equals("receive_message")) {
            var message = (Message) evt.getNewValue();
            updateChatBox(message.toString());
        }

        if (type.equals("remove_user")) {
            var user = (User) evt.getNewValue();
            this.users.remove(user);
            updateChatBox(user.getUsername() + " has left the chat");
        }
    }
}
