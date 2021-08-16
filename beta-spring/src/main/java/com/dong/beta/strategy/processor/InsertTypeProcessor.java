package com.dong.beta.strategy.processor;

import com.dong.beta.strategy.InsertStrategy;
import com.dong.beta.strategy.annotation.InsertType;
import com.dong.beta.strategy.context.InsertStrategyContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InsertTypeProcessor implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> insertStrategyMap = applicationContext.getBeansWithAnnotation(InsertType.class);
        insertStrategyMap.forEach((k, v) -> {
            Class<InsertStrategy> insertStrategyClass = (Class<InsertStrategy>)v.getClass();
            String flag = insertStrategyClass.getAnnotation(InsertType.class).value().getFlag();
            InsertStrategyContext.stringClassMap.put(flag, insertStrategyClass);
        });
    }
}
