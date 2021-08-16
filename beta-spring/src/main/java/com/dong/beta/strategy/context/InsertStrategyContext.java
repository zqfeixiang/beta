package com.dong.beta.strategy.context;

import com.dong.beta.strategy.InsertStrategy;
import com.dong.beta.strategy.enums.InsertTypeEnum;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InsertStrategyContext {

    @Autowired
    ApplicationContext applicationContext;

    public static Map<String, Class<InsertStrategy>> stringClassMap = Maps.newHashMap();

    public InsertStrategy getInsertStrategy(InsertTypeEnum insertTypeEnum){
        Class<InsertStrategy> insertStrategyClass = stringClassMap.get(insertTypeEnum.getFlag());
        if (insertStrategyClass == null) {
            throw new IllegalArgumentException("can not find strategy!!!");
        }
        return applicationContext.getBean(insertStrategyClass);
    }
}
