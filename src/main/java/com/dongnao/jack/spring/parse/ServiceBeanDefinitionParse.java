package com.dongnao.jack.spring.parse;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/** 
 * @Description service标签的解析类 
 * @ClassName   ServiceBeanDefinitionParse 
 * @Date        2017年11月11日 下午9:33:59 
 * @Author      dn-jack 
 */

public class ServiceBeanDefinitionParse implements BeanDefinitionParser {
    private Class<?> beanClass;
    
    public ServiceBeanDefinitionParse(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
    
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        //spring会把这个beanClass进行实例化  BeanDefinitionNames??
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String intf = element.getAttribute("interface");
        String ref = element.getAttribute("ref");
        String protocol = element.getAttribute("protocol");
        
        if (intf == null || "".equals(intf)) {
            throw new RuntimeException("service intf 不能为空！");
        }
        if (ref == null || "".equals(ref)) {
            throw new RuntimeException("service ref 不能为空！");
        }
        //        if (protocol == null || "".equals(protocol)) {
        //            throw new RuntimeException("service protocol 不能为空！");
        //        }
        
        beanDefinition.getPropertyValues().addPropertyValue("intf", intf);
        beanDefinition.getPropertyValues().addPropertyValue("ref", ref);
        beanDefinition.getPropertyValues().addPropertyValue("protocol",
                protocol);
        parserContext.getRegistry().registerBeanDefinition("service" + ref
                + intf,
                beanDefinition);
        return beanDefinition;
    }
}
