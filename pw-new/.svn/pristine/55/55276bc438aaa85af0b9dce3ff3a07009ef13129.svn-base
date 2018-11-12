package com.ectrip.ticket.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Seatsaletab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Seatsaletab implements java.io.Serializable {

	// Fields
	@Id
	private SeatsaletabId id;
	private Long itripprdcontrolid;//排班ID
	private Long ivenueid;//场馆ID
	private Long iprogramid;//节目ID
	private Long itripid;//场次ID
	private Long ivenueareaid;//场馆区域ID
	
	private Long iseatid;//座位ID
	private String startdate;//日期
	private String dtmakedate;
	private Long isvalid;

	// Constructors

	public Long getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Long isvalid) {
		this.isvalid = isvalid;
	}
	/** default constructor */
	public Seatsaletab() {
	}
	public Long getIvenueareaid() {
		return ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

	/** minimal constructor */
	public Seatsaletab(SeatsaletabId id, Long itripprdcontrolid,
			Long ivenueid, Long iprogramid, Long itripid,
			Long iseatid, String startdate) {
		this.id = id;
		this.itripprdcontrolid = itripprdcontrolid;
		this.ivenueid = ivenueid;
		this.iprogramid = iprogramid;
		this.itripid = itripid;
		this.iseatid = iseatid;
		this.startdate = startdate;
	}

	/** full constructor */
	public Seatsaletab(SeatsaletabId id, Long itripprdcontrolid,
			Long ivenueid, Long iprogramid, Long itripid,
			Long iseatid, String startdate, String dtmakedate) {
		this.id = id;
		this.itripprdcontrolid = itripprdcontrolid;
		this.ivenueid = ivenueid;
		this.iprogramid = iprogramid;
		this.itripid = itripid;
		this.iseatid = iseatid;
		this.startdate = startdate;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public SeatsaletabId getId() {
		return this.id;
	}

	public void setId(SeatsaletabId id) {
		this.id = id;
	}

	public Long getItripprdcontrolid() {
		return this.itripprdcontrolid;
	}

	public void setItripprdcontrolid(Long itripprdcontrolid) {
		this.itripprdcontrolid = itripprdcontrolid;
	}

	public Long getIvenueid() {
		return this.ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}

	public Long getIprogramid() {
		return this.iprogramid;
	}

	public void setIprogramid(Long iprogramid) {
		this.iprogramid = iprogramid;
	}

	public Long getItripid() {
		return this.itripid;
	}

	public void setItripid(Long itripid) {
		this.itripid = itripid;
	}

	public Long getIseatid() {
		return this.iseatid;
	}

	public void setIseatid(Long iseatid) {
		this.iseatid = iseatid;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}