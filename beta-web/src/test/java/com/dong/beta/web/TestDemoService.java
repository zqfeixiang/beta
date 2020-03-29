package com.dong.beta.web;

import com.dong.beta.dao.domain.ParseRule;
import com.dong.beta.dao.domain.ParseRuleCriteria;
import com.dong.beta.dao.mapper.ParseRuleMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BetaWebApplication.class)
public class TestDemoService {

    @Autowired
    ParseRuleMapper parseRuleMapper;

    @Test
    public void test(){
        ParseRuleCriteria criteria = new ParseRuleCriteria();
        ParseRuleCriteria.Criteria c = criteria.createCriteria();
        c.andRuleIdEqualTo(2436);
        c.andFieldIdLessThan(410);
        List<ParseRule> list = parseRuleMapper.selectByExample(criteria);
        System.out.println(list);
    }
}
