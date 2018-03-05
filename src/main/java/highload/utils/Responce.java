package highload.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Responce {
    @NotNull
    private String documentRoot;
    @NotNull
    private Request request;
    @Nullable
    private File file;
    private static final Map<String, String> CONTENT_TYPE = new HashMap<>();

    static {
        CONTENT_TYPE.put("html", "text/html");
        CONTENT_TYPE.put("css", "text/css");
        CONTENT_TYPE.put("js", "application/javascript");
        CONTENT_TYPE.put("jpeg", "image/jpeg");
        CONTENT_TYPE.put("jpg", "image/jpeg");
        CONTENT_TYPE.put("png", "image/png");
        CONTENT_TYPE.put("gif", "image/gif");
        CONTENT_TYPE.put("swf", "application/x-shockwave-flash");
        CONTENT_TYPE.put("plain", "text/plain");
    }

    public Responce(@NotNull String documentRoot, @NotNull Request request) {
        this.documentRoot = documentRoot;
        this.request = request;
        this.file = null;
    }

    public String getResponce() {
        if (request.getMethod() != null && (request.getMethod().equals("GET") || request.getMethod().equals("HEAD"))) {
            Path path = Paths.get(documentRoot + request.getPath());
            if (!Files.exists(path)) {
                return ResponceBuilder.notFound();
            }
            if (Files.isDirectory(path)) {
                path = Paths.get(documentRoot + request.getPath() + "/index.html");
                if (!Files.exists(path)) {
                    return ResponceBuilder.forbidden();
                }
            }
            file = new File(path.toString());
            return ResponceBuilder.okWithContent(file.length(), CONTENT_TYPE.get(file.getName().substring(file.getName().lastIndexOf('.') + 1)));
        } else if (request.getMethod() != null && request.getMethod().equals("FORBIDDEN")) {
            return ResponceBuilder.forbidden();
        } else {
            return ResponceBuilder.notAllowed();
        }
    }

    @Nullable
    public File getFile() {
        return file;
    }

}

