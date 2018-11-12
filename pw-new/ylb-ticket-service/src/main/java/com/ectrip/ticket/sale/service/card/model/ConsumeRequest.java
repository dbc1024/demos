package com.ectrip.ticket.sale.service.card.model;

import java.util.List;

import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.sale.service.card.core.Request;

public class ConsumeRequest extends Request{
	
	private String szticketprintno;//Ʊ��
	private String mont;//���
	private String note;//��ע��
	private String consumeType;//PXF��Ʊ���� ��000���߿�,һ��ͨϵͳ���ѡ�
	private String  thirdpartyOrid;//������֧�����ѵĶ�����
	
	private String cardno;//����
	private String swiping;//01ˢ������,�ֶ������˺�������ա�
	private List<Edmcrowdkindpricetab> prices;//������Ŀ�б��������������
	
	
	public String getThirdpartyOrid() {
		return thirdpartyOrid;
	}
	public void setThirdpartyOrid(String thirdpartyOrid) {
		this.thirdpartyOrid = thirdpartyOrid;
	}
	public String getConsumeType() {
		return consumeType;
	}
	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}
	public List<Edmcrowdkindpricetab> getPrices() {
		return prices;
	}
	public void setPrices(List<Edmcrowdkindpricetab> prices) {
		this.prices = prices;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getSzticketprintno() {
		return szticketprintno;
	}
	
	public String getSwiping() {
		return swiping;
	}
	public void setSwiping(String swiping) {
		this.swiping = swiping;
	}
	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}
	public String getMont() {
		return mont;
	}
	public void setMont(String mont) {
		this.mont = mont;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}

