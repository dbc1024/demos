package com.ectrip.ticket.model.permitenter;

import java.math.BigDecimal;

/**
 * Iomwarehouse entity. @author MyEclipse Persistence Tools
 */

public class Iomwarehouse implements java.io.Serializable {

	// Fields

	private Long iwarehouseid; // 仓库ID
	private Long iscenicid; // 服务商ID
	private String szwarehousecode; // 仓库编号
	private String szwarehousename; // 仓库名称
	private String szcontact; // 联系人
	private String szaddress; // 电子邮件
	private Long byisuse; // 是否使用
	private Long bisdefault; // 是否总库
	private String szmemo; // 备注
	private Long int1;
	private Long int2;
	private String note1;
	private String note2;

	private String szscenicname;

	// Constructors

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	/** default constructor */
	public Iomwarehouse() {
	}

	/** minimal constructor */
	public Iomwarehouse(Long iwarehouseid, Long iscenicid,
						String szwarehousecode, String szwarehousename, Long byisuse,
						Long bisdefault) {
		this.iwarehouseid = iwarehouseid;
		this.iscenicid = iscenicid;
		this.szwarehousecode = szwarehousecode;
		this.szwarehousename = szwarehousename;
		this.byisuse = byisuse;
		this.bisdefault = bisdefault;
	}

	/** full constructor */
	public Iomwarehouse(Long iwarehouseid, Long iscenicid,
						String szwarehousecode, String szwarehousename, String szcontact,
						String szaddress, Long byisuse, Long bisdefault, String szmemo) {
		this.iwarehouseid = iwarehouseid;
		this.iscenicid = iscenicid;
		this.szwarehousecode = szwarehousecode;
		this.szwarehousename = szwarehousename;
		this.szcontact = szcontact;
		this.szaddress = szaddress;
		this.byisuse = byisuse;
		this.bisdefault = bisdefault;
		this.szmemo = szmemo;
	}

	// Property accessors

	public Long getIwarehouseid() {
		return this.iwarehouseid;
	}

	public void setIwarehouseid(Long iwarehouseid) {
		this.iwarehouseid = iwarehouseid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getSzwarehousecode() {
		return this.szwarehousecode;
	}

	public void setSzwarehousecode(String szwarehousecode) {
		this.szwarehousecode = szwarehousecode;
	}

	public String getSzwarehousename() {
		return this.szwarehousename;
	}

	public void setSzwarehousename(String szwarehousename) {
		this.szwarehousename = szwarehousename;
	}

	public String getSzcontact() {
		return this.szcontact;
	}

	public void setSzcontact(String szcontact) {
		this.szcontact = szcontact;
	}

	public String getSzaddress() {
		return this.szaddress;
	}

	public void setSzaddress(String szaddress) {
		this.szaddress = szaddress;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public Long getBisdefault() {
		return this.bisdefault;
	}

	public void setBisdefault(Long bisdefault) {
		this.bisdefault = bisdefault;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public Long getInt1() {
		return int1;
	}

	public void setInt1(Long int1) {
		this.int1 = int1;
	}

	public Long getInt2() {
		return int2;
	}

	public void setInt2(Long int2) {
		this.int2 = int2;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

}