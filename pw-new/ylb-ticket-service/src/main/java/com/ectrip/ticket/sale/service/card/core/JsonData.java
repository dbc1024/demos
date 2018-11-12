package com.ectrip.ticket.sale.service.card.core;

public class JsonData {

	private String data;//����
	private String sign;//ǩ��
	private String signType;//ǩ������

	public JsonData() {
		super();
	}

	public JsonData(String data, String sign, String signType) {
		super();
		this.data = data;
		this.sign = sign;
		this.signType = signType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
			
}

