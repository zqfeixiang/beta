package 接口;

public class AnimalImpl implements IAnimal {

    public static void main(String[] args) {
        System.out.println(animalName);
        IAnimal.printSex();
        IAnimal animal = new AnimalImpl();
        animal.printAge();

        IAnimal animal1 = x -> System.out.println("Name:" + x);
        animal1.sayHi("Wangcai");
    }

    @Override
    public void sayHi(String name) {

    }
}
