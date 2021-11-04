package com.dong.beta.mapper;

import com.dong.beta.controller.domain.BatchTaskDispatchLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author dzq
 * @date 2021/11/3 5:14 下午
 **/
public interface BatchTaskDispatchLogDao {
    List<BatchTaskDispatchLog> selectByLogTime(@Param("logTime") Date logTime);

    List<BatchTaskDispatchLog> selectByLogTimeStr(@Param("logTime") String logTime);

    List<BatchTaskDispatchLog> getAllLog();
}
