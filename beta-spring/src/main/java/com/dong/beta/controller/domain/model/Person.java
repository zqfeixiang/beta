package com.dong.beta.controller.domain.model;

import java.util.List;

import lombok.Data;

/**
 * @author dzq
 * @date 2022/6/20 22:33
 */
@Data
public class Person {
    private Address address;
    private List<Car> car = null;
    private List<House> house = null;
}
