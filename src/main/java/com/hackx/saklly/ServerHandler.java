package com.hackx.saklly;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by 曹磊(Hackx) on 11/7/2017.
 * Email: caolei@mobike.com
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 请求的超时时间
     */
    private static final long TIMEOUT = 2*60*1000L;

    /**
     * cache的过期时间：60s
     */
    private static final long MILSECONDS = 1000*60;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        User user = (User)msg;
        ctx.channel().writeAndFlush(user);
    }

    /**
     * Close the connection when an exception is raised.
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        ctx.close();
    }
}
