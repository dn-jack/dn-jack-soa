package com.dongnao.jack.configBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dongnao.jack.registry.BaseRegistryDelegate;

public class Service extends BaseConfigBean implements InitializingBean,
        ApplicationContextAware {
    
    /** 
     * @Fields serialVersionUID TODO 
     */
    
    private static final long serialVersionUID = 2300087543452L;
    
    private String intf;
    
    private String ref;
    
    private String protocol;
    
    private static ApplicationContext application;
    
    public static ApplicationContext getApplication() {
        return application;
    }
    
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        // TODO Auto-generated method stub
        Service.application = applicationContext;
    }
    
    public String getIntf() {
        return intf;
    }
    
    public void setIntf(String intf) {
        this.intf = intf;
    }
    
    public String getRef() {
        return ref;
    }
    
    public void setRef(String ref) {
        this.ref = ref;
    }
    
    public void afterPropertiesSet() throws Exception {
        BaseRegistryDelegate.registry(ref, application);
        
        //        RedisApi.publish("channel" + ref, "这个内容要跟redis里面的节点容易一直");
    }
    
    public String getProtocol() {
        return protocol;
    }
    
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
