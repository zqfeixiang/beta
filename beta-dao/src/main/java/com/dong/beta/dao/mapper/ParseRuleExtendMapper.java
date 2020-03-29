package com.dong.beta.dao.mapper;

import com.dong.beta.dao.domain.ParseRule;
import com.dong.beta.dao.domain.ParseRuleCriteria;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParseRuleExtendMapper extends ParseRuleMapper{

    int updateRuleStatus(@Param("rule_status") int status, @Param("id") int id);
}