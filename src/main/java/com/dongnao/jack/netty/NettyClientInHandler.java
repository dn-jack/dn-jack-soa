package com.dongnao.jack.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

public class NettyClientInHandler extends ChannelInboundHandlerAdapter {
    
    public StringBuffer message;
    
    public String sendMsg;
    
    public NettyClientInHandler(StringBuffer message, String sendMsg) {
        this.message = message;
        this.sendMsg = sendMsg;
    }
    
    @Override
    public boolean isSharable() {
        // TODO Auto-generated method stub
        return super.isSharable();
    }
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.handlerAdded(ctx);
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.handlerRemoved(ctx);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
        super.exceptionCaught(ctx, cause);
    }
    
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelRegistered(ctx);
    }
    
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelUnregistered(ctx);
    }
    
    /* 
     * @see 当我们连接成功以后会触发这个方法
     * 在这个方法里面完成消息的发送
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--------------channelActive-------------");
        ByteBuf encoded = ctx.alloc().buffer(4 * sendMsg.length());
        encoded.writeBytes(sendMsg.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
    
    /* 
     * @see 一旦服务端有消息过来，这个方法会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("------------------channelRead--------------------");
        ByteBuf result = (ByteBuf)msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        System.out.println("server response msg：" + new String(result1));
        message.append(new String(result1));
        result.release();
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelReadComplete(ctx);
    }
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        // TODO Auto-generated method stub
        super.userEventTriggered(ctx, evt);
    }
    
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx)
            throws Exception {
        // TODO Auto-generated method stub
        super.channelWritabilityChanged(ctx);
    }
    
    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress,
            ChannelPromise promise) throws Exception {
        // TODO Auto-generated method stub
        super.bind(ctx, localAddress, promise);
    }
    
    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
            SocketAddress localAddress, ChannelPromise promise)
            throws Exception {
        // TODO Auto-generated method stub
        super.connect(ctx, remoteAddress, localAddress, promise);
    }
    
    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise)
            throws Exception {
        // TODO Auto-generated method stub
        super.disconnect(ctx, promise);
    }
    
    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise)
            throws Exception {
        // TODO Auto-generated method stub
        super.close(ctx, promise);
    }
    
    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise)
            throws Exception {
        // TODO Auto-generated method stub
        super.deregister(ctx, promise);
    }
    
    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.read(ctx);
    }
    
    @Override
    public void write(ChannelHandlerContext ctx, Object msg,
            ChannelPromise promise) throws Exception {
        // TODO Auto-generated method stub
        super.write(ctx, msg, promise);
    }
    
    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.flush(ctx);
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
    
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
    
    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }
    
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    
}
