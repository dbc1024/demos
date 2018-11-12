package com.ectrip.sys.model.syspar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Syslog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="SYSLOG")
public class Syslog implements java.io.Serializable {

	// Fields

	@Id
	private Long logid;
	@Column
	private Long iemployeeid;
	@Column
	private String stlg;
	@Column
	private String brief;
	@Column
	private String note;
	@Column
	private String logdatetime;
	
	@Transient
	private String szemployeename;

	// Constructors

	/** default constructor */
	public Syslog() {
	}

	/** minimal constructor */
	public Syslog(Long logid, Long iemployeeid, String stlg,
			String brief, String logdatetime) {
		this.logid = logid;
		this.iemployeeid = iemployeeid;
		this.stlg = stlg;
		this.brief = brief;
		this.logdatetime = logdatetime;
	}

	/** full constructor */
	public Syslog(Long logid, Long iemployeeid, String stlg,
			String brief, String note, String logdatetime) {
		this.logid = logid;
		this.iemployeeid = iemployeeid;
		this.stlg = stlg;
		this.brief = brief;
		this.note = note;
		this.logdatetime = logdatetime;
	}

	// Property accessors

	public Long getLogid() {
		return this.logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}
	public Long getIemployeeid() {
		return iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getStlg() {
		return this.stlg;
	}

	public void setStlg(String stlg) {
		this.stlg = stlg;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLogdatetime() {
		return this.logdatetime;
	}

	public void setLogdatetime(String logdatetime) {
		this.logdatetime = logdatetime;
	}

	public String getSzemployeename() {
		return szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
	}

}