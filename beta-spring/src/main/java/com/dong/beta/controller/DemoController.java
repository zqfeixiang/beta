package com.dong.beta.controller;

import com.dong.aop.DLock;
import com.dong.beta.annotation.RepeatSubmit;
import com.dong.beta.controller.domain.ParseRule;
import com.dong.beta.controller.vo.BondCodeVo;
import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.enu.RoutingKey;
import com.dong.beta.rabbit.Producer;
import com.dong.beta.service.AsyncService;
import com.dong.beta.service.DemoService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Api(tags="Demo")
@RestController
@RequestMapping("demo")
@Slf4j
public class DemoController {

    static Integer c = 0;
    //    @Resource(name = "demoservice2")
    @Autowired
    private DemoService demoService;
    @Autowired
    AsyncService asyncService;

    @Autowired
    Producer producer;

    @Autowired
    Redisson redisson;

    @Autowired
    StringRedisTemplate redisTemplate;

    @ApiOperation("test RabbitMQ")
    @GetMapping("/rabbit")
    public ResponseModel<String> testRabbitMQ(){
        producer.produceWorkQueue();
        return ResponseModel.successResponse("Message sent success!");
    }

    @ApiOperation("test fanout exchange")
    @GetMapping("/fanoutExchange")
    public ResponseModel<String> testFanoutExchange(){
        producer.sendFanoutExchange();
        return ResponseModel.successResponse("Message sent success!");
    }

    @ApiOperation("test direct exchange")
    @PostMapping("/directExchange")
    public ResponseModel<String> testDirectExchange(@RequestParam("message") String message,
                                                    @RequestParam("routingKey") RoutingKey routingKey){
        producer.sendDirectExchange(message, routingKey);
        return ResponseModel.successResponse("Message sent success!");
    }

    @ApiOperation("test topic exchange")
    @PostMapping("/topicExchange")
    public ResponseModel<String> testTopicExchange(@RequestParam("message") String message,
                                                    @RequestParam("routingKey") String routingKey){
        producer.sendTopicExchange(message, routingKey);
        return ResponseModel.successResponse("Message sent success!");
    }

    @ApiOperation("test redis")
    @GetMapping("/redis")
    public ResponseModel<String> testRedis(){
        String result = null;
        String lockKey = "dong";
        RLock redissonLock = redisson.getLock(lockKey);

        try {
            redissonLock.lock();
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0){
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                log.info("扣减库存成功，剩余:{}", realStock);
            }else {
                log.info("库存不足");
            }
        }finally {
            redissonLock.unlock();
        }

        return ResponseModel.successResponse(result);
    }

    @ApiOperation("test bonds")
    @GetMapping("/bonds")
    public ResponseModel<List<BondCodeVo>> getBonds() {
        System.out.println(demoService.getClass().getName());
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

    @ApiOperation("test async")
    @PostMapping("/async")
    public void async() {
        asyncService.executeAsync();
    }

    @ApiOperation("test")
    @GetMapping("test")
    public List<ParseRule> test() {
        return demoService.test();
    }

}
