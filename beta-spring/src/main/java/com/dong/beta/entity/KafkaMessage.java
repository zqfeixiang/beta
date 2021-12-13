package com.dong.beta.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author dzq
 * @date 2021/12/10 6:04 下午
 */
@Data
public class KafkaMessage {

    private Long id;
    private String msg;
    private Date sendTime;
}
