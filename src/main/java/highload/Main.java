package highload;

import highload.config.Config;
import highload.server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            final Config config = new Config();
            final Server server = new Server(config);
            server.run();
        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
