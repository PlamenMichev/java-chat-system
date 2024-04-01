package dk.via.chatsystem.client;

import dk.via.chatsystem.model.Message;
import dk.via.chatsystem.model.User;
import dk.via.chatsystem.socket.StreamFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Platform;

public class ChatClientImplementation implements ChatClient {
    private static final String EXIT_JSON = """
    {"operator": "exit"}
    """; // JSON string to exit the chat

    private final Socket socket;
    private final PrintWriter output;
    private final BufferedReader input;
    private final PropertyChangeSupport support;
    private final MessageListener listener;


    public ChatClientImplementation(String host, int port) throws IOException {
        this.support = new PropertyChangeSupport(this);
        this.socket = new Socket(host, port);
        this.output = StreamFactory.createWriter(socket);
        this.input = StreamFactory.createReader(socket);

        this.listener = new MessageListener(this, "230.0.0.0", 8888);
        Thread thread = new Thread(listener);
        thread.start();
    }


    @Override
    public void sendMessage(Message message) {
        output.println(MessageType.NEW_MESSAGE + " " + message.getSentBy() +  " " + message.getSentAt() + " " +  message.getContent());
        output.flush();
    }

    @Override
    public void addUser(String username) {
        output.println(MessageType.NEW_USER + " " + username);
        output.flush();
    }

    @Override
    public ArrayList<User> getUsers() throws IOException {
        output.println(MessageType.GET_USERS);
        output.flush();
        ArrayList<User> users = new ArrayList<>();
        while (true) {
            String chatter = input.readLine();
            if (chatter.equals("END")) break;
            users.add(new User(chatter));
        }

        return users;
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
    public void close() throws IOException {
        output.println(EXIT_JSON);
        output.flush();
        socket.close();
        listener.close();
    }

    public void receiveBroadcast(String message) {
        Platform.runLater(() -> {
            var messageType = message.split(" ")[0];

            switch (messageType) {
                case MessageType.NEW_MESSAGE -> {
                    String[] parts = message.split(" ", 4);
                    String sender = parts[1];
                    String content = parts[3];
                    String timestamp = parts[2];

                    support.firePropertyChange("receive_message", null, new Message(content, sender));
                }
                case MessageType.NEW_USER -> {
                    var username = message.split(" ")[1];
                    support.firePropertyChange("receive_user", null, new User(username));
                }
            }
        });
    }
}
