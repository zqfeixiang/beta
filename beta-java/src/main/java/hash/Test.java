package hash;

public class Test {
    private static Object object = new Object();
    public static void main(String[] args) {
        synchronized (object){
            System.out.println("hello");
        }
    }
    private static synchronized void test(){
        System.out.println("test");
    }
}
