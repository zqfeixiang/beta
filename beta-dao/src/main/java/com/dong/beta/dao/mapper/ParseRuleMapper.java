package com.dong.beta.dao.mapper;

import com.dong.beta.dao.domain.ParseRule;
import com.dong.beta.dao.domain.ParseRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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