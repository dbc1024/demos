package com.ectrip.sys.backuplogin.controller;

import java.util.Map;

import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.service.ISyslogService;

/**
 * 后台退出 如果点返回到前台,将返回首页否则返回到后登录页.
 * 
 * @author LiJin
 * 
 */
public class LoginoutAction{/*
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = -4804982311752587784L;
	String backgroup;
	private ISyslogService syslogService;
	public ISyslogService getSyslogService() {
		return syslogService;
	}
	

	public void setSyslogService(ISyslogService syslogService) {
		this.syslogService = syslogService;
	}

	public String getBackgroup() {
		return backgroup;
	}

	public void setBackgroup(String backgroup) {
		this.backgroup = backgroup;
	}

	*//**
	 * 后台登录出方法
	 * 
	 * @return
	 *//*
	public String loginout() {
		System.out.println("  logiout ");
		Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().get(ActionContext.SESSION);
		String username = "";
		username =(String) session.get("adminusername");
		session.isEmpty();
		session.remove("adminusername");
	//Debug.setDebuggingOn(true);
		System.out.println("  logiout ");
		System.out.println("  logiout "+session.get("adminusername"));
		session.remove("employee");
		session.remove("usercs");
		session.remove("userlastlogin");
		session.remove("adminusername");
		
		getRequest().getSession().invalidate();
		
		System.out.println(" loginout logiout "+session.get("adminusername"));
		getRequest().getSession().removeAttribute("adminusername");
		if (backgroup != null && !backgroup.equals("") && backgroup.equals("true")) {
			System.out.println("  logiout "+session.get("adminusername"));
			//李进新加 2014-4-15
			Syslog syslog = new Syslog();
			syslog.setStlg("0245");
			syslog.setBrief("后台退出");
			syslog.setNote(username +"后台退出");
			syslog.setLogdatetime(Tools.getDayTimes());
			syslogService.insertSyslog(syslog);
			
			return SUCCESS; // 退出到后台登录页面
		} else {
			return "login"; // 返回到前台
		}

	}
*/}
