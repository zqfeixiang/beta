package designPattern.singleton;

public class SingleInstance {
    private volatile static SingleInstance singleInstance;
    private SingleInstance(){}

    public SingleInstance getSingleInstance(){
        if (singleInstance == null){
            synchronized (this){
                if (singleInstance == null){
                    return new SingleInstance();
                }
            }
        }
        return null;
    }
}
