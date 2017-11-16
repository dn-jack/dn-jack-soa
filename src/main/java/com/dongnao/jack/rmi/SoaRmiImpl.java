package com.dongnao.jack.rmi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongnao.jack.configBean.Service;

/** 
 * @Description rmi的实现类，负责rmi的调用 ,这个是生产者端使用的类
 * @ClassName   SoaRmiImpl 
 * @Date        2017年11月16日 下午9:57:24 
 * @Author      dn-jack 
 */

public class SoaRmiImpl extends UnicastRemoteObject implements SoaRmi {
    
    protected SoaRmiImpl() throws RemoteException {
        super();
    }
    
    /** 
     * @Fields serialVersionUID TODO 
     */
    
    private static final long serialVersionUID = 14555883254693549L;
    
    public String invoke(String param) throws RemoteException {
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
    
}
