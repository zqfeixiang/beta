package com.dong.beta.service.impl;

import com.dong.beta.controller.domain.ParseRule;
import com.dong.beta.controller.domain.ParseRuleCriteria;
import com.dong.beta.mapper.ParseRuleMapper;
import com.dong.beta.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Autowired
    ParseRuleMapper parseRuleMapper;

    @Override
    public List<ParseRule> test() {
        ParseRuleCriteria criteria = new ParseRuleCriteria();
        ParseRuleCriteria.Criteria c = criteria.createCriteria();
        c.andRuleIdEqualTo(2436);
        c.andFieldIdLessThan(410);
        return parseRuleMapper.selectByExample(criteria);
    }
}
