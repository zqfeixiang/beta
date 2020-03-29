package 并发;

import java.util.concurrent.CountDownLatch;

public class SeeDocterTask implements Runnable {
    private CountDownLatch countDownLatch;

    public SeeDocterTask(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("看大夫成功，大夫给开了些药单子");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (countDownLatch != null){
                countDownLatch.countDown();
            }
        }
    }
}
