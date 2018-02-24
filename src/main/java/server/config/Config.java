package server.config;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {

    private static final String CONFIG_PATH = "/Users/ilamoskalev/Desktop/Highload/httpd.conf";
    private int port;
    private int threadLimit;
    @NotNull
    private String documenRoot;

    public Config() throws IOException, NumberFormatException {
        Files.lines(Paths.get(CONFIG_PATH)).forEach(str -> {
            final String[] value = str.split(" ");
            switch (value[0]) {
                case "listen":
                    port = Integer.parseInt(value[1]);
                    break;
                case "thread_limit":
                    threadLimit = Integer.parseInt(value[1]);
                    break;
                case "document_root":
                    documenRoot = value[1];
                    break;
            }
        });
    }


    public int getPort() {
        return port;
    }

    public int getThreadLimit() {
        return threadLimit;
    }

    @NotNull
    public String getDocumenRoot() {
        return documenRoot;
    }
}
