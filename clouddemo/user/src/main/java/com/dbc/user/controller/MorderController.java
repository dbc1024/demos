package com.dbc.user.controller;


import com.dbc.user.model.Morder;
import com.dbc.user.service.IMorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dbc
 * @since 2018-11-12
 */
@RestController
@RequestMapping("/morder")
public class MorderController {

    @Autowired
    private IMorderService morderService;

    @GetMapping("/detail/{id}")
    public Morder detail(@PathVariable Long id){

        Morder morder = morderService.getById(id);

        return morder;
    }

}

