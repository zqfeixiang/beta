package 锁.可重入锁;

/**
 * 自己实现可重入锁
 * 可重入锁增加了两个状态，锁的计数器和被锁的线程
 */
public class CustomUnReentrantLock {

    boolean isLocked = false;
    volatile int state = 0;
    Thread lockByThread = null;

    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (isLocked && lockByThread != currentThread){
            wait();
        }
        isLocked = true;
        state++;
        lockByThread = currentThread;
    }

    public synchronized void unlock(){
        Thread currentThread = Thread.currentThread();
        if (currentThread == lockByThread){
            state--;
            if (state == 0){
                isLocked = false;
                notify();
            }
        }
    }
}
