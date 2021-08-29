package com.dong.beta.service.impl;

import com.dong.aop.DLock;
import com.dong.beta.mapper.UsersMapper;
import com.dong.beta.service.CacheService;
import com.dong.beta.service.DemoService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * 项目启动时缓存
 */
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Resource(name = "demoService")
    DemoService demoService;

    @Autowired
    UsersMapper usersMapper;

    @Qualifier("asyncServiceExecutor")
    @Autowired
    private Executor customThreadPool;

    private Map<String, String> userNameCache = Maps.newHashMap();

    private List<Map<String, String>> userNameCacheList = Lists.newArrayList();

    @Scheduled(cron = "0 0/5 * * * ?")
    @PostConstruct
    @DLock("dong")
    @Override
    public void init() {
        CompletableFuture.runAsync(() -> {
            initUserName();
        }, customThreadPool);
    }

    private void initUserName() {
        log.info("start init usename");
        userNameCacheList = usersMapper.getUserIdNameMap();
        Map<String, String> tmpUserNameCache = userNameCacheList.parallelStream().collect(Collectors.toMap(e -> e.get("name"), e -> e.get("department"), (e1, e2) -> e1));
        userNameCache = ImmutableMap.copyOf(tmpUserNameCache);
        log.info("end init username");
    }

    @Override
    public List<Map<String, String>> queryUserName() {
        if (CollectionUtils.isEmpty(userNameCacheList)){
            return Lists.newArrayList();
        }
        return userNameCacheList;
    }
}
