package com.ectrip.ticket.model.permitenter;

/**
 * Ticketvenues entity. @author MyEclipse Persistence Tools
 */

public class Ticketvenues implements java.io.Serializable {

	// Fields

	private Long seq;
	private String pmcd;
	private Long itickettypeid;
	private String dtmakedate;


	private String pmva;
	private String sztickettypename;
	private boolean ticketTemp;//票务选中标识
	private Long[] ticketid;//票务数组

	// Constructors

	/** default constructor */
	public Ticketvenues() {
	}

	/** minimal constructor */
	public Ticketvenues(Long seq, String pmcd, Long itickettypeid) {
		this.seq = seq;
		this.pmcd = pmcd;
		this.itickettypeid = itickettypeid;
	}

	/** full constructor */
	public Ticketvenues(Long seq, String pmcd, Long itickettypeid,
						String dtmakedate) {
		this.seq = seq;
		this.pmcd = pmcd;
		this.itickettypeid = itickettypeid;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getPmcd() {
		return this.pmcd;
	}

	public void setPmcd(String pmcd) {
		this.pmcd = pmcd;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getPmva() {
		return pmva;
	}

	public void setPmva(String pmva) {
		this.pmva = pmva;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public boolean isTicketTemp() {
		return ticketTemp;
	}

	public void setTicketTemp(boolean ticketTemp) {
		this.ticketTemp = ticketTemp;
	}

	public Long[] getTicketid() {
		return ticketid;
	}

	public void setTicketid(Long[] ticketid) {
		this.ticketid = ticketid;
	}

}