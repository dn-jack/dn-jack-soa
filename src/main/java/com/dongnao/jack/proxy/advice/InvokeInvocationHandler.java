package com.dongnao.jack.proxy.advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.dongnao.jack.cluster.Cluster;
import com.dongnao.jack.configBean.Reference;
import com.dongnao.jack.invoke.Invocation;
import com.dongnao.jack.invoke.Invoke;

/** 
 * @Description InvokeInvocationHandler 这个是一个advice，在这个advice里面就进行了rpc的远程调用
 * rpc：http、rmi、netty
 *  
 * @ClassName   InvokeInvocationHandler 
 * @Date        2017年11月11日 下午10:14:51 
 * @Author      dn-jack 
 */

public class InvokeInvocationHandler implements InvocationHandler {
    
    private Invoke invoke;
    
    private Reference reference;
    
    public InvokeInvocationHandler(Invoke invoke, Reference reference) {
        this.invoke = invoke;
        this.reference = reference;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //在这个invoke里面最终要调用多个远程的provider
        System.out.print("已经获取到了代理实例，已经掉到了InvokeInvocationHandler.invoke");
        Invocation invocation = new Invocation();
        invocation.setMethod(method);
        invocation.setObjs(args);
        invocation.setReference(reference);
        invocation.setInvoke(invoke);
        //        String result = invoke.invoke(invocation);
        Cluster cluster = reference.getClusters().get(reference.getCluster());
        String result = cluster.invoke(invocation);
        return result;
    }
}
