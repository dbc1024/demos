package com.ectrip.sys.model.syspar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Soderollprintmanage implements java.io.Serializable {
	@Id
	private SoderollprintmanageId id;
	private Long ordernum;

	@Transient
	private String note;
	@Transient
	private String printype;
	@Transient
	private String[] printnos;
	@Transient
	private String szscenicname;
	@Transient
	private String szbusinessname;
	@Transient
	private String szprintno;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPrintype() {
		return printype;
	}

	public void setPrintype(String printype) {
		this.printype = printype;
	}

	public SoderollprintmanageId getId() {
		return id;
	}

	public void setId(SoderollprintmanageId id) {
		this.id = id;
	}

	public Long getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Long ordernum) {
		this.ordernum = ordernum;
	}

	public String[] getPrintnos() {
		return printnos;
	}

	public void setPrintnos(String[] printnos) {
		this.printnos = printnos;
	}

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

	/** default constructor */
	public Soderollprintmanage() {
	}

	/** full constructor */
	public Soderollprintmanage(SoderollprintmanageId id, Long ordernum) {
		this.id = id;
		this.ordernum = ordernum;
	}

}
