package highload.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Request {
    private String path = null;
    private String method = null;


    public Request(String request) throws UnsupportedEncodingException {
        if (request != null && !request.isEmpty() && request.contains("HTTP")) {
            final String[] lines = request.split("\\n");
            final String[] words = lines[0].split("\\s+");
            final int queryPos = words[1].indexOf('?');
            if (queryPos != -1) {
                words[1] = words[1].substring(0, queryPos);
            }
            path = URLDecoder.decode(words[1], "UTF-8");
            System.out.println(path);
            if (path.contains("../")) {
                method = "FORBIDDEN";
            } else {
                method = words[0];
            }
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }
}
