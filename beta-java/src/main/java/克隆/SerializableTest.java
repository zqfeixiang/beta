package 克隆;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {
    public static void main(String[] args) throws Exception {
        Dog dog = new Dog();
        dog.setAge2(5);
        dog.setName("wangwang");

        System.out.println(dog);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.txt"));
        oos.writeObject(dog);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
        Dog dog2 = (Dog) ois.readObject();
        ois.close();
        System.out.println(dog2);
    }
}
