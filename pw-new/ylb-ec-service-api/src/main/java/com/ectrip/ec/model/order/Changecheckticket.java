package com.ectrip.ec.model.order;

/**
 * Changecheckticket entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Changecheckticket implements java.io.Serializable {

	// Fields

	private Long changeid;
	private Long iemployeeid;
	private Long igardengateid;
	private Long iaccessequipid;
	private String szticketprintno;
	private String ydate;
	private Long ytripid;
	private String ndate;
	private Long ntripid;
	private String dtmakedate;

	// Constructors

	/** default constructor */
	public Changecheckticket() {
	}

	/** full constructor */
	public Changecheckticket(Long changeid, Long iemployeeid, Long igardengateid,
			Long iaccessequipid, String szticketprintno, String ydate, Long ytripid,
			String ndate, Long ntripid, String dtmakedate) {
		this.changeid = changeid;
		this.iemployeeid = iemployeeid;
		this.igardengateid = igardengateid;
		this.iaccessequipid = iaccessequipid;
		this.szticketprintno = szticketprintno;
		this.ydate = ydate;
		this.ytripid = ytripid;
		this.ndate = ndate;
		this.ntripid = ntripid;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public Long getChangeid() {
		return this.changeid;
	}

	public void setChangeid(Long changeid) {
		this.changeid = changeid;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
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

	public String getSzticketprintno() {
		return this.szticketprintno;
	}

	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}

	public String getYdate() {
		return this.ydate;
	}

	public void setYdate(String ydate) {
		this.ydate = ydate;
	}

	public Long getYtripid() {
		return this.ytripid;
	}

	public void setYtripid(Long ytripid) {
		this.ytripid = ytripid;
	}

	public String getNdate() {
		return this.ndate;
	}

	public void setNdate(String ndate) {
		this.ndate = ndate;
	}

	public Long getNtripid() {
		return this.ntripid;
	}

	public void setNtripid(Long ntripid) {
		this.ntripid = ntripid;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}