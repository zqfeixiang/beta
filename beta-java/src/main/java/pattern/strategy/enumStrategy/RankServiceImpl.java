package pattern.strategy.enumStrategy;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class RankServiceImpl {

    /**
     * dataService.getSource() 提供原始的股票数据
     */
//    @Resource
    private DataService dataService = new DataService();

    /**
     * 前端传入榜单类型, 返回排序完的榜单
     *
     * @param rankType 榜单类型 形似 RankEnum.HighPrice.name()
     * @return 榜单数据
     */
    public List<Stock> getRank(String rankType) {
        // 获取策略，这里如果未匹配会抛 IllegalArgumentException异常
        RankEnum rank = RankEnum.valueOf(rankType);
        // 然后执行策略
        return rank.sort(dataService.getSource());
    }
}