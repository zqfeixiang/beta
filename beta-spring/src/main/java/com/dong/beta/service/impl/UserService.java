package com.dong.beta.service.impl;


import com.dong.beta.controller.domain.Users;
import com.dong.beta.mapper.UserDao;
import com.dong.beta.mapper.UsersMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    UsersMapper usersMapper;

    /**
     * 根据名字查找用户
     */
    @Cacheable(cacheNames = {"users"} , keyGenerator = "myKeyGenerator")
    public List<Users> selectUserByName(String userName) {
        List<Users> userList = usersMapper.selectByUserName(userName);
        if (!CollectionUtils.isEmpty(userList)){
            return userList;
        }
        return null;
    }


    public void updateByUserName(Users users){
        usersMapper.updateByUserName(users);
    }
    /**
     * 插入两个用户
     */
    public void insertService() {
        userDao.insertUser("SnailClimb", 22, 3000.0);
        userDao.insertUser("Daisy", 19, 3000.0);
    }

    /**
     * 根据username 删除用户
     */

    public void deleteService(String username) {
        userDao.deleteUser(username);
    }

    /**
     * 模拟事务。由于加上了 @Transactional注解，如果转账中途出了意外 SnailClimb 和 Daisy 的钱都不会改变。
     */
    @Transactional
    public void changemoney() {
        userDao.updateUser("SnailClimb", 22, 2000.0, 3);
        // 模拟转账过程中可能遇到的意外状况
        int temp = 1 / 0;
        userDao.updateUser("Daisy", 19, 4000.0, 4);
    }
}
