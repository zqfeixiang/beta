package com.dong.beta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author dzq
 * @date 2021/12/3 2:12 下午
 */
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("consumer hello");
        ResponseEntity<String> entity = restTemplate.getForEntity("http://eureka-provider/provider", String.class);
        return entity.getBody();
    }
}
