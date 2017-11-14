package com.dongnao.jack.spring.parse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext(
                "mytest.xml");
        //        System.out.println(app);
        //        TestService tests = app.getBean(TestService.class);
        //        Test2Service tests2 = app.getBean(Test2Service.class);
        ////        System.out.println(tests);
        //        tests.eat("");
        //        tests2.sleep();
        //        tests.eat("xxxgrer");
    }
}
