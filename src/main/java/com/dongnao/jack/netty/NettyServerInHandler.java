package com.dongnao.jack.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongnao.jack.configBean.Service;

public class NettyServerInHandler extends ChannelInboundHandlerAdapter {
    
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
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelActive(ctx);
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelInactive(ctx);
    }
    
    /* 
     * @see netty的客户端有消息过来的时候就会掉到这个方法
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        
        ByteBuf result = (ByteBuf)msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        String resultStr = new String(result1);
        System.out.println(resultStr);
        
        result.release();
        
        String response = invokeService(resultStr);
        
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        ctx.writeAndFlush(encoded);
        ctx.close();
    }
    
    private String invokeService(String param) {
        JSONObject requestparam = JSONObject.parseObject(param);
        //要从远程的生产者的spring容器中拿到对应的serviceid实例
        String serviceId = requestparam.getString("serviceId");
        String methodName = requestparam.getString("methodName");
        JSONArray paramTypes = requestparam.getJSONArray("paramTypes");
        //这个对应的方法参数
        JSONArray methodParamJa = requestparam.getJSONArray("methodParams");
        //这个就是反射的参数
        Object[] objs = null;
        if (methodParamJa != null) {
            objs = new Object[methodParamJa.size()];
            int i = 0;
            for (Object o : methodParamJa) {
                objs[i++] = o;
            }
        }
        
        //拿到spring的上下文
        ApplicationContext application = Service.getApplication();
        //服务层的实例
        Object serviceBean = application.getBean(serviceId);
        
        //这个方法的获取，要考虑到这个方法的重载
        Method method = getMethod(serviceBean, methodName, paramTypes);
        
        if (method != null) {
            
            Object result;
            try {
                result = method.invoke(serviceBean, objs);
                return result.toString();
            }
            catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        else {
            return "---------------------------------nosuchmethod-----------------------------";
        }
        return null;
    }
    
    private Method getMethod(Object bean, String methodName,
            JSONArray paramTypes) {
        
        Method[] methods = bean.getClass().getMethods();
        List<Method> retMethod = new ArrayList<Method>();
        
        for (Method method : methods) {
            //把名字和methodName入参相同的方法加入到list中来
            if (methodName.trim().equals(method.getName())) {
                retMethod.add(method);
            }
        }
        
        //如果大小是1就说明相同的方法只有一个
        if (retMethod.size() == 1) {
            return retMethod.get(0);
        }
        
        boolean isSameSize = false;
        boolean isSameType = false;
        jack: for (Method method : retMethod) {
            Class<?>[] types = method.getParameterTypes();
            
            if (types.length == paramTypes.size()) {
                isSameSize = true;
            }
            
            if (!isSameSize) {
                continue;
            }
            
            for (int i = 0; i < types.length; i++) {
                if (types[i].toString().contains(paramTypes.getString(i))) {
                    isSameType = true;
                }
                else {
                    isSameType = false;
                }
                if (!isSameType) {
                    continue jack;
                }
            }
            
            if (isSameType) {
                return method;
            }
        }
        return null;
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
