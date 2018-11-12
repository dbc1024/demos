package com.ectrip.ec.model.order;

import javax.persistence.Embeddable;

@Embeddable
public class LOrderId implements java.io.Serializable{
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
	private String orid;//主订单流水号
	private Long iscenicid;//服务商id
	
	public LOrderId() {
	}

	/** full constructor */
	public LOrderId(String orid, Long iscenicid) {
		this.orid = orid;
		this.iscenicid = iscenicid;
	}
}
