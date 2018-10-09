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

        /**
         * 熔断测试思路：
         *  先停掉user服务，所以在调用user服务必然会失败，从而会触发法务降级，从而调用fallback。
         *  此时id为2的倍数的请求不会调用user服务，所以是直接返回提示字符串。
         *
         *  接着连续发起id不为2倍数的请求，让其不断失败调用user服务，达到熔断条件，触发熔断。
         *  此时再发起id为2的倍数的请求，根本不会进入此方法了，也只能调用fallback方法。
         */
        if(id%2==0){
            return "success，直接返回，不进行服务调用，不触发熔断";
        }

        String user = userFeign.findUserById(id);
        System.out.print(user);
        return user;
    }

    private Object fallback(Integer id){//必须与被降级方法有相同的入参，否则会报找不到该降级方法

        return "太拥挤了，请稍后再试~~~~~";
    }
}

