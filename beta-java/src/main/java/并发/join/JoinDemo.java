package 并发.join;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dzq
 * @date 2021/12/29 3:16 下午
 * 模拟泡茶喝，主线程中开启清洗线程、烧水线程
 */
@Slf4j
public class JoinDemo {
    public static final int SLEEP_GAP = 500;

    public static final String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        Thread washThread = new WashThread();
        Thread hotThread = new HotWaterThread();
        hotThread.start();
        washThread.start();
        try {
            hotThread.join();
            washThread.join();
            Thread.currentThread().setName("主线程");
        }catch (InterruptedException e ){
            log.error(getCurrentThreadName() + "exception");
        }
        log.info(getCurrentThreadName() + "运行结束.");
    }

    static class HotWaterThread extends Thread {

        HotWaterThread() {
            super("** 烧水线程");
        }

        @Override
        public void run() {
            try {
                log.info("清洗水壶");
                log.info("装满水");
                log.info("放在火上");
                Thread.sleep(SLEEP_GAP);
                log.info("水开了");
            } catch (InterruptedException e) {
                log.error("发生异常");
            }
        }
    }

    static class WashThread extends Thread{
        WashThread(){
            super("$$ 清洗线程");
        }

        @Override
        public void run() {
            try {
                log.info("洗茶壶");
                log.info("洗茶杯");
                log.info("拿茶叶");
                Thread.sleep(SLEEP_GAP);
                log.info("洗完了");
            } catch (InterruptedException e) {
                log.error("运行中断");
            }
            log.info("运行结束");
        }
    }
}
