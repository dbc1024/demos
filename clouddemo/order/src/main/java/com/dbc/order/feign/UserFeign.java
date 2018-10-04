package com.dbc.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user")
@RequestMapping("user")
public interface UserFeign {

    @GetMapping("get/{id}")
    String findUserById(@PathVariable("id") Integer id);
}
