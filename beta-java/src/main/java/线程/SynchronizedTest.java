package 线程;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedTest {

    static int number = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            addNumber();
        });
        Thread t2 = new Thread(() -> {
            addNumber();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("number:" + number);
    }

    public static void addNumber() {
        for (int i = 0; i < 10000; i++) {
            ++number;
        }
    }

    @Test
    public void test() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            lock.lock();
            addNumber();
            lock.unlock();
        });
        Thread t2 = new Thread(() -> {
            lock.lock();
            addNumber();
            lock.unlock();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("number:" + number);
    }
}
