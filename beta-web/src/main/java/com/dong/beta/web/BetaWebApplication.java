package com.dong.beta.web;

import com.dong.beta.dao.domain.ParseRule;
import com.dong.beta.dao.domain.ParseRuleCriteria;
import com.dong.beta.dao.mapper.ParseRuleExtendMapper;
import com.dong.beta.dao.mapper.ParseRuleMapper;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dzq
 * @date 2019/06/28
 */

@SpringBootApplication(scanBasePackages = "com.dong.beta")
@MapperScan("com.dong.beta.dao.mapper")
@RestController
@EnableScheduling
public class BetaWebApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(BetaWebApplication.class);


    @Autowired
    ParseRuleExtendMapper parseRuleExtendMapper;

    @GetMapping("test")
    public List<ParseRule> test(){
        ParseRuleCriteria criteria = new ParseRuleCriteria();
        ParseRuleCriteria.Criteria c = criteria.createCriteria();
        c.andRuleIdEqualTo(2436);
        c.andFieldIdLessThan(410);
        return parseRuleExtendMapper.selectByExample(criteria);
    }

    @GetMapping("update")
    public void update(){
        parseRuleExtendMapper.updateRuleStatus(3, 300024);
        logger.info("update success");
    }

    public static void main(String[] args) {
        SpringApplication.run(BetaWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ParseRuleCriteria criteria = new ParseRuleCriteria();
        ParseRuleCriteria.Criteria c = criteria.createCriteria();
        c.andRuleIdEqualTo(2436);
        c.andFieldIdLessThan(410);
        List<ParseRule> ruleList = parseRuleExtendMapper.selectByExample(criteria);
        logger.debug(ruleList.toString());
    }
}