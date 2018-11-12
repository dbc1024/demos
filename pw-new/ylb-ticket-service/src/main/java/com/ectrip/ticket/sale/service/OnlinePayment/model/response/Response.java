package com.ectrip.ticket.sale.service.OnlinePayment.model.response;

public class Response {
	private String code; //结果代码
	private String describe; //结果说明
	private String html;

	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}


}
