package 队列;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomDelayQueue {
    static AtomicInteger MESSAGE_NO = new AtomicInteger(1);

    public static void main(String[] args) {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();
        // 生产者1
        producer(delayQueue, "生产者1");
        // 生产者2
        producer(delayQueue, "生产者2");
        // 消费者
        consumer(delayQueue);
    }

    private static void producer(DelayQueue<DelayedElement> delayQueue, String name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //产生1-5秒的随机数
                long time = (new Random().nextInt(5) + 1) * 1000l;
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 组合消息体
                String message = String.format("%s，消息编号：%s 发送时间：%s 延迟：%s 秒",
                        name, MESSAGE_NO.getAndIncrement(), DateFormat.getDateTimeInstance().format(new Date()), time / 1000);
                // 生产消息
                delayQueue.put(new DelayedElement(message, time));
            }
        }).start();
    }

    private static void consumer(DelayQueue<DelayedElement> delayQueue){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DelayedElement delayedElement = null;
                try {
                    delayedElement = delayQueue.take();
                    System.out.println("消费者：" + delayedElement);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
