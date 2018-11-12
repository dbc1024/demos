/*package com.ectrip.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.ectrip.base.service.iservice.IGenericService;
import com.opensymphony.xwork2.ActionSupport;

*//**
 * 后台根据读取的服务商判断该用户是否有管理该服务商的信息 
 * Copy Right Information : Ectrip
 * Package : com.ectrip.common.action ClassName
 * BaseAction.java JDK version used : jdk1.5
 * User : 李进 Version :
 * Modification history :2009-3-21 下午12:52:50
 * 2012-07-09 增加了ACTION注释
 *//*
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;
	*//** 当前处理是增加业务 *//*
	public static final int ADD = 1;
	*//** 当前处理是编辑业务 *//*
	public static final int EDIT = 2;
	*//** 当前处理是删除业务 *//*
	public static final int DELETE = 3;
	*//** 当前处理是查看业务 *//*
	public static final int VIEW = 4;
	*//** 当前处理是审核业务 *//*
	public static final int AUDIT = 5;
	*//** 用户名 *//*
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	*//**
	 * 用户密码
	 *//*
	private String password;
	*//**
	 * 用户当前登录凭据
	 *//*
	private String ticket;
	*//**
	 * 当前验证登录验证地址
	 *//*
	private String service;
	*//**
	 * 通用的 genericService 服务，可以在ACTION中直接进行调用
	 * 在进行简单的处理时，可以通过个SERVICE进行数据读取，最好不要用这个
	 * 服务进行数据更改
	 *//*
	public IGenericService genericService;

	public void setGenericService(IGenericService genericService) {
		this.genericService = genericService;
	}

	// 1 新增 2 修改 3 删除 4 查看 5 审核
	public int strutsAction;

	public int getStrutsAction() {
		return strutsAction;
	}

	public void setStrutsAction(int strutsAction) {
		this.strutsAction = strutsAction;
	}

	// request response
	private HttpServletRequest request;
	private HttpServletResponse response;

	// 这是基类

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	*//**
	 * action 这里下存放ACTION名称
	 *//*
	public String actionName;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}
*/