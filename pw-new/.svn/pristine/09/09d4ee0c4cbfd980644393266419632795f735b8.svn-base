package com.ectrip.ticket.model.provider;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Esbequiptypetab entity.
 * 设备类型表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Esbequiptypetab implements java.io.Serializable {

	// Fields

	@Id
	private Long iequiptypeid;//设备类型id
	private Long  iscenicid;//服务商编号;
	private String szequiptypemodel;//设备类型型号
	private String szequiptypename;//设备类型名称
	private String szcommmode;//通讯方式（01RS232,02 RS485,3 USB-COM,04 USB,05 LAN,06 TCP/IP）
	private Long bycategorytype;//设备种类（0窗口设备1准进设备）
	private Long byisuse;//状态
	private String szmemo;//备注

	private String szscenicname;   //服务商名称
	
	// Constructors

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	/** default constructor */
	public Esbequiptypetab() {
	}

	/** minimal constructor */
	public Esbequiptypetab(Long iequiptypeid, String szequiptypemodel,
			String szequiptypename, String szcommmode, Long bycategorytype,
			Long byisuse) {
		this.iequiptypeid = iequiptypeid;
		this.szequiptypemodel = szequiptypemodel;
		this.szequiptypename = szequiptypename;
		this.szcommmode = szcommmode;
		this.bycategorytype = bycategorytype;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Esbequiptypetab(Long iequiptypeid,
			 Long  iscenicid, String szequiptypemodel,
			String szequiptypename, String szcommmode, Long bycategorytype,
			Long byisuse, String szmemo) {
		this.iequiptypeid = iequiptypeid;
		this.iscenicid = iscenicid;
		this.szequiptypemodel = szequiptypemodel;
		this.szequiptypename = szequiptypename;
		this.szcommmode = szcommmode;
		this.bycategorytype = bycategorytype;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
	}

	// Property accessors

	public Long getIequiptypeid() {
		return this.iequiptypeid;
	}

	public void setIequiptypeid(Long iequiptypeid) {
		this.iequiptypeid = iequiptypeid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getSzequiptypemodel() {
		return this.szequiptypemodel;
	}

	public void setSzequiptypemodel(String szequiptypemodel) {
		this.szequiptypemodel = szequiptypemodel;
	}

	public String getSzequiptypename() {
		return this.szequiptypename;
	}

	public void setSzequiptypename(String szequiptypename) {
		this.szequiptypename = szequiptypename;
	}

	public String getSzcommmode() {
		return this.szcommmode;
	}

	public void setSzcommmode(String szcommmode) {
		this.szcommmode = szcommmode;
	}

	public Long getBycategorytype() {
		return this.bycategorytype;
	}

	public void setBycategorytype(Long bycategorytype) {
		this.bycategorytype = bycategorytype;
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

}
