package com.ectrip.ec.model.cytterminal;
public class QueryOrderInfo {

	private String method;//请求方式
	private String code;//验证码
	private String phone;//手机号码
	private String credentials;//证件号码
	private String orderId;//订单号
	private String startDate;//开始日期
	private String endDate;//结束日期
	private String count;//最大订单数量

	private String devId;//终端mac
	private String otoCode;//企业编码
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCredentials() {
		return credentials;
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
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


}

