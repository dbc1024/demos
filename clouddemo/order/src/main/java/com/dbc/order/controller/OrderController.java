package com.dbc.order.controller;

import com.dbc.order.feign.UserFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("order")
@RestController
public class OrderController {

    @Resource
    private UserFeign userFeign;

    @GetMapping("user/{id}")
    public Object getUserTest(@PathVariable Integer id){

        String user = userFeign.findUserById(id);
        System.out.print(user);
        return user;
    }
}
