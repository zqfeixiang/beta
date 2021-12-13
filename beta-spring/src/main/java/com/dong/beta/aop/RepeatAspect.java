package com.dong.beta.aop;

import com.alibaba.fastjson.JSON;
import com.dong.beta.annotation.RepeatSubmit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dzq
 * @date 2021/12/13 1:54 下午
 * 通过@RepeatSubmit注解实现简单的防止重复提交
 */
@Component
@Aspect
@Slf4j
@Order(value = 10)
public class RepeatAspect {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Pointcut(value = "@annotation(com.dong.beta.annotation.RepeatSubmit)")
    public void repeatCheck() {

    }

    @Around(value = "repeatCheck()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===Check request is repeat or not===");
        long startTime = System.currentTimeMillis();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RepeatSubmit repeatSubmit = method.getAnnotation(RepeatSubmit.class);
        String userId = "dong";
        String key = method.getName() + userId;

        try {
            Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(key, "userInfo", repeatSubmit.limit(), TimeUnit.SECONDS);
            Assert.isTrue(ifAbsent, "Do not send repeat request in a short time!");

            String name = joinPoint.getSignature().getName();
            log.info("===Method {} start to execute===", name);
            Object result = joinPoint.proceed();
            log.info("===Method {} end, result{}, consuming {}", name, JSON.toJSONString(result), (System.currentTimeMillis() - startTime));
            return result;
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
//            redisTemplate.delete(key);
        }
        return null;
    }
}
