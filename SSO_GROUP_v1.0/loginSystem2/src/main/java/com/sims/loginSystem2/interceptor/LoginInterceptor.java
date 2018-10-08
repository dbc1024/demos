package com.sims.loginSystem2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		HttpSession session = request.getSession();
		
//		long currentTime = System.currentTimeMillis();
//		long creationTime = session.getCreationTime();
//		long lastAccessedTime = session.getLastAccessedTime();
//		long cha = currentTime - lastAccessedTime;
		
		if(session.getAttribute("loginUser") == null){
			
			response.sendRedirect("http://www.sso.com:8080/login/checkLogin?domain=www.fx.com:8082");
			return false;
		}
		
		
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
