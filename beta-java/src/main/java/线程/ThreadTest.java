package 线程;

import org.junit.Test;
import org.omg.SendingContext.RunTime;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadTest {
    @Test
    public void test() throws InterruptedException {
        new Thread(() -> {
            System.out.println("run");
        }).start();
        Thread interruptThread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < Integer.MAX_VALUE; i++){
                    System.out.println("i=" + i);
                    if(this.isInterrupted()){
                        break;
                    }
                }
            }
        };
        interruptThread.start();
        Thread.sleep(1);
        interruptThread.interrupt();
    }

    @Test
    public void testDeadLock(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        // 线程1拥有对象1，想要等待获取对象2
        new Thread(() -> {
            synchronized (obj1){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }).start();

        // 线程2拥有对象2，想要等待获取对象1
        new Thread(() -> {
            synchronized (obj2){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }).start();
    }
    @Test
    public void testJoinThread() throws InterruptedException {
        Thread joinThread = new Thread(() -> {
            try {
                System.out.println("线程名：" + Thread.currentThread().getName() +"执行前");
                Thread.sleep(1000);
                System.out.println("执行后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        joinThread.run();
        joinThread.run();
        joinThread.run();
        joinThread.start();
        joinThread.join();
        System.out.println("main");
    }

    @Test
    public void testFixedThread() throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            fixedThreadPool.execute(() -> {
                System.out.println("CurrentTime - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(1000);
    }
    @Test
    public void testCachedThreadPool() throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(() -> {
                System.out.println("CurrentTime - " +
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
//        Thread.sleep(1000);
    }

    @Test
    public void testScheduledPool() throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.schedule(() -> {
            System.out.println("ThreadPool->" + LocalDateTime.now());
        }, 1l, TimeUnit.SECONDS);
        System.out.println("CurrentTime:" + LocalDateTime.now());
        Thread.sleep(1000);
    }

    @Test
    public void testWorkStealingPool() throws InterruptedException {
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 5; i++) {
            int finalNumber = i;
            workStealingPool.execute(() -> {
                System.out.println("I：" + finalNumber);
            });
        }
        Thread.sleep(2000);
    }

    @Test
    public void testThreadName(){
        IntStream.range(0, 5).boxed().
                map(i -> new Thread(() -> System.out.println(Thread.currentThread().getName())))
                .forEach(Thread::start);
    }
}
