package com.ectrip.sys.model.employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Galcompanyinfotab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Galcompanyinfotab implements java.io.Serializable {

	// Fields

	@Id
	private Long icompanyinfoid;
	private Long szinfocomid;
	private String szcompanyemblem;
	private String szcompanyname;
	private String szaddress;
	private String sztel;
	private String szfax;
	private String szbankaccount;
	private String szemail;
	private String companytype;
	private String szwebsite;
	private String szmemo;
	private Long iversion;
	private String szcompanycode;//企业编码
	@Transient
	private String imgUrl;

	// Constructors
	@Transient
	private Long[] iscenicids; 
	@Transient
	private String strcomtype;//企业类别
	public String getStrcomtype() {
		return strcomtype;
	}

	public void setStrcomtype(String strcomtype) {
		this.strcomtype = strcomtype;
	}

	public Long[] getIscenicids() {
		return iscenicids;
	}

	public void setIscenicids(Long[] iscenicids) {
		this.iscenicids = iscenicids;
	}

	/** default constructor */
	public Galcompanyinfotab() {
	}

	/** minimal constructor */
	public Galcompanyinfotab(Long icompanyinfoid, String szcompanyemblem,
			String szcompanyname, String szaddress, String szbankaccount,
			String companytype, Long iversion) {
		this.icompanyinfoid = icompanyinfoid;
		this.szcompanyemblem = szcompanyemblem;
		this.szcompanyname = szcompanyname;
		this.szaddress = szaddress;
		this.szbankaccount = szbankaccount;
		this.companytype = companytype;
		this.iversion = iversion;
	}

	/** full constructor */
	public Galcompanyinfotab(Long icompanyinfoid, Long szinfocomid,
			String szcompanyemblem, String szcompanyname, String szaddress,
			String sztel, String szfax, String szbankaccount, String szemail,
			String companytype, String szwebsite, String szmemo, Long iversion) {
		this.icompanyinfoid = icompanyinfoid;
		this.szinfocomid = szinfocomid;
		this.szcompanyemblem = szcompanyemblem;
		this.szcompanyname = szcompanyname;
		this.szaddress = szaddress;
		this.sztel = sztel;
		this.szfax = szfax;
		this.szbankaccount = szbankaccount;
		this.szemail = szemail;
		this.companytype = companytype;
		this.szwebsite = szwebsite;
		this.szmemo = szmemo;
		this.iversion = iversion;

	}

	// Property accessors

	public Long getIcompanyinfoid() {
		return this.icompanyinfoid;
	}

	public void setIcompanyinfoid(Long icompanyinfoid) {
		this.icompanyinfoid = icompanyinfoid;
	}

	public Long getSzinfocomid() {
		return this.szinfocomid;
	}

	public void setSzinfocomid(Long szinfocomid) {
		this.szinfocomid = szinfocomid;
	}

	public String getSzcompanyemblem() {
		return this.szcompanyemblem;
	}

	public void setSzcompanyemblem(String szcompanyemblem) {
		this.szcompanyemblem = szcompanyemblem;
	}

	public String getSzcompanyname() {
		return this.szcompanyname;
	}

	public void setSzcompanyname(String szcompanyname) {
		this.szcompanyname = szcompanyname;
	}

	public String getSzaddress() {
		return this.szaddress;
	}

	public void setSzaddress(String szaddress) {
		this.szaddress = szaddress;
	}

	public String getSztel() {
		return this.sztel;
	}

	public void setSztel(String sztel) {
		this.sztel = sztel;
	}

	public String getSzfax() {
		return this.szfax;
	}

	public void setSzfax(String szfax) {
		this.szfax = szfax;
	}

	public String getSzbankaccount() {
		return this.szbankaccount;
	}

	public void setSzbankaccount(String szbankaccount) {
		this.szbankaccount = szbankaccount;
	}

	public String getSzemail() {
		return this.szemail;
	}

	public void setSzemail(String szemail) {
		this.szemail = szemail;
	}

	public String getCompanytype() {
		return this.companytype;
	}

	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}

	public String getSzwebsite() {
		return this.szwebsite;
	}

	public void setSzwebsite(String szwebsite) {
		this.szwebsite = szwebsite;
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

	public String getSzcompanycode() {
		return szcompanycode;
	}

	public void setSzcompanycode(String szcompanycode) {
		this.szcompanycode = szcompanycode;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}