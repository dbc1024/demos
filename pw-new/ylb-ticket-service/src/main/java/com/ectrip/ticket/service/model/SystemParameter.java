package com.ectrip.ticket.service.model;


/**
 * 初始化系统参数
 * @LiuJianwen Administrator
 *
 */
public class SystemParameter{
	private static final long serialVersionUID = 1L;
	private String gqtd;//
	private String clientversion;//版本
	private String cptd = "0";//网络订单是否可以修改
	private String tptd = "0";//竹筏停牌是否可以出售
	private String passwordcontrol = "1";//0 不需要输入密码
	private String daytimes;//系统时间


	public SystemParameter() {
	}

	public SystemParameter(String gqtd,
						   String clientversion, String cptd, String tptd,
						   String passwordcontrol, String daytimes) {
		this.gqtd = gqtd;
		this.clientversion = clientversion;
		this.cptd = cptd;
		this.tptd = tptd;
		this.passwordcontrol = passwordcontrol;
		this.daytimes = daytimes;
	}

	public String getGqtd() {
		return gqtd;
	}
	public void setGqtd(String gqtd) {
		this.gqtd = gqtd;
	}
	public String getClientversion() {
		return clientversion;
	}
	public void setClientversion(String clientversion) {
		this.clientversion = clientversion;
	}
	public String getCptd() {
		return cptd;
	}
	public void setCptd(String cptd) {
		this.cptd = cptd;
	}
	public String getTptd() {
		return tptd;
	}
	public void setTptd(String tptd) {
		this.tptd = tptd;
	}
	public String getPasswordcontrol() {
		return passwordcontrol;
	}
	public void setPasswordcontrol(String passwordcontrol) {
		this.passwordcontrol = passwordcontrol;
	}
	public String getDaytimes() {
		return daytimes;
	}
	public void setDaytimes(String daytimes) {
		this.daytimes = daytimes;
	}




}
