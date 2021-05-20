package com.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyBean {
    @Value("${user.name}")
    private String userName;

    @Countable
    public String greet(){
        return String.format("Welcome %s to Spring AOP explorations",userName);
    }

}
