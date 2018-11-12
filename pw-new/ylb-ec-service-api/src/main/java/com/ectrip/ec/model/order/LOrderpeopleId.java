package com.ectrip.ec.model.order;
public class LOrderpeopleId implements java.io.Serializable{
	private Long orderlistid;//��ϸ��ˮ
	private String orid;//������
	private Long iscenicid;//������
	private Long peopleid;
	public Long getOrderlistid() {
		return orderlistid;
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
	public Long getPeopleid() {
		return peopleid;
	}
	public void setPeopleid(Long peopleid) {
		this.peopleid = peopleid;
	}
}

