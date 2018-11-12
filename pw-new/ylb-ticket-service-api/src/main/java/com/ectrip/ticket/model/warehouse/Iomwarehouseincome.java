package com.ectrip.ticket.model.warehouse;

/**
 * Iomwarehouseincome entity. @author MyEclipse Persistence Tools
 */

public class Iomwarehouseincome implements java.io.Serializable {

	// Fields

	private Long idetailsid;     //主键
	private Long itickettypeid;	 //票ID
	private Long istartserial;	//起始票流水号
	private Long iendserial;	//截止票流水号
	private Long iamount;		//数量

	// Constructors

	/** default constructor */
	public Iomwarehouseincome() {
	}

	/** full constructor */
	public Iomwarehouseincome(Long idetailsid, Long itickettypeid,
							  Long istartserial, Long iendserial, Long iamount) {
		this.idetailsid = idetailsid;
		this.itickettypeid = itickettypeid;
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