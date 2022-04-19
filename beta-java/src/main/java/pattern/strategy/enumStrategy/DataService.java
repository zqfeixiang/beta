package pattern.strategy.enumStrategy;

import com.google.common.collect.Lists;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dzq
 * @date 2022/4/19 2:57 下午
 */
@Service
public class DataService {

    public List<Stock> getSource() {
        List<Stock> stockList = Lists.newArrayList();
        Stock s1 = new Stock("1001.sh", 10d, 0.1d);
        Stock s2 = new Stock("1002.sh", 8d, 0.2d);
        Stock s3 = new Stock("1003.sh", 11d, 8d);
        Stock s4 = new Stock("1004.sh", 15d, 0.8d);
        stockList.add(s1);
        stockList.add(s2);
        stockList.add(s3);
        stockList.add(s4);

        return stockList;
    }
}
