package com.ectrip.ec.model.order.common;

public class OrderProduct {
	
	private Long iscenicid;//������
	private Long iticketid;//��Ʒ���
	private Long crowdkindid;//��Ⱥ���
	private String startdate;//��ʼ����
	private String enddate;//��������
	private Long timeid;//��ʱԤԼ��ʼʱ��
	private String starttime;//��ʱԤԼ��ʼʱ��
	private String endtime;//��ʱԤԼ����ʱ��
	private Long num;//����
	private String note;
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OrderProduct() {
	}
	
	public OrderProduct(Long iscenicid, Long iticketid, Long crowdkindid, String startdate, String enddate,String starttime, String endtime,Long timeid, Long num,String note) {
		super();
		this.iscenicid = iscenicid;
		this.iticketid = iticketid;
		this.crowdkindid = crowdkindid;
		this.startdate = startdate;
		this.enddate = enddate;
		this.starttime = starttime;
		this.endtime = endtime;
		this.timeid=timeid;
		this.num = num;
		this.note = note;
	}
	public Long getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}
	public Long getIticketid() {
		return iticketid;
	}
	public void setIticketid(Long iticketid) {
		this.iticketid = iticketid;
	}
	public Long getCrowdkindid() {
		return crowdkindid;
	}
	public void setCrowdkindid(Long crowdkindid) {
		this.crowdkindid = crowdkindid;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Long getTimeid() {
		return timeid;
	}

	public void setTimeid(Long timeid) {
		this.timeid = timeid;
	}
	
}
