package com.ectrip.ec.model.order;

/**
 * TOrderlistId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HistOrderlistId implements java.io.Serializable {

	// Fields

	private Long orderlistid;//��ϸ��ˮ
	private String orid;//������
	private Long iscenicid;//������

	// Constructors

	/** default constructor */
	public HistOrderlistId() {
	}

	/** full constructor */
	public HistOrderlistId(Long orderlistid,String orid,Long iscenicid) {
		this.orderlistid = orderlistid;
		this.orid = orid;
		this.iscenicid=iscenicid;
	}

	// Property accessors

	public Long getOrderlistid() {
		return this.orderlistid;
	}

	public void setOrderlistid(Long orderlistid) {
		this.orderlistid = orderlistid;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}





}