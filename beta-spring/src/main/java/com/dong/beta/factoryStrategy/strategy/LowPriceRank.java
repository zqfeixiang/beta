package com.dong.beta.factoryStrategy.strategy;

import com.dong.beta.factoryStrategy.i.Strategy;
import com.dong.beta.factoryStrategy.model.Stock;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 低价榜
 */
@Service("LowPrice")
public class LowPriceRank implements Strategy {

    @Override
    public List<Stock> sort(List<Stock> source) {
        return source.stream()
                .sorted(Comparator.comparing(Stock::getPrice))
                .collect(Collectors.toList());
    }
}