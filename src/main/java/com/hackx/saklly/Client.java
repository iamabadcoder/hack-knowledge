package com.hackx.saklly;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * Created by 曹磊(Hackx) on 11/7/2017.
 * Email: caolei@mobike.com
 */
public class Client {
    public static String HOST = "localhost";
    public static int PORT = 8888;

    public static Bootstrap bootstrap = getBootstrap();
    public static Channel channel = getChannel(HOST, PORT);


    /**
     * 初始化Bootstrap
     *
     * @return
     */
    public static final Bootstrap getBootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new UserDecoder());
                pipeline.addLast("encoder", new UserEncoder());
                pipeline.addLast("handler", new ClientHandler());
            }
        });
        b.option(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    public static final Channel getChannel(String host, int port) {
        Channel channel = null;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            return null;
        }
        return channel;
    }

    public static void sendUser(User user) throws Exception {
        System.out.println(channel);
        if (channel != null) {
            channel.writeAndFlush(user).sync();
        } else {
            System.out.println("消息发送失败,连接尚未建立!");
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            Client.sendUser(new User("kevin.yang", 24));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
