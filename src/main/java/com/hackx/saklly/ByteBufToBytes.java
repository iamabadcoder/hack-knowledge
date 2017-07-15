package com.hackx.saklly;

import io.netty.buffer.ByteBuf;

/**
 * Created by 曹磊(Hackx) on 11/7/2017.
 * Email: caolei@mobike.com
 */
public class ByteBufToBytes {

    public byte[] read(ByteBuf datas) {
        byte[] bytes = new byte[datas.readableBytes()];
        datas.readBytes(bytes);
        return bytes;
    }
}