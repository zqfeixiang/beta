package 集合排序;

import java.util.Arrays;
import java.util.Comparator;

import 克隆.Dog;

public class ComparatorTest {
    public static void main(String[] args) {
        Dog[] dogs = new Dog[]{
                new Dog("老旺财", 12),
                new Dog("小旺财", 6),
                new Dog("旺财", 8)
        };

        Arrays.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.age2 - o2.age2;
            }
        });

        for (Dog dog : dogs) {
            System.out.println(dog.getName() + " : " + dog.getAge2());
        }
    }
}
