package com.ectrip.ticket.model.permitenter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Priceprintmanager entity. @author MyEclipse Persistence Tools
 */
@Table
@Entity
public class Priceprintmanager implements java.io.Serializable {

	// Fields
	@Id
	private PriceprintmanagerId id;
	private Long ordernum;//排序
	private String noteprinttype;//是否打印标题 0否 1是
	private String note;//内容  
	private String printtype;//0系统控制 1自定义
	private String dtmakedate;//创建时间

	//非数据库字段
	@Transient
	private Long icrowdkindpriceid;//价格ID
	@Transient
	private String printno;//打印编码
	@Transient
	private String szprintno;
	@Transient
	private String[] printnos;
	public String getSzprintno() {
		return szprintno;
	}

	public void setSzprintno(String szprintno) {
		this.szprintno = szprintno;
	}

	// Constructors
	
	/** default constructor */
	public Priceprintmanager() {
	}

	/** minimal constructor */
	public Priceprintmanager(PriceprintmanagerId id) {
		this.id = id;
	}

	/** full constructor */
	public Priceprintmanager(PriceprintmanagerId id, Long ordernum,
			String noteprinttype, String note, String printtype,
			String dtmakedate) {
		this.id = id;
		this.ordernum = ordernum;
		this.noteprinttype = noteprinttype;
		this.note = note;
		this.printtype = printtype;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public PriceprintmanagerId getId() {
		return this.id;
	}

	public void setId(PriceprintmanagerId id) {
		this.id = id;
	}

	public Long getOrdernum() {
		return this.ordernum;
	}

	public void setOrdernum(Long ordernum) {
		this.ordernum = ordernum;
	}

	public String getNoteprinttype() {
		return this.noteprinttype;
	}

	public void setNoteprinttype(String noteprinttype) {
		this.noteprinttype = noteprinttype;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPrinttype() {
		return this.printtype;
	}

	public void setPrinttype(String printtype) {
		this.printtype = printtype;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public String getPrintno() {
		return printno;
	}

	public void setPrintno(String printno) {
		this.printno = printno;
	}

	public String[] getPrintnos() {
		return printnos;
	}

	public void setPrintnos(String[] printnos) {
		this.printnos = printnos;
	}



}
