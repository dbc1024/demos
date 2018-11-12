package com.ectrip.ticket.sale.service.card.core;
public class Request {
	private String targetClass; //Ŀ����
	private String method; //����
	private String iticketwinid; //����Id
	private String iemployeeid; //����ԱId
	private String iaccessequipid;//բ��id
	private String printReceipt;// "01"��ʾ��ӡСƱ
	
	public String getPrintReceipt() {
		return printReceipt;
	}
	public void setPrintReceipt(String printReceipt) {
		this.printReceipt = printReceipt;
	}
	public Request() {
		super();
	}
	public Request(String targetClass, String method, String iticketwinid,
			String iemployeeid) {
		super();
		this.targetClass = targetClass;
		this.method = method;
		this.iticketwinid = iticketwinid;
		this.iemployeeid = iemployeeid;
	}
	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getIticketwinid() {
		return iticketwinid;
	}
	public void setIticketwinid(String iticketwinid) {
		this.iticketwinid = iticketwinid;
	}
	
	public String getIaccessequipid() {
		return iaccessequipid;
	}
	public void setIaccessequipid(String iaccessequipid) {
		this.iaccessequipid = iaccessequipid;
	}
	public String getIemployeeid() {
		return iemployeeid;
	}
	public void setIemployeeid(String iemployeeid) {
		this.iemployeeid = iemployeeid;
	}
	
}

