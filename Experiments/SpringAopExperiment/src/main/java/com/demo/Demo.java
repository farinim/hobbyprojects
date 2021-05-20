package com.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Demo {

    public static void main(String[] args){
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfig.class);
        MyBean myBean = context.getBean(MyBean.class);

        for(int i=1;i<5; i++) {
            System.out.println(myBean.greet());
        }
        context.close();
    }
}
