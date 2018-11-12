package com.ectrip.ticket.service.model;



/**
 * 用户
 * @author LiuJianwen
 *
 */
public class User{

	private static final long serialVersionUID = 1L;

	private String iemployeeid;//编号

	private String empid;//登录名

	private String pwd;//登陆密码

	private String szemployeename;//姓名

	////////////////////////////////
	private String company;// 单位名称

	//////////////////////////////////////
	private String loginTime;//登陆时间

	public User() {
	}

	public User(String iemployeeid, String empid,String pwd, String szemployeename,String company,String loginTime) {
		super();
		this.iemployeeid = iemployeeid;
		this.empid = empid;
		this.pwd = pwd;
		this.szemployeename = szemployeename;
		this.company = company;
		this.loginTime = loginTime;
	}

	public String getIemployeeid() {
		return iemployeeid;
	}

	public void setIemployeeid(String iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getSzemployeename() {
		return szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
