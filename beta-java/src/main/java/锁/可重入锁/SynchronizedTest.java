package 锁.可重入锁;

/**
 * 验证Synchronized 可重入
 */
public class SynchronizedTest {
    Object lock = new Object();
    int value = 0;

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        test.addOne();
        System.out.println(test.value);
    }

    public int getValue(){
        synchronized (lock) {
            return value;
        }
    }

    public void addOne(){
        synchronized (lock) {
            value = getValue() + 1;
        }
    }
}
