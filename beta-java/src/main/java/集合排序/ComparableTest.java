package 集合排序;

import java.util.Arrays;

import 克隆.Dog;

public class ComparableTest {
    public static void main(String[] args) {
        Dog[] dogs = new Dog[]{
                new Dog("老旺财", 12),
                new Dog("小旺财", 6),
                new Dog("旺财", 8)
        };

        Arrays.sort(dogs);
        for (Dog dog : dogs) {
            System.out.println(dog.getName() + " : " + dog.getAge2());
        }
    }
}
