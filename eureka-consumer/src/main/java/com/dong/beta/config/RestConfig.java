package com.dong.beta.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author dzq
 * @date 2021/12/3 2:15 下午
 */
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 覆盖掉 Ribbon 默认的轮询负载均衡策略
     * @return
     */
    @Bean
    public IRule iRule(){
        return new RoundRobinRule();
    }
}
