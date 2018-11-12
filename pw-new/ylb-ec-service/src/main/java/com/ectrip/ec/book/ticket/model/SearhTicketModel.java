package com.ectrip.ec.book.ticket.model;

public class SearhTicketModel {

	private int itickettypeid;// ��Ʒ���
	private String ticketname;// ��Ʒ����
	private String providername;// ����������
	private int iscenicid;// �����̱��
	private double mactualsaleprice;// ʵ���ۼ�
	private double listingprice;// ���Ƽ�

	public int getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(int itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getTicketname() {
		return ticketname;
	}

	public void setTicketname(String ticketname) {
		this.ticketname = ticketname;
	}

	public String getProvidername() {
		return providername;
	}

	public void setProvidername(String providername) {
		this.providername = providername;
	}

	public int getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(int iscenicid) {
		this.iscenicid = iscenicid;
	}

	public double getMactualsaleprice() {
		return mactualsaleprice;
	}

	public void setMactualsaleprice(double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}

	public double getListingprice() {
		return listingprice;
	}

	public void setListingprice(double listingprice) {
		this.listingprice = listingprice;
	}

}
