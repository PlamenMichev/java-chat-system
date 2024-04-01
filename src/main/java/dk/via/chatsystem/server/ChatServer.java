package dk.via.chatsystem.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        UDPBroadcaster broadcaster = new UDPBroadcaster("127.0.0.1", 8080);
        while(true) {
            System.out.println("Server is ready for input port 8080");
            Socket socket = serverSocket.accept();
            ChatCommunicator communicator = new ChatCommunicator(socket, broadcaster);
            Thread communicatorThread = new Thread(communicator);
            communicatorThread.start();
        }
    }
}
