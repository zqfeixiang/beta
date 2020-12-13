package com.dong.beta.pattern.proxy;

public class ProxySubject implements Subject{
    private RealSubject realSubject;

    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        System.out.println("proxy subject request");
        try {
            realSubject.request();
        }catch (Exception e){
            System.out.println("ex:" + e.getMessage());
            throw e;
        }finally {
            System.out.println("finally");
        }
    }
}
