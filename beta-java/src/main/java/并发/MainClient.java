package 并发;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClient {
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new SeeDocterTask(countDownLatch));
        executor.execute(new QueueTask(countDownLatch));
        countDownLatch.await();
        System.out.println("over，回家 cost:"+(System.currentTimeMillis()-now));
    }
}
