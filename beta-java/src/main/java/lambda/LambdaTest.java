package lambda;


import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import bean.Employee;


public class LambdaTest {
    static List<Employee> employees = Arrays.asList(
            new Employee("cc", 29, 8999.8),
            new Employee("aa", 40, 19999.8),
            new Employee("dd", 30, 9999.8),
            new Employee("ee", 32, 10999.8),
            new Employee("aa", 50, 99999.8),
            new Employee("bb", 28, 20999.8));

    public static void main(String[] args) {
//        test();
//        sort();
//        test2();

        Map<String, List<Integer>> listMap = convertListToMap(employees);

        System.out.println(new Random().nextInt(100));

        Map<String, Integer> collect = employees.stream().filter(employee -> employee.getAge() > 30)
                .collect(Collectors.toMap(Employee::getName, Employee::getAge, (e1, e2) -> e1));


    }

    /**
     * convert List<Object> to Map<String, List<Integer>
     *
     * @param list
     * @return
     */
    public static Map<String, List<Integer>> convertListToMap(List<Employee> list) {
        Map<String, List<Integer>> result = new HashMap<>();
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        result = list.stream().collect(Collectors.groupingBy(Employee::getName,
                Collectors.mapping(Employee::getAge, Collectors.toList())));

        return result;
    }

    public static void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().map(x -> x * x).collect(Collectors.toList()).forEach(System.out::println);

//        用map reduce数一数有多少employee
        Optional<Integer> reduce = employees.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());

//        java8并行流
        Instant start = Instant.now();
        long reduce1 = LongStream.rangeClosed(0, 10000000000l)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("Time consuming:" + Duration.between(start, end).toMillis());
    }

    public static void sort() {
        System.out.println("自然排序");
        employees.stream().sorted().forEach(System.out::println);

        System.out.println("定制排序");
        employees.stream()
                .sorted((e1, e2) -> -Integer.compare(e1.getAge(), e2.getAge()))
                .forEach(System.out::println);

        Double collect = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect);
        Long collect1 = (long) employees.size();
        System.out.println(collect1);
        Double collect2 = employees.stream().mapToDouble(Employee::getAge).sum();
        System.out.println(collect2);

        List<Employee> reversedCollect = employees.parallelStream().sorted(Comparator.comparing(Employee::getAge).reversed()).collect(Collectors.toList());
        Set<String> collect3 = employees.parallelStream().map(Employee::getName).collect(Collectors.toSet());
        List<Employee> collect4 = employees.parallelStream().sorted(Comparator.comparing(Employee::getSalary, Double::compareTo).reversed()).collect(Collectors.toList());

        System.out.println(reversedCollect);
        System.out.println(collect3);
        System.out.println(collect4);
    }

    public static void test() {
        Consumer<String> consumer = (x) -> System.out.println("hello " + x);
        consumer.accept("dong");

        System.out.println(strHandler("dong", String::toUpperCase));

        System.out.println(longHandler(100l, 200l, (l1, l2) -> l1 * l2));

        List<Integer> list = Arrays.asList(1, 30, 15, 20, 60, 40, 9, 5);
        list.stream().filter(x -> x % 2 == 0).map(y -> y + 100).forEach(System.out::println);

        System.out.println("============================");

        /*
         * 创建无限流
         * 1。迭代
         * 2。生成
         */
        Stream<Integer> stream = Stream.iterate(1, x -> x + 2);
        stream.limit(10).forEach(System.out::println);

        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    public static String strHandler(String str, StringOperate stringOperate) {
        return stringOperate.operate(str);
    }

    public static Long longHandler(long l1, long l2, LongOperate<Long, Long> mf) {
        return mf.getValue(l1, l2);
    }
}
