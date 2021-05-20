package com.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CounterAspect implements DisposableBean {

    private Map<String,Integer> methodCounter = new HashMap<>();

    @Pointcut("@annotation(com.demo.Countable)")
    //@Pointcut("execution(* com.demo.MyBean.*(..))")
    //@Before("execution(* com.demo.MyBean.*(..))")
    public void countMethod(){
       // System.out.println("point cut reached");
    }

    @Around("countMethod()")
    public Object count( ProceedingJoinPoint joinPoint ) throws Throwable {
        Object retObj = joinPoint.proceed();
        String methodName = joinPoint.getSignature().getName();
        if(methodCounter.containsKey(methodName)){
            Integer current = methodCounter.get(methodName);
            current++;
            methodCounter.put(methodName,current);
            //System.out.println(methodName + "called " + current +" times");
        }else{
            methodCounter.put(methodName,1);
            //System.out.println(methodName + "called one time");
        }
        return retObj;
    }

    @Override
    public void destroy() {
        System.out.println("Terminating...");
        methodCounter.forEach((k,v) -> System.out.format("Method %s called %d times", k,v));
    }
}
