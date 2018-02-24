package server.session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by ilamoskalev on 24.02.2018.
 */
public class Session implements Runnable {
    private final Socket socket;
    private final String docPath;

    public Session(Socket socket, String docPath){
        this.socket = socket;
        this.docPath = docPath;
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()){

        } catch (IOException e) {

        }

    }
}
