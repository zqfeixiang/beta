package com.dong.beta.dao.mapper;

import com.dong.beta.dao.domain.Users;
import com.dong.beta.dao.domain.UsersCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {
    long countByExample(UsersCriteria example);

    int deleteByExample(UsersCriteria example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersCriteria example);

    Users selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersCriteria example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersCriteria example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}