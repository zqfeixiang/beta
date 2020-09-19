package com.dong.beta.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAop {

    @Pointcut("execution(* com.dong.beta.controller..*.*(..))")
    public void executeService(){

    }

    @Before("executeService()")
    public void doBefore(){

    }
}
