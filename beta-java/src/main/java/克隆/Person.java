package 克隆;

public class Person implements Cloneable{
    private String name;
    private int age;
    private ParrotChild parrotChild;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ParrotChild getParrotChild() {
        return parrotChild;
    }

    public void setParrotChild(ParrotChild parrotChild) {
        this.parrotChild = parrotChild;
    }

    public Person(String name, int age, ParrotChild parrotChild) {
        this.name = name;
        this.age = age;
        this.parrotChild = parrotChild;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
