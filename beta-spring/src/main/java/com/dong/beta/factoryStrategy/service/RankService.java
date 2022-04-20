package com.dong.beta.factoryStrategy.service;

import com.dong.beta.factoryStrategy.i.Strategy;
import com.dong.beta.factoryStrategy.model.Stock;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class RankService {

    /**
     * dataService.getSource() 提供原始的股票数据
     */
    @Resource
    private DataService dataService;
    /**
     * 利用注解@Resource和@Autowired特性,直接获取所有策略类
     * key = @Service的value
     */
    @Resource
    private Map<String, Strategy> rankMap;

    /**
     * 前端传入榜单类型, 返回排序完的榜单
     *
     * @param rankType 榜单类型 和Service注解的value属性一致
     * @return 榜单数据
     */
    public List<Stock> getRank(String rankType) {
        // 判断策略是否存在
        if (!rankMap.containsKey(rankType)) {
            throw new IllegalArgumentException("rankType not found");
        }
        // 获得策略实例
        Strategy rank = rankMap.get(rankType);
        // 执行策略
        return rank.sort(dataService.getSource());
    }
}