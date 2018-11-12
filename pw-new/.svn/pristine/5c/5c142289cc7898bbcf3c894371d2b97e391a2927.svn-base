package com.ectrip.sys.model.syspar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Printticketmanage entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Printticketmanage implements java.io.Serializable {

	// Fields
	@Id
	private PrintticketmanageId id;
	private Long ordernum;
	// Constructors
	@Transient
	private String[] printnos;
	@Transient
	private String szscenicname;
	@Transient
	private String szbusinessname;
	@Transient
	private String szprintno;
	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getSzbusinessname() {
		return szbusinessname;
	}

	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}

	public String getSzprintno() {
		return szprintno;
	}

	public void setSzprintno(String szprintno) {
		this.szprintno = szprintno;
	}

	public String[] getPrintnos() {
		return printnos;
	}

	public void setPrintnos(String[] printnos) {
		this.printnos = printnos;
	}

	/** default constructor */
	public Printticketmanage() {
	}

	/** full constructor */
	public Printticketmanage(PrintticketmanageId id, Long ordernum) {
		this.id = id;
		this.ordernum = ordernum;
	}

	// Property accessors

	public PrintticketmanageId getId() {
		return this.id;
	}

	public void setId(PrintticketmanageId id) {
		this.id = id;
	}

	public Long getOrdernum() {
		return this.ordernum;
	}

	public void setOrdernum(Long ordernum) {
		this.ordernum = ordernum;
	}

}