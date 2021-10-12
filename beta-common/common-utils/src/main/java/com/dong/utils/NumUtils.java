package com.dong.utils;

import java.math.BigDecimal;

/**
 * @author dzq
 * @date 2021/9/26 10:43 上午
 **/
public class NumUtils {

    public static double num2digits(double num){
        BigDecimal bg = new BigDecimal(num);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(num2digits(2.12356));
    }
}
