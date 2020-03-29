package 并发.example2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < barrier.getParties(); i++) {
            new Thread(new MyThread(barrier), "队友" + i).start();
        }
        System.out.println("main function is finished.");
    }

    private static class MyThread implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    int random = (new Random().nextInt(2000) + 1) + 1000;
                    Thread.sleep(random);
                    System.out.println(Thread.currentThread().getName() + ", 通过了第"+i+"个障碍物, 使用了 "+((double)random/1000)+"s");
                    this.cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
