package com.dongnao.jack.invoke;

import java.lang.reflect.Method;

import com.dongnao.jack.configBean.Reference;

public class Invocation {
    
    private Method method;
    
    private Object[] objs;
    
    private Invoke invoke;
    
    private Reference reference;
    
    public Method getMethod() {
        return method;
    }
    
    public void setMethod(Method method) {
        this.method = method;
    }
    
    public Object[] getObjs() {
        return objs;
    }
    
    public void setObjs(Object[] objs) {
        this.objs = objs;
    }
    
    public Reference getReference() {
        return reference;
    }
    
    public void setReference(Reference reference) {
        this.reference = reference;
    }
    
    public Invoke getInvoke() {
        return invoke;
    }
    
    public void setInvoke(Invoke invoke) {
        this.invoke = invoke;
    }
    
}
