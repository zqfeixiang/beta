package designPattern.proxy;

public class RealSubject implements Subject{

    @Override
    public void request() {
        System.out.println("real subject execute request");
    }
}
