package 克隆;

import java.io.Serializable;

public class Dog implements Cloneable , Serializable, Comparable<Dog> {

    private static final long serialVersionUID = 945106014294906162L;
    public String name;
    public Integer age2;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge2() {
        return age2;
    }

    public void setAge2(Integer age) {
        this.age2 = age;
    }

    public Dog() {
    }

    public Dog(String name, Integer age2) {
        this.name = name;
        this.age2 = age2;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age='" + age2 + '\'' +
                '}';
    }

    @Override
    public int compareTo(Dog o) {
        return age2 - o.age2;
    }
}
