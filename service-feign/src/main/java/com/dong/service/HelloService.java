package com.dong.service;

import com.dong.fallback.MyFallBack;
import com.dong.fallback.MyFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dzq
 * @date 2021/12/3 7:33 下午
 *
 * 加上 fallback 可以实现远程服务发生异常后进行服务的熔断，但是不能获取到远程服务的异常信息
 * 要获取远程服务的异常信息，可以使用 fallbackFactory.
 */
@FeignClient(value = "eureka-provider", /*fallback = MyFallBack.class*/ fallbackFactory = MyFallbackFactory.class)
public interface HelloService {

    @RequestMapping("/provider")
    public String hello();
}
