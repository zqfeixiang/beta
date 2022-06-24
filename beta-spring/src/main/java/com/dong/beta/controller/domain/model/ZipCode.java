package com.dong.beta.controller.domain.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author dzq
 * @date 2022/6/20 22:36
 */
@Data
public class ZipCode {
    private String code;
    private Date createDate;
    private List<Province> provinceList;
}
