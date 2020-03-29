package 内部类;

public class Outer {
    private String name = "OuterClass";
    public void sayHi(){
        System.out.println("Hi, Outer");
    }

    public Outer(){
        System.out.println("Outer Class");
    }
    class Inner{
        public void sayHi(){

            Outer.this.sayHi();
            System.out.println("Hi, I am Inner");
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        //创建成员内部类
        Outer.Inner inner = outer.new Inner();
        inner.sayHi();
    }
}
