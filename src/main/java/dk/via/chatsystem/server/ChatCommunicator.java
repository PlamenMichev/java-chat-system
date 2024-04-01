package dk.via.chatsystem.server;


import dk.via.chatsystem.socket.StreamFactory;

import java.io.*;
import java.net.Socket;

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