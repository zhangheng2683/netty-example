package com.zh.client.handler;

import com.zh.protocol.response.LoginResponsePacket;
import com.zh.session.Session;
import com.zh.util.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author zh2683
 */
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        System.out.println(new Date() + ": 客户端开始登录");
//
        // 创建登录对象
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId((int)Math.floor(Math.random() * 10));
//        loginRequestPacket.setUsername("zh");
//        loginRequestPacket.setPassword("pwd");
////        // 写数据
//        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();


        if (loginResponsePacket.isSuccess()) {
            System.out.println(new Date() + ": 客户端登录成功, userId为:" + loginResponsePacket.getUserId());
//            LoginUtil.markAsLogin(ctx.channel());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
//        // TODO 发送1000次数据，服务端会出现数据包不完整的问题，就是粘包。。此时服务端需要拆包
//        for (int i = 0 ; i < 1000 ; i++) {
//            //1. 获取数据
//            ByteBuf buffer = getByteBuf(ctx);
//
//            //2. 写数据
//            ctx.channel().writeAndFlush(buffer);
//        }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = "你好".getBytes(Charset.forName("utf-8"));

        buffer.writeBytes(bytes);

        return buffer;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端链接被关闭");
    }
}
