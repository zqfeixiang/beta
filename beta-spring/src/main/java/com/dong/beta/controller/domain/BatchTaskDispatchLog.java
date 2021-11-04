package com.dong.beta.controller.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author dzq
 * @date 2021/11/3 5:12 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchTaskDispatchLog {

    private String taskId;
    private String eventType;
    private String msgDesc;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logTime;
}
