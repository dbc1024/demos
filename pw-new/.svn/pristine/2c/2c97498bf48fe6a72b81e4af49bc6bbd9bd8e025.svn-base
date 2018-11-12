package com.ectrip.ticket.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ticketprintlist entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Ticketprintlist implements java.io.Serializable {

	// Fields
	@Id
	private Long printid;
	private Long iemployeeid;
	private String szsalesvoucherno;
	private String szticketprintno;
	private String printtype;
	private String printtime;
	private Long isok;
    private String newszticketprintno;

	// Constructors

	/** default constructor */
	public Ticketprintlist() {
	}

	/** minimal constructor */
	public Ticketprintlist(Long printid, Long iemployeeid,
			String szticketprintno, String printtype, String printtime, Long isok) {
		this.printid = printid;
		this.iemployeeid = iemployeeid;
		this.szticketprintno = szticketprintno;
		this.printtype = printtype;
		this.printtime = printtime;
		this.isok = isok;
	}

	/** full constructor */
	public Ticketprintlist(Long printid, Long iemployeeid,
			String szsalesvoucherno, String szticketprintno, String printtype,
			String printtime, Long isok) {
		this.printid = printid;
		this.iemployeeid = iemployeeid;
		this.szsalesvoucherno = szsalesvoucherno;
		this.szticketprintno = szticketprintno;
		this.printtype = printtype;
		this.printtime = printtime;
		this.isok = isok;
	}

	// Property accessors

	public Long getPrintid() {
		return this.printid;
	}

	public void setPrintid(Long printid) {
		this.printid = printid;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getSzsalesvoucherno() {
		return this.szsalesvoucherno;
	}

	public void setSzsalesvoucherno(String szsalesvoucherno) {
		this.szsalesvoucherno = szsalesvoucherno;
	}

	public String getSzticketprintno() {
		return this.szticketprintno;
	}

	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}

	public String getPrinttype() {
		return this.printtype;
	}

	public void setPrinttype(String printtype) {
		this.printtype = printtype;
	}

	public String getPrinttime() {
		return this.printtime;
	}

	public void setPrinttime(String printtime) {
		this.printtime = printtime;
	}

	public Long getIsok() {
		return this.isok;
	}

	public void setIsok(Long isok) {
		this.isok = isok;
	}

    public String getNewszticketprintno() {
        return newszticketprintno;
    }

    public void setNewszticketprintno(String newszticketprintno) {
        this.newszticketprintno = newszticketprintno;
    }
}