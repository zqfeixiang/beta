package com.dong.beta.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dong.beta.config.ArticleConfig;
import com.dong.beta.controller.domain.BatchTaskDispatchLog;
import com.dong.beta.controller.request.BatchTaskDispatchLogRequest;
import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.entity.Article;
import com.dong.beta.mapper.PartyDoMapper;
import com.dong.beta.mapper.UserDao;
import com.dong.beta.service.BatchTaskDispatchLogService;
import com.dong.beta.utils.DateUtil;
import com.dong.jedis.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Api(tags = "test管理")
@RestController
@Slf4j
public class TestController {

    @Autowired
    TestController testController;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    JedisPoolUtils jedisPool;

    @Autowired
    BatchTaskDispatchLogService batchTaskDispatchLogService;

    @Autowired
    PartyDoMapper partyDoMapper;

    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ApiOperation("put version")
    @PutMapping("/updateVersion")
    public String updateVersion(){
        log.info("updateVersion");
        partyDoMapper.updateVersion(100);
        return "success";
    }

    @ApiOperation(("test Date post method"))
    @PostMapping("/selectByLogTimeDate")
    public ResponseModel<List<BatchTaskDispatchLog>> getLog(@RequestBody BatchTaskDispatchLogRequest request){
        log.info("selectByLogTimeDate logTime:{}", request.getLogTime());
        List<BatchTaskDispatchLog> batchTaskDispatchLogs = batchTaskDispatchLogService.selectByLogTime(request.getLogTime());

        return  ResponseModel.successResponse(batchTaskDispatchLogs);
    }

    @ApiOperation(("test string Date get method"))
    @GetMapping("/selectByLogTimeStr")
    public ResponseModel<List<BatchTaskDispatchLog>> getLog(@RequestParam("logTime") String logTime){
        log.info("selectByLogTimeStr logTime:{}", logTime);
        List<BatchTaskDispatchLog> batchTaskDispatchLogs = batchTaskDispatchLogService.selectByLogTimeStr(logTime);
        return  ResponseModel.successResponse(batchTaskDispatchLogs);
    }

    @ApiOperation(("test Date get method"))
    @GetMapping("/getAllLog")
    public ResponseModel<List<BatchTaskDispatchLog>> getAllLog(){
        List<BatchTaskDispatchLog> batchTaskDispatchLogs = batchTaskDispatchLogService.getAllLog();
        return  ResponseModel.successResponse(batchTaskDispatchLogs);
    }

    @ApiOperation("test jedis")
    @GetMapping("/jedis")
    public String testJedis(){
        Jedis jedis = jedisPool.getJedis();
        jedis.set("name", "jedis");
        log.info(jedis.get("name"));
        return jedis.get("name");
    }

    @ApiOperation("test redis")
    @GetMapping("/redis")
    public void testRedis(){
        Integer count = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
        redisTemplate.opsForValue().decrement("stock", 2);
        log.info("count:{}", count);
    }

    @ApiOperation("test Transactional")
    @GetMapping("/testTrans")
    @Transactional
    public void testTrans(){
        jdbcTemplate.execute("insert into t_test values(5, '55')");
        testController.b();
        throw new NullPointerException("error");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void b(){
        jdbcTemplate.execute("insert into t_test values(6, '666')");
//        throw new NullPointerException("error");
    }

    @ApiOperation("test jackson")
    @GetMapping("/testJackson")
    public ResponseModel<Object> testJackson(){
        Article book = new Article();
        JSONObject jsonObject = null;
        try {
            String s = objectMapper.writeValueAsString(book);
            jsonObject = JSON.parseObject(s);
            log.info("===s: " + s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseModel.successResponse(jsonObject);
    }

}
