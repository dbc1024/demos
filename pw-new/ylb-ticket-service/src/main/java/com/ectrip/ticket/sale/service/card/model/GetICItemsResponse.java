package com.ectrip.ticket.sale.service.card.model;

import java.util.List;

import com.ectrip.ticket.sale.service.card.core.Response;

public class GetICItemsResponse extends Response{
	private List<?> icitems;
	private Object custom;
	private String html;//��ѯ�Ľ�����Ϣ��,html��ʾ
	private String mont;//��������ɵĽ��
	private String type;//06���˻��� 05�˿�
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<?> getIcitems() {
		return icitems;
	}
	public String getMont() {
		return mont;
	}



	public void setMont(String mont) {
		this.mont = mont;
	}



	public Object getCustom() {
		return custom;
	}



	public void setCustom(Object custom) {
		this.custom = custom;
	}



	public void setIcitems(List<?> icitems) {
		this.icitems = icitems;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	
}

