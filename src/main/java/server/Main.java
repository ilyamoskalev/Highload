package server;

import server.config.Config;
import server.pool.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            final Config config = new Config();
                final Server server = new Server(config);
                server.start();

        } catch (IOException e) {
            System.out.println("Bad config");
        }
    }

}
