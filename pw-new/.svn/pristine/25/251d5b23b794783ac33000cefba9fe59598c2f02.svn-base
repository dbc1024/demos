package com.ectrip.ec.model.order;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Seatordertab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Seatordertab")
public class Seatordertab implements java.io.Serializable {

	// Fields

	@EmbeddedId
	private SeatordertabId id;
	@Column(name="ITRIPPRDCONTROLID")
	private Long itripprdcontrolid;//排班ID
	@Column(name="IPROGRAMID")
	private Long iprogramid;//节目ID
	@Column(name="IVENUEID")
	private Long ivenueid;//场馆ID
	@Column(name="IVENUEAREAID")
	private Long ivenueareaid;//场馆区域ID
	
	@Column(name="ITRIPID")
	private Long itripid;//场次ID
	@Column(name="ISEATID")
	private Long iseatid;//座位ID
	@Column(name="STARTDATE")
	private String startdate;//日期
	@Column(name="DTMAKEDATE")
	private String dtmakedate;
	@Column(name="ISVALID")
	private Long isvalid;
	// Constructors
	@Transient
	private String szprogramname;
	@Transient
	private String venueidname;
	@Transient
	private String ivenueareaname;
	@Transient
	private String tripname;
	@Transient
	private String szvenueseatsname;
	@Transient
	private String starttime;
	@Transient
	private String orid;
	@Transient
	private Long iscenicid;
	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}
	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}
	public String getSzprogramname() {
		return szprogramname;
	}

	public void setSzprogramname(String szprogramname) {
		this.szprogramname = szprogramname;
	}

	public String getVenueidname() {
		return venueidname;
	}

	public void setVenueidname(String venueidname) {
		this.venueidname = venueidname;
	}

	public String getIvenueareaname() {
		return ivenueareaname;
	}

	public void setIvenueareaname(String ivenueareaname) {
		this.ivenueareaname = ivenueareaname;
	}

	public String getTripname() {
		return tripname;
	}

	public void setTripname(String tripname) {
		this.tripname = tripname;
	}

	public String getSzvenueseatsname() {
		return szvenueseatsname;
	}

	public void setSzvenueseatsname(String szvenueseatsname) {
		this.szvenueseatsname = szvenueseatsname;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public Long getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Long isvalid) {
		this.isvalid = isvalid;
	}

	/** default constructor */
	public Seatordertab() {
	}

	/** minimal constructor */
	public Seatordertab(SeatordertabId id, Long itripprdcontrolid,
			Long iprogramid, Long ivenueid, Long itripid,
			Long iseatid, String startdate) {
		this.id = id;
		this.itripprdcontrolid = itripprdcontrolid;
		this.iprogramid = iprogramid;
		this.ivenueid = ivenueid;
		this.itripid = itripid;
		this.iseatid = iseatid;
		this.startdate = startdate;
	}

	/** full constructor */
	public Seatordertab(SeatordertabId id, Long itripprdcontrolid,
			Long iprogramid, Long ivenueid, Long itripid,
			Long iseatid, String startdate, String dtmakedate) {
		this.id = id;
		this.itripprdcontrolid = itripprdcontrolid;
		this.iprogramid = iprogramid;
		this.ivenueid = ivenueid;
		this.itripid = itripid;
		this.iseatid = iseatid;
		this.startdate = startdate;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public SeatordertabId getId() {
		return this.id;
	}

	public void setId(SeatordertabId id) {
		this.id = id;
	}

	public Long getItripprdcontrolid() {
		return this.itripprdcontrolid;
	}

	public void setItripprdcontrolid(Long itripprdcontrolid) {
		this.itripprdcontrolid = itripprdcontrolid;
	}

	public Long getIprogramid() {
		return this.iprogramid;
	}

	public void setIprogramid(Long iprogramid) {
		this.iprogramid = iprogramid;
	}

	public Long getIvenueid() {
		return this.ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
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
	public Long getIvenueareaid() {
		return ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

}
