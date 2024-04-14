package dk.via.chatsystem.viewmodel;

import dk.via.chatsystem.model.Message;
import dk.via.chatsystem.model.Model;
import dk.via.chatsystem.model.User;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatRoomViewModel extends UnicastRemoteObject implements RemotePropertyChangeListener<Message> {
    private final Model client;
    private StringProperty message;
    private StringProperty chatBox;
    private ObservableList<User> users;

    public ChatRoomViewModel(Model client) throws RemoteException {
        this.client = client;
        this.users = FXCollections.observableArrayList(client.getUsers());
        this.chatBox = new SimpleStringProperty("");
        this.message = new SimpleStringProperty("");
        try {
            this.client.addPropertyChangeListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        try {
            client.sendMessage(new Message(message.get(), this.client.getCurrentUser().getUsername()));
        } catch (Exception e) {
            return;
        }
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
    public void propertyChange(RemotePropertyChangeEvent<Message> evt) {
        var type = evt.getPropertyName();

        if (type.equals("receive_user")) {
            var userMessageModel = (Message) evt.getNewValue();
            if (this.users.stream().noneMatch(u -> u.getUsername().equals(userMessageModel.getSentBy())))
            {
                this.users.add(new User(userMessageModel.getSentBy()));
                updateChatBox(userMessageModel.getSentBy() + " has joined the chat");
            }
        }

        if (type.equals("receive_message")) {
            var message = (Message) evt.getNewValue();
            updateChatBox(message.toString());
        }

        if (type.equals("remove_user")) {
            var userMessageModel = (Message) evt.getNewValue();
            var user = this.users.stream().filter(u -> u.getUsername().equals(userMessageModel.getSentBy())).findFirst().orElse(null);
            this.users.remove(user);
            updateChatBox(userMessageModel.getSentBy() + " has left the chat");
        }
    }
}
