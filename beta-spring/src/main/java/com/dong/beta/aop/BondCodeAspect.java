package com.dong.beta.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dong.beta.filter.ValuePostFilter;
import com.dong.beta.filter.ValuePreFilter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Aspect
@ConditionalOnProperty(name = "aspectOn.bondCodeAspect", matchIfMissing = true)
@Component
@Slf4j
public class BondCodeAspect {

    @Pointcut("execution(* com.dong.beta.controller..*.*(..))")
    public void webLog() {
    }

    @Around(value = "webLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object obj : args){
            JSON.toJSONString(obj, new ValuePreFilter(), SerializerFeature.WriteMapNullValue);
        }
        Object result = joinPoint.proceed(args);

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Type returnType = method.getGenericReturnType();
        byte[] bytes = JSON.toJSONBytes(result, new ValuePostFilter(), SerializerFeature.WriteMapNullValue);
        return JSON.parseObject(bytes, returnType);
    }
}
