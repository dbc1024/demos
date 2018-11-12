package com.ectrip.ticket.model.venuemarketing;

public class Seatlockdetail implements java.io.Serializable {

	private SeatlockdetailId id;
	private Long iseatid;//座位ID
	private String status;//状态：00 释放(释放未出票的座位)， 01 出票(未出票)，02 锁定(已出票)


	public SeatlockdetailId getId() {
		return id;
	}
	public void setId(SeatlockdetailId id) {
		this.id = id;
	}
	public Long getIseatid() {
		return iseatid;
	}
	public void setIseatid(Long iseatid) {
		this.iseatid = iseatid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}