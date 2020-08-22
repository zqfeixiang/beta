package com.dong.beta.mapper;

import org.apache.ibatis.annotations.Param;

public interface ParseRuleExtendMapper extends ParseRuleMapper {

    int updateRuleStatus(@Param("rule_status") int status, @Param("id") int id);
}