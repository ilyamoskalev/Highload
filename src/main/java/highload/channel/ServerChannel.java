package highload.channel;

import highload.utils.Request;
import highload.utils.Responce;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;

public class ServerChannel extends ChannelInboundHandlerAdapter {
    @NotNull
    private String documentRoot;

    public ServerChannel(@NotNull String documentRoot) {
        this.documentRoot = documentRoot;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        final Request request = new Request((String) msg);
        ReferenceCountUtil.release(msg);
        final Responce responce = new Responce(documentRoot, request);
        final String resp = responce.getResponce();
        final ByteBuf respData = ctx.alloc().buffer(resp.length());
        respData.writeBytes(resp.getBytes());
        if (request.getMethod() != null) {
            final ChannelFuture future;
            if (request.getMethod().equals("HEAD") || responce.getFile() == null) {
                future = ctx.writeAndFlush(respData);
            } else {
                ctx.write(respData);
                future = ctx.writeAndFlush(new DefaultFileRegion(responce.getFile(), 0, responce.getFile().length()));
            }
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
