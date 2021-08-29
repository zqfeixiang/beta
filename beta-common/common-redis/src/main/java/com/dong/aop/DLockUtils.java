package com.dong.aop;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@Slf4j
public class DLockUtils {

    private static final Long LOCK_WAIT_TIME = 5L;
    private static final Long LOCK_LEASE_TIME = 20L;

    @Autowired
    RedissonClient redissonClient;

    public <T> Object lock(String key, Supplier<T> supplier){
        return lock(key, LOCK_WAIT_TIME, LOCK_LEASE_TIME, supplier);
    }

    public <T> Object lock(String key, Long lockWaitTime, Long lockLeaseTime, Supplier<T> supplier) {
        RLock lock = redissonClient.getLock(key);
        boolean isLockSuccess = false;
        try {
            isLockSuccess = lock.tryLock(lockWaitTime, lockLeaseTime, TimeUnit.SECONDS);
            log.info("get lock {}", isLockSuccess ? "success" : "fail");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!isLockSuccess){
            throw new RuntimeException("get lock failed!");
        }

        T result = null;
        try {
            result = supplier.get();
        }finally {
            lock.unlock();
            log.info("unlock success");
        }
        return result;
    }
}
