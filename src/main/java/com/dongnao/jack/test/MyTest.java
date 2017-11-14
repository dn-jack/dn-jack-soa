package com.dongnao.jack.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext(
                "mytest.xml");
        //        TestService tests = app.getBean(TestService.class);
        //        tests.eat("");
        
        UserService userservice = app.getBean(UserService.class);
        userservice.eat("xxxxx");
    }
    
}
