package com.dong.beta.strategy.service;

import com.dong.beta.strategy.enums.InsertTypeEnum;

public interface InsertService {
    void insert(InsertTypeEnum insertTypeEnum, String date);
}
