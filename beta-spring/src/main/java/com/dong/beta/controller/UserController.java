package com.dong.beta.controller;

import com.dong.beta.annotation.ControllerWebLog;
import com.dong.beta.config.ArticleConfig;
import com.dong.beta.controller.domain.Users;
import com.dong.beta.controller.vo.PageVo;
import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.entity.DataListDTO;
import com.dong.beta.service.CacheService;
import com.dong.beta.service.impl.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final CacheService cacheService;

    @Autowired
    public UserController(UserService userService, CacheService cacheService) {
        this.userService = userService;
        this.cacheService = cacheService;
    }

    @ApiOperation("返回用户列表")
    @GetMapping("/listAllUsers")
    public ResponseModel<PageVo<Map<String, String>>> listAllUsers(@ApiParam(value = "当前页") @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage,
                                                                   @ApiParam(value = "每页大小") @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize){
        List<Map<String, String>> userNameMap = cacheService.queryUserName();
        PageVo pageVo = new PageVo(userNameMap, currPage, pageSize);
        return ResponseModel.successResponse(pageVo);
    }

    @ApiOperation("根据用户名查询")
    @GetMapping("/queryByUserName")
    @ControllerWebLog(name = "根据用户名查询用户", intoDb = true)
    public ResponseModel<List<Users>> queryByUserName(@ApiParam(value = "用户名") @RequestParam("username") String username) {
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

    @ApiOperation("根据登录时间查询")
    @GetMapping("/queryByLoginTime")
    public ResponseModel<List<Users>> queryByLoginTime(@ApiParam(value = "登录时间") @RequestParam("loginTime") Date loginTime) {
        Assert.notNull(loginTime, "loginTime can not be null");
        List<Users> list = userService.selectLoginTime(loginTime);
        if (CollectionUtils.isEmpty(list)){
            return ResponseModel.successResponse(Collections.EMPTY_LIST);
        }
        DataListDTO dataListDTO = new DataListDTO();
        dataListDTO.setDataList(list);
        dataListDTO.setSize(list.size());
        return ResponseModel.successResponse(list);
    }

    @ApiOperation("根据用户名删除")
    @DeleteMapping("/deleteByUserName")
    @ControllerWebLog(name = "删除用户", intoDb = true)
    public ResponseModel<String> testDelete(@RequestParam("username") String username) {
        userService.deleteService(username);
        return ResponseModel.successResponse(String.format("%s删除成功", username));
    }

    @ApiOperation("根据用户名更新")
    @PutMapping("/updateByUserName")
    @ControllerWebLog(name = "更新用户", intoDb = true)
    public ResponseModel<Users> update(@RequestBody Users users){
        DataListDTO dataListDTO = new DataListDTO();
        dataListDTO.setDataList(userService.updateByUserName(users));
        return ResponseModel.successResponse(dataListDTO);
    }
}