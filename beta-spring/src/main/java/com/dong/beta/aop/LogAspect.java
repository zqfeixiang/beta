package com.dong.beta.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    // 第一个*代表返回类型不限
    // 第二个*代表所有类
    // 第三个*代表所有方法
    // (..) 代表参数不限
    @Pointcut("execution(public * com.dong.beta.controller.*.*(..))")
    @Order(1)
    public void pointCut(){};

    @Pointcut("@annotation(com.dong.beta.annotation.ControllerWebLog)")
    @Order(2) // Order 代表优先级，数字越小优先级越高
    public void annoationPoint(){};

     @Before(value = "annoationPoint() || pointCut()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("=====================================================");
        logger.info("请求来源： =》" + request.getRemoteAddr());
        logger.info("请求URL：" + request.getRequestURL().toString());
        logger.info("请求方式：" + request.getMethod());
        logger.info("响应方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数：" + Arrays.toString(joinPoint.getArgs()));
        startTime.set(System.currentTimeMillis());
    }

    // 定义需要匹配的切点表达式，同时方法必须带有参数
    @Around("pointCut() && args(arg)")
    public Object around(ProceedingJoinPoint pjp, String arg) throws Throwable{
        logger.info("方法环绕start...around name:{}", arg);
        Object result = null;
        try{
            result = pjp.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
        logger.info("方法环绕end...around");
        return result;
    }

    @AfterReturning(value="pointCut()",returning = "rst")
    public void afterRunning(Object rst){
        if(startTime.get() == null){
            startTime.set(System.currentTimeMillis());
        }
        logger.info("方法执行完执行...afterRunning");
        logger.info("耗时（毫秒）：" +  (System.currentTimeMillis() - startTime.get()));
        logger.info("返回数据：{}", rst);
        logger.info("==========================================>");
    }

    @AfterThrowing("within(com.dong.beta.controller.*Controller)")
    public void afterThrowing(){
        System.out.println("异常出现之后...afterThrowing");
    } 
}