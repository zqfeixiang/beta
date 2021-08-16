package com.dong.beta.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dong.beta.controller.domain.User;
import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.entity.Article;
import com.dong.beta.mapper.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private UserDao userDao;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/testGetAll")
    public ResponseModel<List<User>> testGetAll() {
        List<User> allUser = userDao.findAllUser();
        return ResponseModel.successResponse(allUser);
    }

    @GetMapping("/testJackson")
    public ResponseModel<String> testJackson(){
        Article book = new Article();
        String s = null;
        JSONObject jsonObject = null;
        try {
            s = objectMapper.writeValueAsString(book);
            jsonObject = JSON.parseObject(s);
            System.out.println("===s: " + s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseModel.successResponse(jsonObject);
    }

}
