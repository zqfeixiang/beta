package com.dong.beta.biz.service.impl;

import com.dong.beta.biz.service.DemoService;
import com.dong.beta.dao.domain.ParseRule;
import com.dong.beta.dao.domain.ParseRuleCriteria;
import com.dong.beta.dao.mapper.ParseRuleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
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
