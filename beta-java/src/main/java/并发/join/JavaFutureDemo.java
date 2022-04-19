package 并发.join;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dzq
 * @date 2021/12/29 3:46 下午
 *
 * Guava {@code ListenableFuture} 扩展了Java的Future接口，实现了 非阻塞获取异步结果的功能。
 */
@Slf4j
public class JavaFutureDemo {
    public static final int SLEEP_GAP = 500;

    public static final String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        Callable<Boolean> hotWaterJob = new HotWaterJob();  // 异步逻辑
        FutureTask<Boolean> hTask = new FutureTask<>(hotWaterJob);        // 搭桥实例
        Thread hotThread = new Thread(hTask, "** 烧水线程");          //异步线程

        Callable<Boolean> washJob = new WashJob();
        FutureTask<Boolean> washTask = new FutureTask<>(washJob);
        Thread washThread = new Thread(washTask, "$$ 清洗线程");

        hotThread.start();
        washThread.start();

        try {
            Boolean hotWaterOk = hTask.get();
            Boolean washOk = washTask.get();
            drinkTea(hotWaterOk, washOk);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info(getCurrentThreadName() + "运行结束");
    }

    private static void drinkTea(Boolean hotWaterOk, Boolean washOk) {
        if (hotWaterOk && washOk){
            log.info("泡茶喝");
        }else if (!washOk){
            log.info("清洗杯子失败");
        }else if (!hotWaterOk){
            log.info("烧水失败");
        }
    }

    static class HotWaterJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            try {
                log.info("清洗水壶");
                log.info("装满水");
                log.info("放在火上");
                Thread.sleep(SLEEP_GAP);
                log.info("水开了");
            } catch (InterruptedException e) {
                log.error("发生异常");
                return false;
            }
            log.info("运行结束");
            return true;
        }
    }

    static class WashJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            try {
                log.info("洗茶壶");
                log.info("洗茶杯");
                log.info("拿茶叶");
                Thread.sleep(SLEEP_GAP);
                log.info("洗完了");
            } catch (InterruptedException e) {
                log.error("运行中断");
                return false;
            }
            log.info("运行结束");
            return true;
        }
    }
}
