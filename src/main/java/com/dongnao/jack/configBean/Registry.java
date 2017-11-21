package com.dongnao.jack.configBean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dongnao.jack.registry.BaseRegistry;
import com.dongnao.jack.registry.RedisRegistry;

public class Registry extends BaseConfigBean implements InitializingBean,
        ApplicationContextAware {
    
    public ApplicationContext application;
    
    private static Map<String, BaseRegistry> registryMap = new HashMap<String, BaseRegistry>();
    
    /** 
     * @Fields serialVersionUID TODO 
     */
    static {
        registryMap.put("redis", new RedisRegistry());
    }
    
    private static final long serialVersionUID = 45672141098765L;
    
    private String protocol;
    
    private String address;
    
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.application = applicationContext;
        
    }
    
    //    public Registry() {
    //    	registryMap.put("redis", new RedisRegistry());
    //    }
    
    public void afterPropertiesSet() throws Exception {
        
    }
    
    public String getProtocol() {
        return protocol;
    }
    
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public static Map<String, BaseRegistry> getRegistryMap() {
        return registryMap;
    }
    
    public static void setRegistryMap(Map<String, BaseRegistry> registryMap) {
        Registry.registryMap = registryMap;
    }
}
