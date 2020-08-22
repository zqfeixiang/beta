package com.dong.beta.mapper;


import com.dong.beta.controller.domain.Users;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {

    List<Users> selectByUserName(@Param("userName") String userName);

    void updateByUserName(@Param("users") Users users);
}