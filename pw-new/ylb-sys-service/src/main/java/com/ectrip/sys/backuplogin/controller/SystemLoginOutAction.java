package com.ectrip.sys.backuplogin.controller;

import java.util.Map;

import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.service.ISyslogService;

public class SystemLoginOutAction{/*
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;
	private String backgroup;
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
	public String cancelUser() {
		try{
	    //System.out.println("  1 ");
		//System.out.println("  开始进入 ACTION cancelUser");
		//System.out.println("  2 ");
			
		Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().get(ActionContext.SESSION);
		Esfemployeetab employee=(Esfemployeetab) session.get("employee");
		//System.out.println("  3 ");
		String username = "";
		username =employee.getEmpid();
		session.isEmpty();
		//System.out.println("  4 ");
		getRequest().getSession().removeAttribute("adminusername");
		//System.out.println("  5 ");
		session.remove("employee");
		//System.out.println("  6 ");
		session.remove("usercs");
		//System.out.println("  7 ");
		session.remove("userlastlogin");
		//System.out.println("  8 ");
		session.remove("adminusername");
		//System.out.println("  9 ");
		//System.out.println("  logiout ");
		
		//System.out.println("  logiout "+session.get("adminusername"));

		getRequest().getSession().invalidate();
		//System.out.println("  10 ");
		//System.out.println("  logiout ");
		//System.out.println("  logiout "+session.get("adminusername"));
		//System.out.println("  11 ");
		if (backgroup != null && !backgroup.equals("") && backgroup.equals("true")) {
			employee.setLogintime(employee.getLasttime());
			employee.setIpaddress(employee.getLastipaddress());
			this.genericService.update(employee);
			//李进新加 2014-4-15
			Syslog syslog = new Syslog();
			syslog.setStlg("0245");
			syslog.setBrief("后台退出");
			syslog.setNote(username +"后台退出");
			syslog.setLogdatetime(Tools.getDayTimes());
			syslogService.insertSyslog(syslog);
			//System.out.println("  退出 ACTION ");
			//System.out.println("  12 ");
			return SUCCESS; // 退出到后台登录页面
		} else {
			//System.out.println("  退出 ");
			//System.out.println("  13 ");
			return "login"; // 返回到前台
		}
		} catch(Exception e)
		{
			//System.out.println("  14 ");
			//System.out.println("   e "+  e.toString());
			return SUCCESS; // 退出到后台登录页面
		}
	}
*/}
