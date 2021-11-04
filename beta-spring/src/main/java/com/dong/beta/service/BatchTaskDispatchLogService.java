package com.dong.beta.service;

import com.dong.beta.controller.domain.BatchTaskDispatchLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author dzq
 * @date 2021/11/3 5:23 下午
 **/
@Service
public interface BatchTaskDispatchLogService {
    List<BatchTaskDispatchLog> selectByLogTime(Date logTime);

    List<BatchTaskDispatchLog> selectByLogTimeStr(String logTime);

    List<BatchTaskDispatchLog> getAllLog();
}
