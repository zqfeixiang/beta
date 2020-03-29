package com.dong.beta.web.controller;

import com.dong.beta.biz.service.DemoService;
import com.dong.beta.dao.domain.ParseRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("test")
    public List<ParseRule> test(){
        return demoService.test();
    }
    @GetMapping("test2")
    public String test2(){
        return "dong";
    }
}
