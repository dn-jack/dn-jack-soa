package com.dongnao.jack.spring.parse;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/** 
 * @Description protocol标签的解析 
 * @ClassName   ProtocolBeanDefinitionParse 
 * @Date        2017年11月11日 下午9:30:35 
 * @Author      dn-jack 
 */

public class ProtocolBeanDefinitionParse implements BeanDefinitionParser {
    
    private Class<?> beanClass;
    
    public ProtocolBeanDefinitionParse(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
    
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        //spring会把这个beanClass进行实例化  BeanDefinitionNames??
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String name = element.getAttribute("name");
        String host = element.getAttribute("host");
        String port = element.getAttribute("port");
        String contextpath = element.getAttribute("contextpath");
        
        if (name == null || "".equals(name)) {
            throw new RuntimeException("protocol name 不能为空！");
        }
        if (host == null || "".equals(host)) {
            throw new RuntimeException("protocol host 不能为空！");
        }
        if (port == null || "".equals(port)) {
            throw new RuntimeException("protocol port 不能为空！");
        }
        
        beanDefinition.getPropertyValues().addPropertyValue("name", name);
        beanDefinition.getPropertyValues().addPropertyValue("host", host);
        beanDefinition.getPropertyValues().addPropertyValue("port", port);
        beanDefinition.getPropertyValues().addPropertyValue("contextpath",
                contextpath);
        parserContext.getRegistry().registerBeanDefinition("protocol" + host
                + port,
                beanDefinition);
        return beanDefinition;
    }
    
}
