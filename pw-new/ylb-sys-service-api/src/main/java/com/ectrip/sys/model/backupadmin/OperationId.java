package com.ectrip.sys.model.backupadmin;

import java.math.BigDecimal;

/**
 * OperationId entity. @author MyEclipse Persistence Tools
 */

public class OperationId implements java.io.Serializable {

	// Fields

	
	private BigDecimal opid;
	private BigDecimal popid;
	private BigDecimal opjb;
	private String grtp;
	private String opname;
	private String opurl;
	private String opnote;
	private BigDecimal isvalid;
	private BigDecimal ordernum;
	private BigDecimal iversion;
	private String empid;
	
	
	//ºÎ°²ÖÞÐÂ¼Ó
	private String picurlf;
	private String picurls;

	// Constructors
	
	
	
	public String getPicurlf() {
		return picurlf;
	}

	public void setPicurlf(String picurlf) {
		this.picurlf = picurlf;
	}

	public String getPicurls() {
		return picurls;
	}

	public void setPicurls(String picurls) {
		this.picurls = picurls;
	}
	
	
	

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public BigDecimal getIversion() {
		return iversion;
	}

	public void setIversion(BigDecimal iversion) {
		this.iversion = iversion;
	}

	/** default constructor */
	public OperationId() {
	}

	/** minimal constructor */
	public OperationId(BigDecimal opid, BigDecimal popid, BigDecimal opjb, String opname, String opurl,
			BigDecimal isvalid, BigDecimal ordernum,BigDecimal iversion) {
		this.opid = opid;
		this.popid = popid;
		this.opjb = opjb;
		this.opname = opname;
		this.opurl = opurl;
		this.isvalid = isvalid;
		this.ordernum = ordernum;
		this.iversion = iversion;
	}

	/** full constructor */
	public OperationId(BigDecimal opid, BigDecimal popid, BigDecimal opjb, String grtp, String opname, String opurl,
			String opnote, BigDecimal isvalid, BigDecimal ordernum,BigDecimal iversion) {
		this.opid = opid;
		this.popid = popid;
		this.opjb = opjb;
		this.grtp = grtp;
		this.opname = opname;
		this.opurl = opurl;
		this.opnote = opnote;
		this.isvalid = isvalid;
		this.ordernum = ordernum;
		this.iversion =  iversion;
	}

	// Property accessors

	public OperationId(BigDecimal opid, BigDecimal popid, BigDecimal opjb,
			String grtp, String opname, String opurl, String opnote,
			BigDecimal isvalid, BigDecimal ordernum, BigDecimal iversion,
			String empid, String picurlf, String picurls) {
		super();
		this.opid = opid;
		this.popid = popid;
		this.opjb = opjb;
		this.grtp = grtp;
		this.opname = opname;
		this.opurl = opurl;
		this.opnote = opnote;
		this.isvalid = isvalid;
		this.ordernum = ordernum;
		this.iversion = iversion;
		this.empid = empid;
		this.picurlf = picurlf;
		this.picurls = picurls;
	}

	public BigDecimal getOpid() {
		return this.opid;
	}

	public void setOpid(BigDecimal opid) {
		this.opid = opid;
	}

	public BigDecimal getPopid() {
		return this.popid;
	}

	public void setPopid(BigDecimal popid) {
		this.popid = popid;
	}

	public BigDecimal getOpjb() {
		return this.opjb;
	}

	public void setOpjb(BigDecimal opjb) {
		this.opjb = opjb;
	}

	public String getGrtp() {
		return this.grtp;
	}

	public void setGrtp(String grtp) {
		this.grtp = grtp;
	}

	public String getOpname() {
		return this.opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	}

	public String getOpurl() {
		return this.opurl;
	}

	public void setOpurl(String opurl) {
		this.opurl = opurl;
	}

	public String getOpnote() {
		return this.opnote;
	}

	public void setOpnote(String opnote) {
		this.opnote = opnote;
	}

	public BigDecimal getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(BigDecimal isvalid) {
		this.isvalid = isvalid;
	}

	public BigDecimal getOrdernum() {
		return this.ordernum;
	}

	public void setOrdernum(BigDecimal ordernum) {
		this.ordernum = ordernum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OperationId))
			return false;
		OperationId castOther = (OperationId) other;

		return ((this.getOpid() == castOther.getOpid()) || (this.getOpid() != null && castOther.getOpid() != null && this
				.getOpid().equals(castOther.getOpid())))
				&& ((this.getPopid() == castOther.getPopid()) || (this.getPopid() != null
						&& castOther.getPopid() != null && this.getPopid().equals(castOther.getPopid())))
				&& ((this.getOpjb() == castOther.getOpjb()) || (this.getOpjb() != null && castOther.getOpjb() != null && this
						.getOpjb().equals(castOther.getOpjb())))
				&& ((this.getGrtp() == castOther.getGrtp()) || (this.getGrtp() != null && castOther.getGrtp() != null && this
						.getGrtp().equals(castOther.getGrtp())))
				&& ((this.getOpname() == castOther.getOpname()) || (this.getOpname() != null
						&& castOther.getOpname() != null && this.getOpname().equals(castOther.getOpname())))
				&& ((this.getOpurl() == castOther.getOpurl()) || (this.getOpurl() != null
						&& castOther.getOpurl() != null && this.getOpurl().equals(castOther.getOpurl())))
				&& ((this.getOpnote() == castOther.getOpnote()) || (this.getOpnote() != null
						&& castOther.getOpnote() != null && this.getOpnote().equals(castOther.getOpnote())))
				&& ((this.getIsvalid() == castOther.getIsvalid()) || (this.getIsvalid() != null
						&& castOther.getIsvalid() != null && this.getIsvalid().equals(castOther.getIsvalid())))
				&& ((this.getOrdernum() == castOther.getOrdernum()) || (this.getOrdernum() != null
						&& castOther.getOrdernum() != null && this.getOrdernum().equals(castOther.getOrdernum())))
						//ºÎ°²ÖÞÐÂ¼Ó 2017-05-03
				&& ((this.getPicurlf() == castOther.getPicurlf()) || (this.getPicurlf() != null
						&& castOther.getPicurlf() != null && this.getPicurlf().equals(castOther.getPicurlf())))
				&& ((this.getPicurls() == castOther.getPicurls()) || (this.getPicurls() != null
						&& castOther.getPicurls() != null && this.getPicurls().equals(castOther.getPicurls())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOpid() == null ? 0 : this.getOpid().hashCode());
		result = 37 * result + (getPopid() == null ? 0 : this.getPopid().hashCode());
		result = 37 * result + (getOpjb() == null ? 0 : this.getOpjb().hashCode());
		result = 37 * result + (getGrtp() == null ? 0 : this.getGrtp().hashCode());
		result = 37 * result + (getOpname() == null ? 0 : this.getOpname().hashCode());
		result = 37 * result + (getOpurl() == null ? 0 : this.getOpurl().hashCode());
		result = 37 * result + (getOpnote() == null ? 0 : this.getOpnote().hashCode());
		result = 37 * result + (getIsvalid() == null ? 0 : this.getIsvalid().hashCode());
		result = 37 * result + (getOrdernum() == null ? 0 : this.getOrdernum().hashCode());
		//ºÎ°²ÖÞÐÂ¼Ó
		result = 37 * result + (getPicurlf() == null ? 0 : this.getPicurlf().hashCode());
		result = 37 * result + (getPicurls() == null ? 0 : this.getPicurls().hashCode());
		return result;
	}

}