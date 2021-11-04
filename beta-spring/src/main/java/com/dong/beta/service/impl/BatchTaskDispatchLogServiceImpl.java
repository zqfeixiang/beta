package com.dong.beta.service.impl;

import com.dong.beta.controller.domain.BatchTaskDispatchLog;
import com.dong.beta.mapper.BatchTaskDispatchLogDao;
import com.dong.beta.service.BatchTaskDispatchLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author dzq
 * @date 2021/11/3 5:24 下午
 **/
@Service
@Slf4j
public class BatchTaskDispatchLogServiceImpl implements BatchTaskDispatchLogService {

    @Autowired
    BatchTaskDispatchLogDao batchTaskDispatchLogDao;

    @Override
    public List<BatchTaskDispatchLog> selectByLogTime(Date logTime) {
        List<BatchTaskDispatchLog> batchTaskDispatchLogs = batchTaskDispatchLogDao.selectByLogTime(logTime);
        return batchTaskDispatchLogs;
    }

    @Override
    public List<BatchTaskDispatchLog> selectByLogTimeStr(String logTime) {
        List<BatchTaskDispatchLog> batchTaskDispatchLogs = batchTaskDispatchLogDao.selectByLogTimeStr(logTime);
        return batchTaskDispatchLogs;
    }

    @Override
    public List<BatchTaskDispatchLog> getAllLog() {
        return batchTaskDispatchLogDao.getAllLog();
    }
}
