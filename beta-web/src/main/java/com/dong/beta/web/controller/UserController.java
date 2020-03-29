package com.dong.beta.web.controller;

import com.dong.beta.biz.service.impl.UserService;
import com.dong.beta.dao.domain.User;
import com.dong.beta.dao.domain.Users;
import com.dong.beta.web.controller.vo.ResponseModel;
import com.dong.beta.web.entity.DataListDTO;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public ResponseModel<List<Users>> testQuery(@Param("username") String username) {
        List<Users> list = userService.selectUserByName(username);
        DataListDTO dataListDTO = new DataListDTO();
        dataListDTO.setDataList(list);
        dataListDTO.setSize(list.size());
        return ResponseModel.successResponse(dataListDTO);
    }

    @RequestMapping("/delete")
    public String testDelete() {
        userService.deleteService(3);
        return "OK";
    }

}