package com.dong.beta.factoryStrategy.strategy;

import com.dong.beta.factoryStrategy.i.Strategy;
import com.dong.beta.factoryStrategy.model.Stock;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 高涨幅榜
 */
@Service("HighRise")
public class HighRiseRank implements Strategy {

    @Override
    public List<Stock> sort(List<Stock> source) {
        return source.stream()
                .sorted(Comparator.comparing(Stock::getRise).reversed())
                .collect(Collectors.toList());
    }
}