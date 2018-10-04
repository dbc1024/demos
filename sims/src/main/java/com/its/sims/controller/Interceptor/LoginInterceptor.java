package com.its.sims.controller.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by csz on 2017/8/21.
 */
public class LoginInterceptor implements HandlerInterceptor{

    private static final String LOGIN_URL = "/login/loginPage";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Cookie[] cookies = request.getCookies();
//
//        if(cookies != null){
//            for (Cookie cookie:cookies) {
//                if("cookieUser".equals(cookie.getName())){
//                    return true;
//                }
//
//            }
//        }

        Object sessionUser = request.getSession().getAttribute("sessionUser");
        if(sessionUser != null){
            return true;
        }


        response.sendRedirect(LOGIN_URL);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
