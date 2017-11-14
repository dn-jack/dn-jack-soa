package com.dongnao.jack.configBean;

import java.io.Serializable;

public abstract class BaseConfigBean implements Serializable {
    
    /** 
     * @Fields serialVersionUID TODO 
     */
    
    private static final long serialVersionUID = 12345673642257L;
    
    public String id;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
}
