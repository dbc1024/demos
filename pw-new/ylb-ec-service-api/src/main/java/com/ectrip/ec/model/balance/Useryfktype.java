package com.ectrip.ec.model.balance;

/**
 * 一卡通赠送金额明细表
 * Useryfk entity. @author MyEclipse Persistence Tools
 */

public class Useryfktype implements java.io.Serializable {

	// Fields

	private Integer seq;//主键
	private String usid;//用户名
	private Integer yfklb;//预付款类别（1增加，-1减少）
	private String orderid;//订单号
	private Double point;//金额
	private String bdate;//预付款产生日期
	private String yfksc;//预付款来源
	private Integer cztp;//预付款操作类别(0自动，1人工)
	private String empid;//操作人
	private String note;//备注
	private String accnt;
	private String hotelid;
	private String arr;
	private String szmemo;//其它说明


	//非数据库字段
	private String stryfklb;//预付款类别
	private String corpname;//用户姓名
	private String stryfksc;//预付款来源
	private Integer version;//版本控制


	// Constructors



	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/** default constructor */
	public Useryfktype() {
	}

	/** minimal constructor */
	public Useryfktype(Integer seq, String usid, Integer yfklb,
					   String orderid, Double point, String bdate, String yfksc,
					   Integer cztp) {
		this.seq = seq;
		this.usid = usid;
		this.yfklb = yfklb;
		this.orderid = orderid;
		this.point = point;
		this.bdate = bdate;
		this.yfksc = yfksc;
		this.cztp = cztp;
	}

	/** full constructor */
	public Useryfktype(Integer seq, String usid, Integer yfklb,
					   String orderid, Double point, String bdate, String yfksc,
					   Integer cztp, String empid, String note, String accnt,
					   String hotelid, String arr, String szmemo) {
		this.seq = seq;
		this.usid = usid;
		this.yfklb = yfklb;
		this.orderid = orderid;
		this.point = point;
		this.bdate = bdate;
		this.yfksc = yfksc;
		this.cztp = cztp;
		this.empid = empid;
		this.note = note;
		this.accnt = accnt;
		this.hotelid = hotelid;
		this.arr = arr;
		this.szmemo = szmemo;
	}

	// Property accessors

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Integer getYfklb() {
		return this.yfklb;
	}

	public void setYfklb(Integer yfklb) {
		this.yfklb = yfklb;
	}

	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Double getPoint() {
		return this.point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	public String getBdate() {
		return this.bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getYfksc() {
		return this.yfksc;
	}

	public void setYfksc(String yfksc) {
		this.yfksc = yfksc;
	}

	public Integer getCztp() {
		return this.cztp;
	}

	public void setCztp(Integer cztp) {
		this.cztp = cztp;
	}

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public String getHotelid() {
		return this.hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}

	public String getArr() {
		return this.arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public String getStryfklb() {
		return stryfklb;
	}

	public void setStryfklb(String stryfklb) {
		this.stryfklb = stryfklb;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getStryfksc() {
		return stryfksc;
	}

	public void setStryfksc(String stryfksc) {
		this.stryfksc = stryfksc;
	}

}