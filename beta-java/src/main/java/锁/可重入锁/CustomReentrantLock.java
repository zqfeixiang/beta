package 锁.可重入锁;

/**
 * 自己实现不可重入锁
 */
public class CustomReentrantLock {

    boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked){
            wait();
        }
        isLocked = true;
    }

    public void unlock(){
        isLocked = false;
        notify();
    }

}
