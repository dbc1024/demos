package com.ectrip.ticket.model.venuemarketing;

/**
 * Seatstatustab entity. @author MyEclipse Persistence Tools
 * 座位状态表
 */

public class Seatstatustab implements java.io.Serializable {

	// Fields


	private SeatstatustabId id;
	private Long status;//状态 ：0 预订   1已预订  -1 退订 2 锁定座位
	private String dtmakedate;
	private String iseatids;

	// Constructors



	/** default constructor */
	public Seatstatustab() {
	}

	public SeatstatustabId getId() {
		return id;
	}

	public void setId(SeatstatustabId id) {
		this.id = id;
	}

	public String getIseatids() {
		return iseatids;
	}

	public void setIseatids(String iseatids) {
		this.iseatids = iseatids;
	}

	/** minimal constructor */
	public Seatstatustab( SeatstatustabId id, Long status) {
		this.id = id;
		this.status = status;
	}

	/** full constructor */
	public Seatstatustab(SeatstatustabId id, Long status, String dtmakedate) {
		this.id = id;
		this.status = status;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors



	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}