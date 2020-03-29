package 克隆;

public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Dog dog = new Dog();
        dog.name = "wangcai";
        dog.age2 = 5;

        //使用克隆来解决引用类型复制的问题
        Dog dog1 = (Dog) dog.clone();
        dog1.name = "xiaobai";
        dog1.age2= 2;

        System.out.println(dog.name + " : " + dog.age2);
        System.out.println(dog1.name + " : " + dog1.age2);
    }
}
