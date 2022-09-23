package com.dong.beta.config;

import com.github.benmanes.caffeine.cache.Caffeine;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author dzq
 * @date 2022/9/23 13:53
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * 创建基于Caffeine的Cache Manager
     * @return {@link CacheManager}
     */
    @Bean("caffeineCacheManager")
    public CacheManager CaffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(10, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(500));

        return cacheManager;
    }
}
