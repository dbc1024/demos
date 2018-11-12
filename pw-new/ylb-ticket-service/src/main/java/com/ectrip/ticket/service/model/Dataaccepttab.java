package com.ectrip.ticket.service.model;

/**
 * Dataaccepttab entity. @author MyEclipse Persistence Tools
 */

public class Dataaccepttab implements java.io.Serializable {

	// Fields

	private DataaccepttabId id;
	private String infodata;
	private String dtmakedate;

	// Constructors

	/** default constructor */
	public Dataaccepttab() {
	}

	/** minimal constructor */
	public Dataaccepttab(DataaccepttabId id) {
		this.id = id;
	}

	/** full constructor */
	public Dataaccepttab(DataaccepttabId id, String infodata, String dtmakedate) {
		this.id = id;
		this.infodata = infodata;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public DataaccepttabId getId() {
		return this.id;
	}

	public void setId(DataaccepttabId id) {
		this.id = id;
	}

	public String getInfodata() {
		return this.infodata;
	}

	public void setInfodata(String infodata) {
		this.infodata = infodata;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}