package com.dong.beta.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.entity.Article;
import com.dong.beta.mapper.UserDao;
import com.dong.jedis.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@Api(tags = "test管理")
@RestController
@Slf4j
public class TestController {

    @Autowired
    TestController testController;

    @Autowired
    private UserDao userDao;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    JedisPoolUtils jedisPool;


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
        Integer count = Integer.parseInt(redisTemplate.opsForValue().get("name"));
        redisTemplate.opsForValue().decrement("name", 2);
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
