package com.dong.beta.strategy.controller;

import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.strategy.enums.InsertTypeEnum;
import com.dong.beta.strategy.service.InsertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api(tags = "insert strategy")
@RestController
@Slf4j
public class InsertController {

    @Autowired
    InsertService insertService;

    @ApiOperation("insert")
    @PostMapping("/insert")
    public ResponseModel<String> insert(@RequestParam(value = "type") List<InsertTypeEnum> type,
                                        @RequestParam(value = "date") String date){
        try {
            type.forEach(insertTypeEnum -> {
                insertService.insert(insertTypeEnum, date);
            });
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return ResponseModel.successResponse(type);
    }
}
