package 锁.可重入锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 验证ReentrantLock可重入和不可重入
 */
public class ReentrantTest {

    Lock lock = new ReentrantLock();
    int value = 0;

    public static void main(String[] args) {
        ReentrantTest test = new ReentrantTest();
        test.addOne();
        System.out.println(test.value);
    }

    public int getVale(){
        try {
            lock.lock();
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return 0;
    }

    public void addOne(){
        try {
            lock.lock();
            value = getVale() + 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
