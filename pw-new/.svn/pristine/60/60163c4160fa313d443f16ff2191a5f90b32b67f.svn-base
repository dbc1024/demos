package com.ectrip.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {
	public static final int ADD = 1;
	public static final int EDIT = 2;
	public static final int DELETE = 3;
	public static final int SUCCESS_CODE_200 = 200;
	public static final int ERROR_CODE_400 = 400;
	public static final int PAGE_SIZE = 20;
	/**
	 * 
	* @Title: getRequest  
	* @Description: TODO 获取Request实例
	* @param @return    参数  
	* @return HttpServletRequest    返回类型  
	* @throws
	 */
	protected HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request;
	}
	/**
	 * 
	* @Title: getReponse  
	* @Description: TODO 获取Response实例
	* @param @return    参数  
	* @return HttpServletRequest    返回类型  
	* @throws
	 */
	protected HttpServletResponse getReponse() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = requestAttributes.getResponse();
		return response;
	}
}
