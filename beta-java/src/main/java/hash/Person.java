package hash;

/**
 * 重写了equals方法要重写hashcode
 */
public class Person {
    private Integer age;

    public Person(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        Person p = (Person) obj;
        return p.getAge().equals(this.age);
    }

    @Override
    public int hashCode() {
        return age.hashCode();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Person p1 = new Person(19);
        Person p2 = new Person(19);
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode() + " : " + p2.hashCode());
    }
}
