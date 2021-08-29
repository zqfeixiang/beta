package com.dong.beta.controller;

import com.dong.beta.annotation.ControllerWebLog;
import com.dong.beta.config.ArticleConfig;
import com.dong.beta.controller.domain.Users;
import com.dong.beta.controller.vo.PageVo;
import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.entity.DataListDTO;
import com.dong.beta.service.CacheService;
import com.dong.beta.service.impl.UserService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    ArticleConfig articleConfig;

    @Autowired
    CacheService cacheService;

    @RequestMapping("/test")
    public void test(){
        System.out.println(articleConfig);
    }

    @GetMapping("/listAllUsers")
    public ResponseModel<PageVo<Map<String, String>>> listAllUsers(@RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage,
                                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize){
        List<Map<String, String>> userNameMap = cacheService.queryUserName();
        PageVo pageVo = new PageVo(userNameMap, currPage, pageSize);
        return ResponseModel.successResponse(pageVo);
    }

    @RequestMapping("/queryByUserName")
    public ResponseModel<List<Users>> queryByUserName(@Param("username") String username) {
        Assert.notNull(username, "username can not be null");
        List<Users> list = userService.selectUserByName(username);
        if (CollectionUtils.isEmpty(list)){
            return ResponseModel.successResponse(Collections.EMPTY_LIST);
        }
        DataListDTO dataListDTO = new DataListDTO();
        dataListDTO.setDataList(list);
        dataListDTO.setSize(list.size());
        return ResponseModel.successResponse(list);
    }

    @RequestMapping("/deleteByUserName")
    @ControllerWebLog(name = "删除用户", intoDb = true)
    public ResponseModel<String> testDelete(@RequestParam("username") String username) {
        userService.deleteService(username);
        return ResponseModel.successResponse(String.format("%s删除成功", username));
    }

    @RequestMapping("/updateByUserName")
    @ControllerWebLog(name = "更新用户", intoDb = true)
    public ResponseModel<Users> update(@RequestBody Users users){
        DataListDTO dataListDTO = new DataListDTO();
        dataListDTO.setDataList(userService.updateByUserName(users));
        return ResponseModel.successResponse(dataListDTO);
    }
}