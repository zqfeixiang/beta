package static1;

/**
 * static{}静态代码块与{}非静态代码块(构造代码块)
 * 相同点： 都是在JVM加载类时且在构造方法执行之前执行，在类中都可以定义多个，定义多个时按定义的顺序执行，一般在代码块中对一些static变量进行赋值。
 *
 * 不同点： 静态代码块在非静态代码块之前执行(静态代码块 -> 非静态代码块 -> 构造方法)。
 * 静态代码块只在第一次new执行一次，之后不再执行，而非静态代码块在每new一次就执行一次。 非静态代码块可在普通方法中定义(不过作用不大)；而静态代码块不行。
 *
 * 代码输出：
 *
 * 静态代码块！--非静态代码块！--默认构造方法！--静态方法中的内容! --静态方法中的代码块！--
 * 当只执行 Test.test(); 时输出：
 *
 * 静态代码块！--静态方法中的内容! --静态方法中的代码块！--
 * 当只执行 Test test = new Test(); 时输出：
 *
 * 静态代码块！--非静态代码块！--默认构造方法！--
 *
 */
public class Test {
    public Test() {
        System.out.print("默认构造方法！--");
    }

    //非静态代码块
    {
        System.out.print("非静态代码块！--");
    }

    //静态代码块
    static {
        System.out.print("静态代码块！--");
    }

    private static void test() {
        System.out.print("静态方法中的内容! --");
        {
            System.out.print("静态方法中的代码块！--");
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        Test.test();//静态代码块！--静态方法中的内容! --静态方法中的代码块！--
    }
}
