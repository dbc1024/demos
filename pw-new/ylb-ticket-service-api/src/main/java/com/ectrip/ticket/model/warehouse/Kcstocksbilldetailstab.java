package com.ectrip.ticket.model.warehouse;

/**
 * Kcstocksbilldetailstab entity. @author MyEclipse Persistence Tools
 */

public class Kcstocksbilldetailstab implements java.io.Serializable {

	// Fields

	private String szstocksbilldetailsid;   //主键
	private String szstocksbillid;			//单据信息
	private Long itickettypeid;				//票ID
	private Long iamount;					//数量
	private Long int1;						//
	private Long int2;
	private String note1;
	private String note2;

	// Constructors

	/** default constructor */
	public Kcstocksbilldetailstab() {
	}

	/** minimal constructor */
	public Kcstocksbilldetailstab(String szstocksbilldetailsid,
								  Long itickettypeid, Long iamount) {
		this.szstocksbilldetailsid = szstocksbilldetailsid;
		this.itickettypeid = itickettypeid;
		this.iamount = iamount;
	}

	/** full constructor */
	public Kcstocksbilldetailstab(String szstocksbilldetailsid,
								  String szstocksbillid, Long itickettypeid,
								  Long iamount, Long int1, Long int2, String note1,
								  String note2) {
		this.szstocksbilldetailsid = szstocksbilldetailsid;
		this.szstocksbillid = szstocksbillid;
		this.itickettypeid = itickettypeid;
		this.iamount = iamount;
		this.int1 = int1;
		this.int2 = int2;
		this.note1 = note1;
		this.note2 = note2;
	}

	// Property accessors

	public String getSzstocksbilldetailsid() {
		return this.szstocksbilldetailsid;
	}

	public void setSzstocksbilldetailsid(String szstocksbilldetailsid) {
		this.szstocksbilldetailsid = szstocksbilldetailsid;
	}

	public String getSzstocksbillid() {
		return szstocksbillid;
	}

	public void setSzstocksbillid(String szstocksbillid) {
		this.szstocksbillid = szstocksbillid;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIamount() {
		return this.iamount;
	}

	public void setIamount(Long iamount) {
		this.iamount = iamount;
	}

	public Long getInt1() {
		return this.int1;
	}

	public void setInt1(Long int1) {
		this.int1 = int1;
	}

	public Long getInt2() {
		return this.int2;
	}

	public void setInt2(Long int2) {
		this.int2 = int2;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

}