package com.dong.beta.controller;

import com.dong.beta.controller.domain.ParseRule;
import com.dong.beta.service.AsyncService;
import com.dong.beta.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("demo")
public class DemoController {

    static Integer c = 0;
    @Autowired
    private DemoService demoService;
    @Autowired
    AsyncService asyncService;

    @RequestMapping("/async")
    public void async(){
        asyncService.executeAsync();
    }

    @RequestMapping("/stat")
    public Integer stat(){
        return c;
    }

    @RequestMapping("/add")
    public Integer add(){
        c++;
        return 1;
    }


    @GetMapping("test")
    public List<ParseRule> test(){
        return demoService.test();
    }
    @GetMapping("test2")
    public String test2(){
        return "dong";
    }
}
