package com.dbc.user.controller;

import com.dbc.user.model.User;
import com.dbc.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("get/{id}")
    public User findById(@PathVariable Integer id){

        User users = userRepository.findById(id).get();


        return users;
    }
}
