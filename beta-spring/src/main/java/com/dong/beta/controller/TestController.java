package com.dong.beta.controller;

import com.dong.beta.controller.domain.User;
import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/testGetAll")
    public ResponseModel<List<User>> testGetAll() {
        List<User> allUser = userDao.findAllUser();
        return ResponseModel.successResponse(allUser);
    }

}
