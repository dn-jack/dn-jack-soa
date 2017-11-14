package com.dongnao.jack.configBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Protocol extends BaseConfigBean implements InitializingBean,
        ApplicationContextAware {
    
    /** 
     * @Fields serialVersionUID TODO 
     */
    
    private static final long serialVersionUID = 667333278987L;
    
    private String name;
    
    private String port;
    
    private String host;
    
    private String contextpath;
    
    private static ApplicationContext application;
    
    public static ApplicationContext getApplication() {
        return application;
    }
    
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        // TODO Auto-generated method stub
        Protocol.application = applicationContext;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPort() {
        return port;
    }
    
    public void setPort(String port) {
        this.port = port;
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getContextpath() {
        return contextpath;
    }
    
    public void setContextpath(String contextpath) {
        this.contextpath = contextpath;
    }
    
    public void afterPropertiesSet() throws Exception {
    }
    
}
