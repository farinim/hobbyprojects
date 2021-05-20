package com.demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class MySpringConfig {

    @Bean
    public MyBean myBean(){
        return new MyBean();
    }

    /*@Bean(destroyMethod = "destroy")
    public CounterAspect counterAspect(){
        return new CounterAspect();
    }*/

}
