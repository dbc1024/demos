package com.hqyt.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyt.listener.MySessionContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="tags的内容",description="description的内容")
@RestController
@RequestMapping("test")
public class TestController {
	
	
	@ApiOperation("testRedirect")
	@GetMapping("testRedirect")
	Object testRedirect(HttpServletResponse response) throws IOException {
		
		response.sendRedirect("https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=6221886712000985403&cardBinCheck=true");
		
		return "success";
	}
	
	@ApiOperation("初始化session")
	@GetMapping("initsession")
	Object initsession(HttpSession session) {
		
		session.setAttribute("loginUser", "admin");
		
		
		String sessionId = session.getId();
		long creationTime = session.getCreationTime();
		long lastAccessedTime = session.getLastAccessedTime();
		int maxInactiveInterval = session.getMaxInactiveInterval();
		
		Map<String, Object> sessionInfoMap = new HashMap<>();
		sessionInfoMap.put("sessionId", sessionId);
		sessionInfoMap.put("creationTime", creationTime);
		sessionInfoMap.put("lastAccessedTime", lastAccessedTime);
		sessionInfoMap.put("maxInactiveInterval", maxInactiveInterval);
		
		return sessionInfoMap;
	}
	
	
	@ApiOperation("查看session信息")
	@GetMapping("readSession")
	Object readSession(HttpSession session) {
		
		Object loginUser = session.getAttribute("loginUser");
		
		String sessionId = session.getId();
		long creationTime = session.getCreationTime();
		long lastAccessedTime = session.getLastAccessedTime();
		int maxInactiveInterval = session.getMaxInactiveInterval();
		
		Map<String, Object> sessionInfoMap = new HashMap<>();
		sessionInfoMap.put("sessionId", sessionId);
		sessionInfoMap.put("creationTime", creationTime);
		sessionInfoMap.put("lastAccessedTime", lastAccessedTime);
		sessionInfoMap.put("maxInactiveInterval", maxInactiveInterval);
		sessionInfoMap.put("loginUser", loginUser);
		
		
		return sessionInfoMap;
	}
	
	
	@ApiOperation("根据sessionid销毁session")
	@GetMapping("destorySession")
	String destorySession(String sessionId, HttpServletRequest request) {
		
		HttpSession session = MySessionContext.getSession(sessionId);
		String id = session.getId();
		session.invalidate();
		
		return "已销毁session:"+ id;
	}
	
	
	
	@ApiOperation("获取系统ip")
	@GetMapping("ip")
	String sayHello() throws UnknownHostException {
		
		InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
		String hostAddress = address.getHostAddress();//192.168.0.121 
		
		return hostAddress;
	}
	
	@ApiOperation("Header操作")
	@GetMapping("header")
	String handleHeader(HttpServletRequest request, HttpServletResponse response) {
		String getHeader = request.getHeader("myHeader");
		System.out.println(getHeader);
		
		Enumeration<String> getHeaders = request.getHeaders("Cookie");
		System.out.println(getHeaders);
		
		Enumeration<String> headerNames = request.getHeaderNames();
		System.out.println(headerNames);
		
		response.addHeader("myTestHeader", "header1.0.0");
		
		return "headerTest";
	}
	
	@ApiOperation("获取系统时间")
	@GetMapping("time")
	Object systemTime() {
		
		Map<String, Object> time = new HashMap<>();
		
		Date now = new Date();
		
		time.put("beforeFormat", now);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String afterFormat = df.format(now);
		
		time.put("afterFormat", afterFormat);
		
		
		return time;
	}
}
