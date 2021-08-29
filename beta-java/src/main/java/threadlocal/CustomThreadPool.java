package threadlocal;

import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.*;

public class CustomThreadPool {
    private static final int CORE_POOL_SIZE = 50;
    private static final int MAX_POOL_SIZE = 100;
    private static final int TIME_TO_LIVE = 10000;

    private static ExecutorService commonThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, TIME_TO_LIVE,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());

    private static ForkJoinPool forkJoinPool = new ForkJoinPool(CORE_POOL_SIZE);

    public static ExecutorService getCommonThreadPool(){
        return TtlExecutors.getTtlExecutorService(commonThreadPool);
    }

    public static ForkJoinPool getForkJoinPool(){
        return forkJoinPool;
    }
}
