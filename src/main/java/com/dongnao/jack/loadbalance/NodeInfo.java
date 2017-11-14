package com.dongnao.jack.loadbalance;

public class NodeInfo {
    private String host;
    
    private String port;
    
    private String contextpath;
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getPort() {
        return port;
    }
    
    public void setPort(String port) {
        this.port = port;
    }
    
    public String getContextpath() {
        return contextpath;
    }
    
    public void setContextpath(String contextpath) {
        this.contextpath = contextpath;
    }
}
