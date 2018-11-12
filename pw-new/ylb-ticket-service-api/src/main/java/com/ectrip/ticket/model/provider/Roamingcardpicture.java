package com.ectrip.ticket.model.provider;

/**
 * Roamingcardpicture entity. @author MyEclipse Persistence Tools
 */

public class Roamingcardpicture implements java.io.Serializable {

	// Fields

	private Long pictureid;
	private String cardno;
	private Long seq;
	private Long upid;
	private String dtmakedate;

	// Constructors

	/** default constructor */
	public Roamingcardpicture() {
	}

	/** minimal constructor */
	public Roamingcardpicture(Long pictureid, String cardno,
			Long seq, Long upid) {
		this.pictureid = pictureid;
		this.cardno = cardno;
		this.seq = seq;
		this.upid = upid;
	}

	/** full constructor */
	public Roamingcardpicture(Long pictureid, String cardno,
			Long seq, Long upid, String dtmakedate) {
		this.pictureid = pictureid;
		this.cardno = cardno;
		this.seq = seq;
		this.upid = upid;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public Long getPictureid() {
		return this.pictureid;
	}

	public void setPictureid(Long pictureid) {
		this.pictureid = pictureid;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getUpid() {
		return this.upid;
	}

	public void setUpid(Long upid) {
		this.upid = upid;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}