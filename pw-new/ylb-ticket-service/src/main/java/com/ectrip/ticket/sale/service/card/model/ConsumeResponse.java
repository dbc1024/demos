package com.ectrip.ticket.sale.service.card.model;

import com.ectrip.ticket.sale.service.card.core.Response;

public class ConsumeResponse extends Response{
	private Object custom;
	private String voice;//��Ʊ������
	
	////////////////������֧�������ֶ�////////////////////////
	private String consumeOrid;//����ƾ֤
	private String consumeamount;//���ѽ��
	private String consumedate;//����ʱ��
	private String iemployeeid;//������Ա
	private String usid;//֧���û�
	private String iaccessequipid;//�����豸
	
	public String getIaccessequipid() {
		return iaccessequipid;
	}

	public void setIaccessequipid(String iaccessequipid) {
		this.iaccessequipid = iaccessequipid;
	}

	public String getConsumeamount() {
		return consumeamount;
	}

	public void setConsumeamount(String consumeamount) {
		this.consumeamount = consumeamount;
	}

	public String getConsumedate() {
		return consumedate;
	}

	public void setConsumedate(String consumedate) {
		this.consumedate = consumedate;
	}

	public String getIemployeeid() {
		return iemployeeid;
	}

	public void setIemployeeid(String iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getConsumeOrid() {
		return consumeOrid;
	}

	public void setConsumeOrid(String consumeOrid) {
		this.consumeOrid = consumeOrid;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public Object getCustom() {
		return custom;
	}

	public void setCustom(Object custom) {
		this.custom = custom;
	}
	
}

