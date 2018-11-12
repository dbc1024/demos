package com.ectrip.ticket.model.salesmanager;

/**
 * Ospbankpayeesettab entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ospbankpayeesettab implements java.io.Serializable {

	// Fields

	private Long ibankid;
	private String szbankcode;
	private String szbankname;
	private String szbankaccount;
	private Long byisuse;
	private String szmemo;
	private Long iversion;

	// Constructors

	/** default constructor */
	public Ospbankpayeesettab() {
	}

	/** full constructor */
	public Ospbankpayeesettab(Long ibankid, String szbankcode,
			String szbankname, String szbankaccount, Long byisuse,
			String szmemo, Long iversion) {
		this.ibankid = ibankid;
		this.szbankcode = szbankcode;
		this.szbankname = szbankname;
		this.szbankaccount = szbankaccount;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
	}

	// Property accessors

	public Long getIbankid() {
		return this.ibankid;
	}

	public void setIbankid(Long ibankid) {
		this.ibankid = ibankid;
	}

	public String getSzbankcode() {
		return this.szbankcode;
	}

	public void setSzbankcode(String szbankcode) {
		this.szbankcode = szbankcode;
	}

	public String getSzbankname() {
		return this.szbankname;
	}

	public void setSzbankname(String szbankname) {
		this.szbankname = szbankname;
	}

	public String getSzbankaccount() {
		return this.szbankaccount;
	}

	public void setSzbankaccount(String szbankaccount) {
		this.szbankaccount = szbankaccount;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public Long getIversion() {
		return this.iversion;
	}

	public void setIversion(Long iversion) {
		this.iversion = iversion;
	}

}