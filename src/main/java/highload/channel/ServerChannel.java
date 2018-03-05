package highload.channel;

import io.netty.channel.ChannelInboundHandlerAdapter;
import org.jetbrains.annotations.NotNull;

public class ServerChannel extends ChannelInboundHandlerAdapter {
    @NotNull
    private String documentRoot;

    public ServerChannel(@NotNull String documentRoot) {
        this.documentRoot = documentRoot;
    }
}
