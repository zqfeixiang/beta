package guava;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuavaTest {

    @Test
    public void test1(){
        String[] headers = {"aa", "bb", "cc"};
        List<String> list = Arrays.asList(headers);
        String result = Joiner.on(",").join(list);
        log.info(result);
    }

    @Test
    public void test2(){
        List<Map<String, Object>> natureList = Lists.newArrayList();
        Map<String, Object> m1 = Maps.newHashMap();
        m1.put("the_date", "2019-06-01");
        Map<String, Object> m2 = Maps.newHashMap();
        m2.put("the_date", "2019-06-02");
        Map<String, Object> m3 = Maps.newHashMap();
        m3.put("the_date", "2019-06-03");
        Map<String, Object> m4 = Maps.newHashMap();
        m4.put("the_date", "2019-06-04");
        Map<String, Object> m5 = Maps.newHashMap();
        m5.put("the_date", "2019-06-05");
        Map<String, Object> m6 = Maps.newHashMap();
        m6.put("the_date", "2019-06-06");
        Map<String, Object> m7 = Maps.newHashMap();
        m7.put("the_date", "2019-06-07");
        Map<String, Object> m8 = Maps.newHashMap();
        m8.put("the_date", "2019-06-08");
        Map<String, Object> m9 = Maps.newHashMap();
        m9.put("the_date", "2019-06-09");
        Map<String, Object> m10 = Maps.newHashMap();
        m10.put("the_date", "2019-06-10");
        Map<String, Object> m11 = Maps.newHashMap();
        m11.put("the_date", "2019-06-11");
        Map<String, Object> m12 = Maps.newHashMap();
        m12.put("the_date", "2019-06-12");
        Map<String, Object> m13 = Maps.newHashMap();
        m13.put("the_date", "2019-06-13");
        Map<String, Object> m14 = Maps.newHashMap();
        m14.put("the_date", "2019-06-14");
        Map<String, Object> m15 = Maps.newHashMap();
        m15.put("the_date", "2019-06-15");
        natureList.add(m1);
        natureList.add(m2);
        natureList.add(m3);
        natureList.add(m4);
        natureList.add(m5);
        natureList.add(m6);
        natureList.add(m7);
        natureList.add(m8);
        natureList.add(m9);
        natureList.add(m10);
        natureList.add(m11);
        natureList.add(m12);
        natureList.add(m13);
        natureList.add(m14);
        natureList.add(m15);

        List<Map<String, String>> tradeList = Lists.newArrayList();
        Map<String, String> mm11 = Maps.newHashMap();
        mm11.put("trade_date", "2019-06-03");
        Map<String, String> mm12 = Maps.newHashMap();
        mm12.put("trade_date", "2019-06-04");
        Map<String, String> mm13 = Maps.newHashMap();
        mm13.put("trade_date", "2019-06-05");
        Map<String, String> mm14 = Maps.newHashMap();
        mm14.put("trade_date", "2019-06-06");
        Map<String, String> mm15 = Maps.newHashMap();
        mm15.put("trade_date", "2019-06-10");
        Map<String, String> mm16 = Maps.newHashMap();
        mm16.put("trade_date", "2019-06-11");
        Map<String, String> mm17 = Maps.newHashMap();
        mm17.put("trade_date", "2019-06-12");
        Map<String, String> mm18 = Maps.newHashMap();
        mm18.put("trade_date", "2019-06-13");
        Map<String, String> mm19 = Maps.newHashMap();
        mm19.put("trade_date", "2019-06-14");
        tradeList.add(mm11);
        tradeList.add(mm12);
        tradeList.add(mm13);
        tradeList.add(mm14);
        tradeList.add(mm15);
        tradeList.add(mm16);
        tradeList.add(mm17);
        tradeList.add(mm18);
        tradeList.add(mm19);

        LocalDate start = LocalDate.parse("2019-06-01");
        for (Map<String, Object> m : natureList){
            LocalDate end = LocalDate.parse(m.get("the_date").toString());
            System.out.println("end:" + end);
            List<String> trade_date = tradeList.stream().map(t -> t.get("trade_date")).collect(Collectors.toList());
            long count = trade_date.stream().filter(str -> (LocalDate.parse(str).isBefore(end) &&
                    LocalDate.parse(str).isAfter(start)) || LocalDate.parse(str).isEqual(start) || LocalDate.parse(str).isEqual(end)).count();
            m.put("trade_day", count);
        }
        System.out.printf("===========");
    }
}
