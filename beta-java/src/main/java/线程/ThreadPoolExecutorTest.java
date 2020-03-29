package 线程;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 10l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2), new MyFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        threadPool.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    static class MyFactory implements ThreadFactory {

        AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            String threadName = "MyThread" + count.addAndGet(1);
            thread.setName(threadName);
            return thread;
        }
    }

    @Test
    public void test() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 10l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2), new ThreadPoolExecutor.DiscardPolicy());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
