package com.ectrip.ticket.model.warehouse;

/**
 * Iomstationticketdetails entity. @author MyEclipse Persistence Tools
 */

public class Iomstationticketdetails implements java.io.Serializable {

	// Fields

	private Long idetailsid;            //主键
	private Long itickettypeid;			//产品信息
	private Long iwarehouseid;			//仓库信息
	private String szstartticketcode;	//起始票号
	private String szendticketcode;		//截止票号
	private Long istartserial;			//起始流水号
	private Long iendserial;			//截止流水号
	private Long iamount;				//数量

	// Constructors

	/** default constructor */
	public Iomstationticketdetails() {
	}

	/** full constructor */
	public Iomstationticketdetails(Long idetailsid,
								   Long itickettypeid, Long iwarehouseid,
								   String szstartticketcode, String szendticketcode,
								   Long istartserial, Long iendserial, Long iamount) {
		this.idetailsid = idetailsid;
		this.itickettypeid = itickettypeid;
		this.iwarehouseid = iwarehouseid;
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

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIwarehouseid() {
		return this.iwarehouseid;
	}

	public void setIwarehouseid(Long iwarehouseid) {
		this.iwarehouseid = iwarehouseid;
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