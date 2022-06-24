package com.dong.beta.controller.domain.model;

import java.util.List;

import lombok.Data;

/**
 * @author dzq
 * @date 2022/6/22 16:01
 */
@Data
public class Province {
    private String name;
    private List<City> cityList;
}
