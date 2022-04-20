package com.dong.beta.factoryStrategy.i;

import com.dong.beta.factoryStrategy.model.Stock;

import java.util.List;

/**
 * @author dzq
 * @date 2022/4/19 8:58 下午
 */
public interface Strategy {
    List<Stock> sort(List<Stock> source);
}
