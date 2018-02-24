package server;

import server.config.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            final Config config = new Config();
            try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
                while(true){
                    final Socket newSocket = serverSocket.accept();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
