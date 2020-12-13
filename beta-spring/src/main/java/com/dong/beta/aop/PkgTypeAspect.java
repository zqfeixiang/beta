package com.dong.beta.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PkgTypeAspect {

    @Pointcut("within(com.dong.beta.controller.*)")
    public void matchType(){
    }

    @Before("matchType()")
    public void before(){
        System.out.println();
        System.out.println("++++++++++before");
    }
}
