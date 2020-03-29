package 多态;

/**
 * 调用构造器要按照下面的顺序：
 * 1. 调用基类构造器。
 * 2. 按声明顺序调用成员的初始化方法。
 * 3. 调用导出类构造器的主体。
 */
public class Sandwich extends PortableLunch{
    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();

    public Sandwich(){
        System.out.println("Sandwich()");
    }

    public static void main(String[] args) {
        new Sandwich();
    }
}

class PortableLunch extends Lunch{
    PortableLunch(){
        System.out.println("PortableLunch()");
    }
}

class Lunch extends Meal{
    Lunch(){
        System.out.println("Lunch()");
    }
}

class Meal{
    Meal(){
        System.out.println("Meal()");
    }
}
class Bread{
    Bread(){
        System.out.println("Bread()");
    }
}
class Cheese{
    Cheese(){
        System.out.println("Cheese()");
    }
}
class Lettuce{
    Lettuce(){
        System.out.println("Lettuce()");
    }
}