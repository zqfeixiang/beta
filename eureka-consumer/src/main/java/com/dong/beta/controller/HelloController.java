package com.dong.beta.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

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
        ResponseEntity<String> entity = restTemplate.getForEntity("http://{serviceBHost}/insertData", String.class);
        return entity.getBody();
    }

    /**
     * hystrix 超时时间
     * @return
     */
    @RequestMapping("/hystrix")
    @HystrixCommand(fallbackMethod = "error", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3500")
    })
    public String hystrix(){
        System.out.println("hystrix");
//        int a  = 1 / 0;
        ResponseEntity<String> entity = restTemplate.getForEntity("http://eureka-provider/provider", String.class);
        return entity.getBody();
    }
    public String error(Throwable throwable){
        System.out.println("error: " + throwable.getMessage());
        return "hystrix error method";
    }
}
