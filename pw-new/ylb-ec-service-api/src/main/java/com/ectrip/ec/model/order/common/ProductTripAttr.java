package com.ectrip.ec.model.order.common;

public class ProductTripAttr{
	
	private OrderProduct product;
	
	private String tripdate;//�������
	
	private Long tripid;//��α��
	
	private Long ivenueid;//���ݱ��
	
	private Long ivenueareaid;//����������
	
//	private Long seatid;//��λ���
	
	private Long tripticketid;
	
	private Long filmid;//��Ӱ���
	
	private String seatids="";
	
	public String getTripdate() {
		return tripdate;
	}

	public void setTripdate(String tripdate) {
		this.tripdate = tripdate;
	}

	public Long getTripid() {
		return tripid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}

	public Long getIvenueid() {
		return ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}

	public Long getIvenueareaid() {
		return ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

	public OrderProduct getProduct() {
		return product;
	}

	public void setProduct(OrderProduct product) {
		this.product = product;
	}

	public Long getTripticketid() {
		return tripticketid;
	}

	public void setTripticketid(Long tripticketid) {
		this.tripticketid = tripticketid;
	}

	public Long getFilmid() {
		return filmid;
	}

	public void setFilmid(Long filmid) {
		this.filmid = filmid;
	}

	public String getSeatids() {
		return seatids;
	}

	public void setSeatids(String seatids) {
		this.seatids = seatids;
	}

	
}
