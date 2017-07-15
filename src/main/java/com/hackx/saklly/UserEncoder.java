package com.hackx.saklly;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by 曹磊(Hackx) on 11/7/2017.
 * Email: caolei@mobike.com
 */
public class UserEncoder extends MessageToByteEncoder<User> {

    @Override
    protected void encode(ChannelHandlerContext ctx, User user, ByteBuf out) throws Exception {
        byte[] datas = ByteObjConverter.objectToByte(user);
        out.writeBytes(datas);
        ctx.flush();
    }
}
