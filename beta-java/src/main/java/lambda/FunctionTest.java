package lambda;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import bean.Employee;
import lombok.extern.slf4j.Slf4j;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.summarizingDouble;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static org.apache.kafka.common.utils.Utils.notNull;
import static org.junit.Assert.assertEquals;

@Slf4j
public class FunctionTest {

    Function<Long, Long> add = value -> value + 3;
    Predicate<Object> notNull = Objects::nonNull;
    Supplier<Long> random = () -> (long) (Math.random() * 20);
    Consumer<Object> print = value -> log.info("{}", value);
    List<Employee> userList;
    Employee[] employeeList = {
            Employee.builder().name("lisi").age(33).salary(5000.00).build(),
            Employee.builder().name("zhangsan").age(35).salary(3000.00).build(),
            Employee.builder().name("wangwu").age(40).salary(6000.00).build()
    };
    @Before
    public void init() {
        log.info("init");
        userList = Arrays.asList(employeeList);
    }

    @Test
    public void test() {
        print.accept(add.apply((long)10));
        print.accept(notNull.test(5));
        print.accept(random.get());
        LongStream.range(0, random.get()).map(x -> x + 3).skip(1)
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
        DoubleSummaryStatistics summaryStatistics = userList.stream().collect(summarizingDouble(Employee::getAge));
        assertEquals((33 + 35 + 40) / 3.0, summaryStatistics.getAverage(), 0.0);

        List<String> list = Arrays.asList("bb", "ddd", "cc", "a");
        Map<Integer, TreeSet<String>> collect = list.stream()
                .collect(
                        groupingBy(
                                String::length,
                                mapping(
                                        String::toUpperCase,
                                        toCollection(TreeSet::new))
                        )
                );
        log.info("result:{}", collect);
    }

    @Test
    public void test3(){
        Map<String, String> map = Maps.newHashMap();
        map.put("userName", "zhangsan");
        map.put("email", "zhangsan@163.com");
        map.put("age", "30");
        String collect = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .sorted()
                .collect(Collectors.joining(
                        "&",
                        "http://www.baidu.com?",
                        ""
                ));
        assertEquals("http://www.baidu.com?age=30&email=zhangsan@163.com&userName=zhangsan", collect);
    }
    @Test
    public void testSort(){
        List<String> list = Arrays.asList("One", "Abc", "BCD");
        List<String> collect = list.stream().sorted().collect(toList());
        assertEquals("Abc", collect.get(0));
        List<String> collect1 = list.stream().sorted(reverseOrder()).collect(toList());
        assertEquals("One", collect1.get(0));
        List<String> collect2 = list.stream().sorted((o1, o2) -> {
            return o1.compareTo(o2);
        }).collect(toList());
    }

    @Test
    public void testw(){
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> m1 = Maps.newHashMap();
        m1.put("theDate", "2019-12-01");
        m1.put("dq", "11");
        Map<String, Object> m2 = Maps.newHashMap();
        m2.put("theDate", "2019-12-02");
        m2.put("dq", "22");
        Map<String, Object> m3 = Maps.newHashMap();
        m3.put("theDate", "2019-12-03");
        m3.put("dq", "33");
        Map<String, Object> m4 = Maps.newHashMap();
        m4.put("theDate", "2019-12-04");
        m4.put("dq", "44");

        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        Comparator<Map<String, Object>> com = (mm, mn) -> mn.get("theDate").toString().compareTo(mm.get("theDate").toString());
        Object dq = list.stream().filter(m -> m.get("dq") != null).min(com).orElse(null).get("dq");
        System.out.println("ssss:" + dq);

    }
}
