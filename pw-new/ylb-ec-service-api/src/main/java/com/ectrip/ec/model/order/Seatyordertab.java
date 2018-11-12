package com.ectrip.ec.model.order;


public class Seatyordertab implements java.io.Serializable{
	private SeatyordertabId id;
	private Long itripprdcontrolid;//排班ID
	private Long iprogramid;//节目ID
	private Long ivenueid;//场馆ID
	private Long ivenueareaid;//场馆区域ID
	

	private Long itripid;//场次ID
	private Long iseatid;//座位ID
	private String startdate;//日期
	private String dtmakedate;
	private Long isvalid;
	public Seatyordertab() {
	}
	public Seatyordertab(SeatyordertabId id, Long itripprdcontrolid,
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
	public Seatyordertab(SeatyordertabId id, Long itripprdcontrolid,
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
	public SeatyordertabId getId() {
		return id;
	}
	public void setId(SeatyordertabId id) {
		this.id = id;
	}
	public Long getItripprdcontrolid() {
		return itripprdcontrolid;
	}
	public void setItripprdcontrolid(Long itripprdcontrolid) {
		this.itripprdcontrolid = itripprdcontrolid;
	}
	public Long getIprogramid() {
		return iprogramid;
	}
	public void setIprogramid(Long iprogramid) {
		this.iprogramid = iprogramid;
	}
	public Long getIvenueid() {
		return ivenueid;
	}
	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}
	public Long getIvenueareaid() {
		return ivenueareaid;
	}
	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}
	public Long getItripid() {
		return itripid;
	}
	public void setItripid(Long itripid) {
		this.itripid = itripid;
	}
	public Long getIseatid() {
		return iseatid;
	}
	public void setIseatid(Long iseatid) {
		this.iseatid = iseatid;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getDtmakedate() {
		return dtmakedate;
	}
	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}
	public Long getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Long isvalid) {
		this.isvalid = isvalid;
	}
}
