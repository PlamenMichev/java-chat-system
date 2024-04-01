package dk.via.chatsystem.server;


import dk.via.chatsystem.client.MessageType;
import dk.via.chatsystem.model.User;
import dk.via.chatsystem.socket.StreamFactory;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ChatCommunicator implements Runnable {
    private final Socket socket;
    private final UDPBroadcaster broadcaster;

    public ChatCommunicator(Socket socket, UDPBroadcaster broadcaster) {
        this.socket = socket;
        this.broadcaster = broadcaster;
    }

    private void communicate() throws IOException {
        try {
            BufferedReader input = StreamFactory.createReader(socket);
            PrintWriter output = StreamFactory.createWriter(socket);
            while (true) {
                String message = input.readLine();
                if (message == null) break;

                String command = message.split(" ")[0];

                switch (command) {
                    case MessageType.EXIT:
                        return;
                    case MessageType.NEW_USER:
                        User newUserUsername = new User(message.split(" ")[1]);
                        if (ChatServer.getUsers().contains(newUserUsername)) {
                            break;
                        }

                        ChatServer.addUser(newUserUsername);
                        broadcaster.broadcast(MessageType.NEW_USER + " " + newUserUsername);
                        break;
                    case MessageType.GET_USERS:
                        ArrayList<User> chatters = ChatServer.getUsers();
                        for (User chatter : chatters) {
                            output.println(chatter.toString());
                        }

                        output.println("END");
                        break;
                    case MessageType.NEW_MESSAGE:
                        // Format: SEND_MESSAGE <message> <sender> <timestamp>
                        String[] parts = message.split(" ", 4);
                        String sender = parts[1];
                        String content = parts[3];
                        String timestamp = parts[2];
                        Logger.getInstance().logMessage(new User(sender), content, Long.parseLong(timestamp));
                        broadcaster.broadcast(message);
                        break;
                }
                output.flush();
            }
        } finally {
            socket.close();
        }
    }

    @Override
    public void run() {
        try {
            communicate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}