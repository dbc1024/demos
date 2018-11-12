package com.ectrip.ticket.model.warehouse;

/**
 * Iompersonalticketdetails entity. @author MyEclipse Persistence Tools
 */

public class Iompersonalticketdetails implements java.io.Serializable {

	// Fields

	private Long idetailsid;      		 //主键
	private Long ireceiverid;			//售票员信息
	private Long itickettypeid;			//产品信息
	private String szstartticketcode;	//起始票号
	private String szendticketcode;		//截止票号
	private Long istartserial;			//起始流水号
	private Long iendserial;			//截止流水号
	private Long iamount;				//数量

	// Constructors

	/** default constructor */
	public Iompersonalticketdetails() {
	}

	/** full constructor */
	public Iompersonalticketdetails(Long idetailsid,
									Long ireceiverid, Long itickettypeid,
									String szstartticketcode, String szendticketcode,
									Long istartserial, Long iendserial, Long iamount) {
		this.idetailsid = idetailsid;
		this.ireceiverid = ireceiverid;
		this.itickettypeid = itickettypeid;
		this.szstartticketcode = szstartticketcode;
		this.szendticketcode = szendticketcode;
		this.istartserial = istartserial;
		this.iendserial = iendserial;
		this.iamount = iamount;
	}

	// Property accessors

	public Long getIdetailsid() {
		return this.idetailsid;
	}

	public void setIdetailsid(Long idetailsid) {
		this.idetailsid = idetailsid;
	}

	public Long getIreceiverid() {
		return this.ireceiverid;
	}

	public void setIreceiverid(Long ireceiverid) {
		this.ireceiverid = ireceiverid;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getSzstartticketcode() {
		return this.szstartticketcode;
	}

	public void setSzstartticketcode(String szstartticketcode) {
		this.szstartticketcode = szstartticketcode;
	}

	public String getSzendticketcode() {
		return this.szendticketcode;
	}

	public void setSzendticketcode(String szendticketcode) {
		this.szendticketcode = szendticketcode;
	}

	public Long getIstartserial() {
		return this.istartserial;
	}

	public void setIstartserial(Long istartserial) {
		this.istartserial = istartserial;
	}

	public Long getIendserial() {
		return this.iendserial;
	}

	public void setIendserial(Long iendserial) {
		this.iendserial = iendserial;
	}

	public Long getIamount() {
		return this.iamount;
	}

	public void setIamount(Long iamount) {
		this.iamount = iamount;
	}

}