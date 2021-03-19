package com.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class EchoServerHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(final ChannelHandlerContext channelHandlerContext, Object message) {
        ByteBuf in = Unpooled.copiedBuffer((String) message, CharsetUtil.UTF_8);
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        channelHandlerContext.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
