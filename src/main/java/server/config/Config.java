package server.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ilamoskalev on 24.02.2018.
 */
public class Config {

    private static final String CONFIG_PATH = "/Users/ilamoskalev/Desktop/Highload/httpd.conf";
    private int port;
    private int threadLimit;
    private String documenRoot;

    public Config() throws IOException {
        final List<String> conf = Files.lines(Paths.get(CONFIG_PATH)).collect(Collectors.toList());
        for (String str : conf) {
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
                default:
                    throw new IOException("bad config");
            }
        }
    }


    public int getPort() {
        return port;
    }

    public int getThreadLimit() {
        return threadLimit;
    }

    public String getDocumenRoot() {
        return documenRoot;
    }
}
