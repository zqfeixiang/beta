package com.dong.beta.mapper;


import com.dong.beta.controller.domain.ParseRule;
import com.dong.beta.controller.domain.ParseRuleCriteria;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParseRuleMapper {
    long countByExample(ParseRuleCriteria example);

    int deleteByExample(ParseRuleCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ParseRule record);

    int insertSelective(ParseRule record);

    List<ParseRule> selectByExample(ParseRuleCriteria example);

    ParseRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ParseRule record, @Param("example") ParseRuleCriteria example);

    int updateByExample(@Param("record") ParseRule record, @Param("example") ParseRuleCriteria example);

    int updateByPrimaryKeySelective(ParseRule record);

    int updateByPrimaryKey(ParseRule record);

    int updateRuleStatus(@Param("rule_status") int status, @Param("id") int id);
}