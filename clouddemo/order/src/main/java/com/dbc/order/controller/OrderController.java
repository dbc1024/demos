package com.dbc.order.controller;

import com.dbc.order.feign.UserFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private UserFeign userFeign;

    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
                @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//设置熔断
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//滚动时间窗口中，最小请求次数
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//熔断持续时间窗（超过此时间会重新尝试发起请求）
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//滚动时间窗口中，请求失败率
    })
    @GetMapping("user/{id}")
    public Object getUserTest(@PathVariable Integer id){

        if(id%2==0){
            return "success，直接返回，不进行服务调用，不触发熔断";
        }

        String user = userFeign.findUserById(id);
        System.out.print(user);
        return user;
    }

    private Object fallback(Integer id){//必须与被降级方法有自相同的入参，否则会报找不到方法

        return "太拥挤了，请稍后再试~~~~~";
    }
}

