package com.dong.beta.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dzq
 * @date 2021/12/3 2:10 下午
 */
@RestController
public class HelloController {

    @RequestMapping("/provider")
    public String hello(){
        /*try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("hello provider 01 !!!!!!");
        return "hello provider 01 !!!!!!";
    }
}
