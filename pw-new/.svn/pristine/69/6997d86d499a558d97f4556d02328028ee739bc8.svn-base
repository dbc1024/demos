package com.ectrip.sys.model.employee;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Esppoststab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Esppoststab implements java.io.Serializable {

	// Fields
	@Id
	private Long ipostsid;//岗位ID
	private String szpostscode;//岗位代码
	private String szpostsname;//岗位名称
	private Long bycategorytype;//岗位类型
	private String szcreator;//建立者
	private Date dtcreateddate;//建立日期时间
	private Long byisuse;//使用状态
	private String szmemo;//备注
	private Long iversion;//版本
	private String posttype; //岗位类别
	private Long icompanyinfoId;  //企业ID

	// Constructors

	/** default constructor */
	public Esppoststab() {
	}

	/** minimal constructor */
	public Esppoststab(Long ipostsid, String szpostscode, String szpostsname, Long bycategorytype, String szcreator,
			Date dtcreateddate, Long byisuse, Long iversion,String posttype) {
		this.ipostsid = ipostsid;
		this.szpostscode = szpostscode;
		this.szpostsname = szpostsname;
		this.bycategorytype = bycategorytype;
		this.szcreator = szcreator;
		this.dtcreateddate = dtcreateddate;
		this.byisuse = byisuse;
		this.iversion = iversion;
		this.posttype=posttype;
	}

	/** full constructor */
	public Esppoststab(Long ipostsid, String szpostscode, String szpostsname, Long bycategorytype, String szcreator,
			Date dtcreateddate, Long byisuse, String szmemo, Long iversion,String posttype) {
		this.ipostsid = ipostsid;
		this.szpostscode = szpostscode;
		this.szpostsname = szpostsname;
		this.bycategorytype = bycategorytype;
		this.szcreator = szcreator;
		this.dtcreateddate = dtcreateddate;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.posttype=posttype;
	}

	// Property accessors

	public Long getIpostsid() {
		return this.ipostsid;
	}

	public void setIpostsid(Long ipostsid) {
		this.ipostsid = ipostsid;
	}

	public String getSzpostscode() {
		return this.szpostscode;
	}

	public void setSzpostscode(String szpostscode) {
		this.szpostscode = szpostscode;
	}

	public String getSzpostsname() {
		return this.szpostsname;
	}

	public void setSzpostsname(String szpostsname) {
		this.szpostsname = szpostsname;
	}

	public Long getBycategorytype() {
		return this.bycategorytype;
	}

	public void setBycategorytype(Long bycategorytype) {
		this.bycategorytype = bycategorytype;
	}

	public String getSzcreator() {
		return this.szcreator;
	}

	public void setSzcreator(String szcreator) {
		this.szcreator = szcreator;
	}

	public Date getDtcreateddate() {
		return this.dtcreateddate;
	}

	public void setDtcreateddate(Date dtcreateddate) {
		this.dtcreateddate = dtcreateddate;
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

	public String getPosttype() {
		return posttype;
	}

	public void setPosttype(String posttype) {
		this.posttype = posttype;
	}

	public Long getIcompanyinfoId() {
		return icompanyinfoId;
	}

	public void setIcompanyinfoId(Long icompanyinfoId) {
		this.icompanyinfoId = icompanyinfoId;
	}

}