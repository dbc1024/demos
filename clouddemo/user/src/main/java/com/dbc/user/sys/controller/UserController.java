package com.dbc.user.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbc.user.sys.entity.User;
import com.dbc.user.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dbc
 * @since 2018-11-12
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/detail/{id}")
    public User detail(@PathVariable Integer id){

        User user = userService.getById(id);

        return user;
    }


    @PostMapping("/page")
    public Object page(@RequestBody Map<String, String> params){

        IPage<User> page = new Page<User>(1, 10);

        LambdaQueryWrapper<User> entity = new LambdaQueryWrapper<>();
        entity.like(User::getName, params.get("name"));

        IPage<User> userList = userService.page(page, entity);

        return userList;
    }

}

