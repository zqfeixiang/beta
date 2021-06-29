package com.dong.beta.controller.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BondCodeVo {
    private String securidMain;
    private String corpName;
    private BigDecimal bondPrice;
}
