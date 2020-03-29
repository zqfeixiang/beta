package com.dong.beta.biz.service.impl;

import com.dong.beta.dao.domain.User;
import com.dong.beta.dao.domain.Users;
import com.dong.beta.dao.domain.UsersCriteria;
import com.dong.beta.dao.mapper.UserDao;
import com.dong.beta.dao.mapper.UsersMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
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
    public List<Users> selectUserByName(String userName) {
        UsersCriteria example = new UsersCriteria();
        UsersCriteria.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(userName)){
            userName = "%" + userName + "%";
            criteria.andUsernameLike(userName);
        }
        List<Users> userList = usersMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userList)){
            return userList;
        }
        return null;
    }


    /**
     * 插入两个用户
     */
    public void insertService() {
        userDao.insertUser("SnailClimb", 22, 3000.0);
        userDao.insertUser("Daisy", 19, 3000.0);
    }

    /**
     * 根据id 删除用户
     */

    public void deleteService(int id) {
        userDao.deleteUser(id);
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
