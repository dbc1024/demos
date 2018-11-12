package com.ectrip.ticket.model.venuemarketing;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Venueprogram entity. @author MyEclipse Persistence Tools
 * 节目表
 */
@Entity
@Table(name="Venueprogram")
public class Venueprogram implements java.io.Serializable {

	// Fields
	@Id
	private Long iprogramid;//节目ID
	@Column(name="ISCENICID")
	private Long iscenicid; // 服务商编号
	@Column(name="SZPROGRAMNAME")
	private String szprogramname;//节目名称
	@Column(name="SZPROGRAMDEAC")
	private String szprogramdeac;//节目简介
	@Column(name="SZPROGRAMDETAILDESC")
	private Blob szprogramdetaildesc;//节目详细介绍
	@Column(name="BYWEBSALETYPE")
	private Long bywebsaletype;//网上销售方式
	@Column(name="BYCASHSALETYPE")
	private Long bycashsaletype;//现金销售方式
	@Column(name="DTMAKEDATE")
	private String dtmakedate;
	@Column(name="BYISUSE")
	private Long byisuse;//是否使用

	// Constructors
	@Transient
	private String detailsNote;
	@Transient
	private String striscenicid; // 服务商名称
	@Transient
	private String[] upids;
	@Transient
	private List list;

	/** default constructor */
	public Venueprogram() {
	}

	/** minimal constructor */
	public Venueprogram(Long iprogramid, String szprogramname,
						Long bywebsaletype, Long bycashsaletype,
						Long byisuse) {
		this.iprogramid = iprogramid;
		this.szprogramname = szprogramname;
		this.bywebsaletype = bywebsaletype;
		this.bycashsaletype = bycashsaletype;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Venueprogram(Long iprogramid, String szprogramname,
						String szprogramdeac, Blob szprogramdetaildesc,
						Long bywebsaletype, Long bycashsaletype,
						String dtmakedate, Long byisuse) {
		this.iprogramid = iprogramid;
		this.szprogramname = szprogramname;
		this.szprogramdeac = szprogramdeac;
		this.szprogramdetaildesc = szprogramdetaildesc;
		this.bywebsaletype = bywebsaletype;
		this.bycashsaletype = bycashsaletype;
		this.dtmakedate = dtmakedate;
		this.byisuse = byisuse;
	}

	// Property accessors

	public Long getIprogramid() {
		return this.iprogramid;
	}

	public void setIprogramid(Long iprogramid) {
		this.iprogramid = iprogramid;
	}

	public String getSzprogramname() {
		return this.szprogramname;
	}

	public void setSzprogramname(String szprogramname) {
		this.szprogramname = szprogramname;
	}

	public String getSzprogramdeac() {
		return this.szprogramdeac;
	}

	public void setSzprogramdeac(String szprogramdeac) {
		this.szprogramdeac = szprogramdeac;
	}


	public Blob getSzprogramdetaildesc() {
		return szprogramdetaildesc;
	}

	public void setSzprogramdetaildesc(Blob szprogramdetaildesc) {
		this.szprogramdetaildesc = szprogramdetaildesc;
	}

	public String getDetailsNote() {
		return detailsNote;
	}

	public void setDetailsNote(String detailsNote) {
		this.detailsNote = detailsNote;
	}

	public Long getBywebsaletype() {
		return this.bywebsaletype;
	}

	public void setBywebsaletype(Long bywebsaletype) {
		this.bywebsaletype = bywebsaletype;
	}

	public Long getBycashsaletype() {
		return this.bycashsaletype;
	}

	public void setBycashsaletype(Long bycashsaletype) {
		this.bycashsaletype = bycashsaletype;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public String[] getUpids() {
		return upids;
	}

	public void setUpids(String[] upids) {
		this.upids = upids;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}