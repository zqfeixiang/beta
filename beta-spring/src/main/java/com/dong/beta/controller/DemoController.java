package com.dong.beta.controller;

import com.dong.beta.controller.domain.ParseRule;
import com.dong.beta.controller.vo.BondCodeVo;
import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.service.AsyncService;
import com.dong.beta.service.DemoService;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("demo")
public class DemoController {

    static Integer c = 0;
    @Autowired
    private DemoService demoService;
    @Autowired
    AsyncService asyncService;

    @RequestMapping("/bonds")
    public ResponseModel<List<BondCodeVo>> getBonds(){
        List<BondCodeVo> list = Lists.newArrayList();
        BondCodeVo vo1 = new BondCodeVo();
        vo1.setSecuridMain("B100001.XCIB");
        vo1.setCorpName("国债1号");
        vo1.setBondPrice(new BigDecimal(1000));
        BondCodeVo vo2 = new BondCodeVo();
        vo2.setSecuridMain("1000001.XSHE");
        vo2.setCorpName("国债2号");
        vo2.setBondPrice(new BigDecimal(1001));
        list.add(vo1);
        list.add(vo2);
        return ResponseModel.successResponse(list);
    }


    @RequestMapping("/async")
    public void async(){
        asyncService.executeAsync();
    }

    @RequestMapping("/stat")
    public Integer stat(){
        return c;
    }

    @RequestMapping("/add")
    public Integer add(){
        c++;
        return 1;
    }


    @GetMapping("test")
    public List<ParseRule> test(){
        return demoService.test();
    }
    @GetMapping("test2")
    public String test2(){
        return "dong";
    }
}
