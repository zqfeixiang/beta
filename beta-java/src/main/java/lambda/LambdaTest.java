package lambda;


import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
            new Employee("bb", 28, 20999.8));

    public static void main(String[] args) {
//        test();
//        sort();
//        test2();
        System.out.println(new Random().nextInt(100));
    }

    public static void test2(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().map(x -> x * x).collect(Collectors.toList()).forEach(System.out::println);

//        用map resuce数一数有多少employee
        Optional<Integer> reduce = employees.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());


//        java8并行流
        Instant start = Instant.now();
        long reduce1 = LongStream.rangeClosed(0, 10000000000l)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("Time consuming:" + Duration.between(start, end).toMillis() );
    }

    public static void sort(){
        System.out.println("自然排序");
        employees.stream().sorted().forEach(System.out::println);

        System.out.println("定制排序");
        employees.stream()
                .sorted((e1, e2) -> -Integer.compare(e1.getAge(), e2.getAge()))
                .forEach(System.out::println);

        Double collect = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect);
        Long collect1 = employees.stream().collect(Collectors.counting());
        System.out.println(collect1);
        Double collect2 = employees.stream().collect(Collectors.summingDouble(Employee::getAge));
        System.out.println(collect2);
    }

    public static void test() {
        Consumer<String> consumer = (x) -> System.out.println("hello " + x);
        consumer.accept("dongzhaoqi");

        System.out.println(strHandler("dongzhaoqi", x -> x.toUpperCase()));

        System.out.println(longHandler(100l, 200l, (l1, l2) -> l1*l2));

        List<Integer> list = Arrays.asList(1, 30, 15, 20, 60, 40, 9, 5);
        list.stream().filter(x -> x%2==0).map(y -> y+100).forEach(System.out::println);

        System.out.println("============================");

        /**
         * 创建无限流
         * 1。迭代
         * 2。生成
          */
        Stream<Integer> stream = Stream.iterate(1, x -> x + 2);
        stream.limit(10).forEach(System.out::println);

        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);
    }

    public static String strHandler(String str, StringOperate stringOperate){
        return stringOperate.operate(str);
    }

    public static Long longHandler(long l1, long l2, LongOperate<Long, Long> mf){
        return mf.getValue(l1, l2);
    }
}
