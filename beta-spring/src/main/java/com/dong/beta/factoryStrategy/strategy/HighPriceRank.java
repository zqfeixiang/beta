package com.dong.beta.factoryStrategy.strategy;

import com.dong.beta.factoryStrategy.i.Strategy;
import com.dong.beta.factoryStrategy.model.Stock;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 高价榜
 * 注意申明 Service.value = HighPrice,他是我们的key,下同
 */
@Service("HighPrice")
public class HighPriceRank implements Strategy {

    @Override
    public List<Stock> sort(List<Stock> source) {
        return source.stream()
                .sorted(Comparator.comparing(Stock::getPrice).reversed())
                .collect(Collectors.toList());
    }
}