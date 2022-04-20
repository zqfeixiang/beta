package com.dong.beta.controller;

import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.factoryStrategy.model.Stock;
import com.dong.beta.factoryStrategy.service.RankService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "Factory strategy pattern")
@RestController
@Slf4j
public class FactoryStrategyController {

    final RankService rankService;

    public FactoryStrategyController(RankService rankService) {
        this.rankService = rankService;
    }

    @ApiOperation("rank")
    @PostMapping("/rank")
    public ResponseModel<List<Stock>> insert(@RequestParam(value = "rankType") String rankType){
        List<Stock> stockList = rankService.getRank(rankType);
        return ResponseModel.successResponse(stockList);
    }
}
