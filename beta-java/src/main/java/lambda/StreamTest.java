package lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class StreamTest {
    private static List<Person> list;
    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("iron", 17, "宁波", 888.8));
    }
    public static void main(String[] args) {
        Map<String, Person> collect = list.parallelStream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toMap(Person::getName, person -> person, (p1, p2) -> p2));
        System.out.println(collect);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class Person {
        private String name;
        private Integer age;
        private String address;
        private Double salary;
    }
}
