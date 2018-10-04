package com.its.sims.controller;

import com.its.sims.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by csz on 2017/8/21.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IStudentService studentService;

    @RequestMapping("/main")
    public ModelAndView mainView(HttpServletRequest request) {
        Object user = null;

        Object sessionUser = request.getSession().getAttribute("sessionUser");


        ModelAndView mv = new ModelAndView("common/main");
        mv.addObject("user", sessionUser);

        return mv;
    }


}
