package 接口;

@FunctionalInterface
public interface IAnimal {
    static String animalName = "Animal";
    static void printSex(){
        System.out.println("Male Dog");
    }
    default void printAge(){
        System.out.println("18");
    }
    void sayHi(String name);
}
