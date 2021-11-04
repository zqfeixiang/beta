package com.dong.beta.mapper;


import com.dong.beta.controller.domain.Users;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UsersMapper {

    List<Users> selectByUserName(@Param("username") String username);

    List<Users> selectByLoginTime(@Param("loginTime") Date loginTime);

    void updateByUserName(@Param("users") Users users);

    void deleteByUserName(@Param("username") String username);

    List<Map<String, String>> getUserIdNameMap();
}