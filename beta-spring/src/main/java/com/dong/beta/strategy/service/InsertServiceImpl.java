package com.dong.beta.strategy.service;

import com.dong.beta.strategy.InsertStrategy;
import com.dong.beta.strategy.context.InsertStrategyContext;
import com.dong.beta.strategy.enums.InsertTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InsertServiceImpl implements InsertService{

    private final InsertStrategyContext insertStrategyContext;

    public InsertServiceImpl(InsertStrategyContext insertStrategyContext) {
        this.insertStrategyContext = insertStrategyContext;
    }

    @Override
    public void insert(InsertTypeEnum insertTypeEnum, String date) {
        InsertStrategy insertStrategy = insertStrategyContext.getInsertStrategy(insertTypeEnum);
        insertStrategy.doInsert();
    }
}
