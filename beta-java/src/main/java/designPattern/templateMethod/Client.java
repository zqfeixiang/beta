package designPattern.templateMethod;

public class Client {
    public static void main(String[] args) {
        AbstractTemplate t1 = new ConcreteClass_BaoCai();
        t1.cookProcess();

        System.out.println("------");

        AbstractTemplate t2 = new ConcreteClass_CaiXin();
        t2.cookProcess();
    }
}
