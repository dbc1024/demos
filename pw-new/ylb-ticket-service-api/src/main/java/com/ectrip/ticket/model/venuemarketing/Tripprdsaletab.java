package com.ectrip.ticket.model.venuemarketing;
/**
 * Trpprdsaletab entity. @author MyEclipse Persistence Tools
 * 排班产品区域关联表
 */

public class Tripprdsaletab implements java.io.Serializable {

	// Fields

	private Long seq;
	private Long itripprdcontrolid;
	private Long iproductid;
	private Long ivenueareaid;
	private String dtmakedate;

	private String ivenueareaname;
	private String sztickettypename;
	// Constructors

	/** default constructor */
	public Tripprdsaletab() {
	}

	/** minimal constructor */
	public Tripprdsaletab(Long seq, Long itripprdcontrolid,
						  Long iproductid, Long ivenueareaid) {
		this.seq = seq;
		this.itripprdcontrolid = itripprdcontrolid;
		this.iproductid = iproductid;
		this.ivenueareaid = ivenueareaid;
	}

	/** full constructor */
	public Tripprdsaletab(Long seq, Long itripprdcontrolid,
						  Long iproductid, Long ivenueareaid, String dtmakedate) {
		this.seq = seq;
		this.itripprdcontrolid = itripprdcontrolid;
		this.iproductid = iproductid;
		this.ivenueareaid = ivenueareaid;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getItripprdcontrolid() {
		return this.itripprdcontrolid;
	}

	public void setItripprdcontrolid(Long itripprdcontrolid) {
		this.itripprdcontrolid = itripprdcontrolid;
	}

	public Long getIproductid() {
		return this.iproductid;
	}

	public void setIproductid(Long iproductid) {
		this.iproductid = iproductid;
	}

	public Long getIvenueareaid() {
		return this.ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getIvenueareaname() {
		return ivenueareaname;
	}

	public void setIvenueareaname(String ivenueareaname) {
		this.ivenueareaname = ivenueareaname;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

}