package dk.via.chatsystem.model;

import java.util.ArrayList;

public class ChatRoom {
    private String name;
    private ArrayList<Message> messages;
    private ArrayList<User> users;

    public ChatRoom(String name) {
        this.name = name;
        messages = new ArrayList<>();
        users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof ChatRoom)) {
            return false;
        }

        return name.equals(((ChatRoom)obj).name);
    }
}
