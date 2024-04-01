package dk.via.chatsystem.server;

import dk.via.chatsystem.model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private static ArrayList<User> users = new ArrayList<>();
    public static void addUser(User chatter) {
        users.add(chatter);
    }
    public static void removeUser(User chatter) {
        users.remove(chatter);
    }
    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        UDPBroadcaster broadcaster = new UDPBroadcaster("230.0.0.0", 8888);
        while(true) {
            System.out.println("Server is ready for input port 8080");
            Socket socket = serverSocket.accept();
            ChatCommunicator communicator = new ChatCommunicator(socket, broadcaster);
            Thread communicatorThread = new Thread(communicator);
            communicatorThread.start();
        }
    }
}
