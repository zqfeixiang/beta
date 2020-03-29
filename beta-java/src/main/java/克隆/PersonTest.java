package 克隆;

import java.util.Arrays;

public class PersonTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        ParrotChild parrotChild = new ParrotChild();
        parrotChild.name = "jingang";
        Person p1 = new Person("zhangsan", 3, parrotChild);
        Person p2 = (Person) p1.clone();
        p2.setName("lisi");
        p2.setAge(5);
        ParrotChild parrotChild2 = new ParrotChild();
        parrotChild2.name = "babi";
        p2.setParrotChild(parrotChild2);
        System.out.println("p1:" + p1.getName() + " " + p1.getAge() + " " + p1.getParrotChild().name);
        System.out.println("p2:" + p2.getName() + " " + p2.getAge() + " " + p2.getParrotChild().name);

        int[] a1 = {1,2,3,4,5};
        int[] a2 = a1.clone();
        a2[2] = 6;
        System.out.println(Arrays.toString(a1));
    }
}
