package com.dong.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
@Slf4j
public class DLockAop {

    @Resource
    DLockUtils dLockUtils;

    @Pointcut("@annotation(dLock)")
    public void pointCut(DLock dLock){
        log.info("pointcut");
    }

    @Around("pointCut(dLock)")
    public Object doAround(ProceedingJoinPoint joinPoint, DLock dLock) {
        try {
            return dLockUtils.lock(dLock.value(), () -> {
                try {
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
