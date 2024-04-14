package dk.via.chatsystem.server;

import dk.via.chatsystem.model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
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

    public static void main(String[] args) throws IOException, AlreadyBoundException {
        var registry = LocateRegistry.createRegistry(1099);
        var calculator = new RemoteChat();
        var remote = UnicastRemoteObject.exportObject(calculator, 0);
        registry.bind("chat", remote);
        System.out.println("Server running");
    }
}
