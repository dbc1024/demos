package com.ectrip.ticket.model.provider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Esbticketstationtab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ESBTICKETSTATIONTAB")
public class Esbticketstationtab implements java.io.Serializable {

	// Fields
	@Id
	private Long iticketstationid; // 门票站点ID
	@Column
	private Long iscenicid; // 服务商ID
	@Column
	private String szstationcode; // 门票站点代码
	@Column
	private String szstationname; // 门票站点名称
	@Column
	private String szcontact; // 联系人
	@Column
	private String szphone; // 联系电话
	@Column
	private String szaddress; // 实际地址
	@Column
	private Long byisuse; // 启用状态
	@Column
	private String szmemo; // 备注
	@Column
	private Long iversion; // 版本

	// 非数据库字段
	@Transient
	private String szscenicname; // 服务商名称

	// Constructors

	/** default constructor */
	public Esbticketstationtab() {
	}

	/** minimal constructor */
	public Esbticketstationtab(Long iticketstationid, Long iscenicid, String szstationcode, String szstationname,
			String szcontact, String szphone, String szaddress, Long byisuse, Long iversion) {
		this.iticketstationid = iticketstationid;
		this.iscenicid = iscenicid;
		this.szstationcode = szstationcode;
		this.szstationname = szstationname;
		this.szcontact = szcontact;
		this.szphone = szphone;
		this.szaddress = szaddress;
		this.byisuse = byisuse;
		this.iversion = iversion;
	}

	/** full constructor */
	public Esbticketstationtab(Long iticketstationid, Long iscenicid, String szstationcode, String szstationname,
			String szcontact, String szphone, String szaddress, Long byisuse, String szmemo, Long iversion) {
		this.iticketstationid = iticketstationid;
		this.iscenicid = iscenicid;
		this.szstationcode = szstationcode;
		this.szstationname = szstationname;
		this.szcontact = szcontact;
		this.szphone = szphone;
		this.szaddress = szaddress;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
	}

	// Property accessors

	public Long getIticketstationid() {
		return this.iticketstationid;
	}

	public void setIticketstationid(Long iticketstationid) {
		this.iticketstationid = iticketstationid;
	}

	public String getSzstationcode() {
		return this.szstationcode;
	}

	public void setSzstationcode(String szstationcode) {
		this.szstationcode = szstationcode;
	}

	public String getSzstationname() {
		return this.szstationname;
	}

	public void setSzstationname(String szstationname) {
		this.szstationname = szstationname;
	}

	public String getSzcontact() {
		return this.szcontact;
	}

	public void setSzcontact(String szcontact) {
		this.szcontact = szcontact;
	}

	public String getSzphone() {
		return this.szphone;
	}

	public void setSzphone(String szphone) {
		this.szphone = szphone;
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

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

}