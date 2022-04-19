package pattern.strategy.enumStrategy;

import java.util.List;

/**
 * @author dzq
 * @date 2022/4/19 3:04 下午
 */
public class Test {

    public static void main(String[] args) {
        RankServiceImpl rankService = new RankServiceImpl();
        List<Stock> lowPrice = rankService.getRank("HighRise");
        lowPrice.forEach(System.out::println);
    }
}
