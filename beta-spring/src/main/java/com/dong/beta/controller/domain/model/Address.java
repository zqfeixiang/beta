package com.dong.beta.controller.domain.model;

import java.util.List;

import lombok.Data;

/**
 * @author dzq
 * @date 2022/6/20 22:35
 */
@Data
public class Address {
    private String address;
    private String Status;
    private List<ZipCode> zipCodes = null;

}
