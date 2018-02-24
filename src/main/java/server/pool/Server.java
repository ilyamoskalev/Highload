package server.pool;

import server.config.Config;
import server.session.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
    private final Executor ex;
    private final Config config;

    public Server(Config config) {
        this.config = config;
        ex = Executors.newFixedThreadPool(config.getThreadLimit());
    }

    public void start() {
        try (final ServerSocket ss = new ServerSocket(config.getPort())){
            while (true) {
                ex.execute(new Session(ss.accept(), config.getDocumenRoot()));
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
