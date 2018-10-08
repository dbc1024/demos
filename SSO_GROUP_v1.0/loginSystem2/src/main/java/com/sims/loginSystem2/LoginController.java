package com.sims.loginSystem2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.sims.loginSystem2.util.HttpUtil;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/checkLogin")
	public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException{
	
		
		//这里必须用重定向，要为后面再次访问sso获取session做准备
		return "redirect:http://www.sso.com:8080/login/checkLogin?domain=www.fx.com:8082&username="+ username +"&password="+ password;
	}
	
	@RequestMapping("/receiveToken")
	public String receiveToken(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		String token = request.getParameter("token");
		String loginUser = request.getParameter("loginUser");
		
		
		JSONObject jsonResult = HttpUtil.httpClient("http://www.sso.com:8080/login/verifyToken?token="+ token);
		String success = jsonResult.get("success").toString();
		if("true".equals(success)){
			
			
			session.setAttribute("loginUser", loginUser);
			
			return "redirect:http://www.fx.com:8082/home";
		}
		
		return "redirect:http://www.fx.com:8082/login.html";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		return "redirect:http://www.sso.com:8080/login/logout?domain=www.fx.com:8082";
	}
	
	@RequestMapping("/ssoLogout")
	public String ssoLogout(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:http://www.sso.com:8080/login/logout?domain=www.fx.com:8082";
	}
	
	
}
