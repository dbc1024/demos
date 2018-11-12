package com.ectrip.ticket.model.salesmanager;

/**
 * Opcempployeecardcheck entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Opcempployeecardcheck implements java.io.Serializable {

	// Fields

	private Long checkid;
	private Long iemployeecardid;
	private Long igardengateid;
	private Long iaccessequipid;
	private String icardno;
	private String usid;
	private Long iemployeeid;
	private String szdtime;
	private Long iyear;
	private Long imonth;
	private Long iday;
	private String szticketprintno;
	private String dtmakedate;
	

	// Constructors

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	/** default constructor */
	public Opcempployeecardcheck() {
	}

	/** minimal constructor */
	public Opcempployeecardcheck(Long checkid, Long iemployeecardid,
			Long igardengateid, Long iaccessequipid, Long iyear, Long imonth,
			Long iday) {
		this.checkid = checkid;
		this.iemployeecardid = iemployeecardid;
		this.igardengateid = igardengateid;
		this.iaccessequipid = iaccessequipid;
		this.iyear = iyear;
		this.imonth = imonth;
		this.iday = iday;
	}

	/** full constructor */
	public Opcempployeecardcheck(Long checkid, Long iemployeecardid,
			Long igardengateid, Long iaccessequipid, String icardno, String usid,
			Long iemployeeid, String szdtime, Long iyear, Long imonth, Long iday,
			String szticketprintno) {
		this.checkid = checkid;
		this.iemployeecardid = iemployeecardid;
		this.igardengateid = igardengateid;
		this.iaccessequipid = iaccessequipid;
		this.icardno = icardno;
		this.usid = usid;
		this.iemployeeid = iemployeeid;
		this.szdtime = szdtime;
		this.iyear = iyear;
		this.imonth = imonth;
		this.iday = iday;
		this.szticketprintno = szticketprintno;
	}

	// Property accessors

	public Long getCheckid() {
		return this.checkid;
	}

	public void setCheckid(Long checkid) {
		this.checkid = checkid;
	}

	public Long getIemployeecardid() {
		return this.iemployeecardid;
	}

	public void setIemployeecardid(Long iemployeecardid) {
		this.iemployeecardid = iemployeecardid;
	}

	public Long getIgardengateid() {
		return this.igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getIaccessequipid() {
		return this.iaccessequipid;
	}

	public void setIaccessequipid(Long iaccessequipid) {
		this.iaccessequipid = iaccessequipid;
	}

	public String getIcardno() {
		return this.icardno;
	}

	public void setIcardno(String icardno) {
		this.icardno = icardno;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getSzdtime() {
		return this.szdtime;
	}

	public void setSzdtime(String szdtime) {
		this.szdtime = szdtime;
	}

	public Long getIyear() {
		return this.iyear;
	}

	public void setIyear(Long iyear) {
		this.iyear = iyear;
	}

	public Long getImonth() {
		return this.imonth;
	}

	public void setImonth(Long imonth) {
		this.imonth = imonth;
	}

	public Long getIday() {
		return this.iday;
	}

	public void setIday(Long iday) {
		this.iday = iday;
	}

	public String getSzticketprintno() {
		return this.szticketprintno;
	}

	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}

}