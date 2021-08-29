package com.dong.beta.service.impl;

import com.dong.beta.controller.domain.ParseRule;
import com.dong.beta.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("demoservice2")
@Slf4j
public class DemoService2 implements DemoService {
    @Override
    public List<ParseRule> test() {
        log.info("demo2");
        return null;
    }
}
