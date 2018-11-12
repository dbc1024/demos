package com.ectrip.ec.model.cytterminal;
public class ConsumeOrderInfo {

	private String orid;//订单号
	private String password;//订单取票密码
	private String verifyPassword;//是否验证密码
	private String consumeCount;//消费数量
	private String sign;//订单标识
	private String devId;//终端mac
	private String otoCode;//企业编码
	public String getOrid() {
		return orid;
	}
	public void setOrid(String orid) {
		this.orid = orid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyPassword() {
		return verifyPassword;
	}
	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}
	public String getConsumeCount() {
		return consumeCount;
	}
	public void setConsumeCount(String consumeCount) {
		this.consumeCount = consumeCount;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getOtoCode() {
		return otoCode;
	}
	public void setOtoCode(String otoCode) {
		this.otoCode = otoCode;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}


}
