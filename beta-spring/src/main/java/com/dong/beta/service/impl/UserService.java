package com.dong.beta.service.impl;


import com.dong.beta.controller.domain.Users;
import com.dong.beta.mapper.UserDao;
import com.dong.beta.mapper.UsersMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;
    private final UsersMapper usersMapper;

    public UserService(UserDao userDao, UsersMapper usersMapper) {
        this.userDao = userDao;
        this.usersMapper = usersMapper;
    }

    /**
     * 根据名字查找用户
     *  keyGenerator = "myKeyGenerator"
     */
    @Cacheable(cacheNames = "users", key = "#userName")
    public List<Users> selectUserByName(String userName) {
        LOGGER.info("query by username:{}", userName);
        return usersMapper.selectByUserName(userName);
    }

    public List<Users> selectLoginTime(Date loginTime) {
        return usersMapper.selectByLoginTime(loginTime);
    }

    @CachePut(/*value = "users", */key = "#users.username")
    public List<Users> updateByUserName(Users users){
        usersMapper.updateByUserName(users);
        return usersMapper.selectByUserName(users.getUsername());
    }

    /**
     * 根据username 删除用户
     * beforeInvocation: true   代表清除缓存操作是在方法执行之前，无论方法是否出现异常，缓存都清除
     */
    @CacheEvict(/*value = "users", */key = "#username", beforeInvocation = true)
    public void deleteService(String username) {
//        userDao.deleteUser(username);
        usersMapper.deleteByUserName(username);
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
