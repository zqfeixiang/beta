package com.dong.beta.strategy;

import com.dong.beta.strategy.annotation.InsertType;
import com.dong.beta.strategy.enums.InsertTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@InsertType(InsertTypeEnum.INSERT_TWO)
public class InsertTwoStrategy implements InsertStrategy{
    @Override
    public void doInsert() {
        log.info("insert two...");
    }
}
