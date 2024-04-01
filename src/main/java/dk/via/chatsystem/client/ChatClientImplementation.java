package dk.via.chatsystem.client;

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

public class ChatClientImplementation implements ChatClient {
    private static final String EXIT_JSON = """
    {"operator": "exit"}
    """; // JSON string to exit the chat

    private final Socket socket;
    private final PrintWriter output;
    private final BufferedReader input;
    private final PropertyChangeSupport support;

    public ChatClientImplementation(String host, int port) throws IOException {
        this.support = new PropertyChangeSupport(this);
        this.socket = new Socket(host, port);
        this.output = StreamFactory.createWriter(socket);
        this.input = StreamFactory.createReader(socket);
    }


    @Override
    public void sendMessage(String senderUsername, String messageContent, Date sentAt) {
        output.println(MessageType.NEW_MESSAGE + " " + senderUsername + " " +  sentAt + " " + messageContent);
        output.flush();
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

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void close() throws IOException {

    }
}
