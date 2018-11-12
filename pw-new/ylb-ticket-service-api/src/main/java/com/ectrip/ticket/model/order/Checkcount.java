package com.ectrip.ticket.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Checkcount entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Checkcount")
public class Checkcount implements java.io.Serializable {

	// Fields
	@Id
	private Long seq;
	@Column(name="ISCENICID")
	private Long iscenicid;
	@Column(name="STDT")
	private String stdt;
	@Column(name="IGARDENGATEID")
	private Long igardengateid;
	@Column(name="NUMB")
	private Long numb;
	@Column(name="CNUMB")
	private Long cnumb;
	@Column(name="DTMAKEDATE")
	private String dtmakedate;

	// Constructors

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	/** default constructor */
	public Checkcount() {
	}

	/** full constructor */
	public Checkcount(Long seq, Long iscenicid, String stdt, Long igardengateid,
			Long numb, Long cnumb) {
		this.seq = seq;
		this.iscenicid = iscenicid;
		this.stdt = stdt;
		this.igardengateid = igardengateid;
		this.numb = numb;
		this.cnumb = cnumb;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getStdt() {
		return this.stdt;
	}

	public void setStdt(String stdt) {
		this.stdt = stdt;
	}

	public Long getIgardengateid() {
		return this.igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getNumb() {
		return this.numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public Long getCnumb() {
		return this.cnumb;
	}

	public void setCnumb(Long cnumb) {
		this.cnumb = cnumb;
	}

}