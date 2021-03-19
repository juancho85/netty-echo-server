package com.example;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;

public class EchoServerHandler1 extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(final ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        channelHandlerContext.executor().schedule(() -> channelHandlerContext.writeAndFlush("I'm\n"), 300, TimeUnit.MILLISECONDS);
        channelHandlerContext.executor().schedule(() -> channelHandlerContext.writeAndFlush("repeating:\n"), 600, TimeUnit.MILLISECONDS);
        channelHandlerContext.executor().schedule(() -> channelHandlerContext.writeAndFlush(String.format("%s%n", message)), 900, TimeUnit.MILLISECONDS);
    }
}
