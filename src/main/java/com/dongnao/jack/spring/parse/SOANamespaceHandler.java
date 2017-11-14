package com.dongnao.jack.spring.parse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.dongnao.jack.configBean.Protocol;
import com.dongnao.jack.configBean.Reference;
import com.dongnao.jack.configBean.Registry;
import com.dongnao.jack.configBean.Service;

public class SOANamespaceHandler extends NamespaceHandlerSupport {
    
    public void init() {
        registerBeanDefinitionParser("registry",
                new RegistryBeanDefinitionParse(Registry.class));
        registerBeanDefinitionParser("protocol",
                new ProtocolBeanDefinitionParse(Protocol.class));
        registerBeanDefinitionParser("reference",
                new ReferenceBeanDifinitionParse(Reference.class));
        registerBeanDefinitionParser("service", new ServiceBeanDefinitionParse(
                Service.class));
        
    }
    
}
