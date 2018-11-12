package com.ectrip.ticket.service.model;



/**
 * 售票窗口
 * @author LiuJianwen
 *
 */
//Hibernate: select esbticketw0_.ITICKETWINID as ITICKETW1_35_,
//esbticketw0_.ITICKETSTATIONID as ITICKETS2_35_,
//esbticketw0_.ISCENICID as ISCENICID35_,
//esbticketw0_.SZTICKETWINCODE as SZTICKET4_35_,
//esbticketw0_.SZTICKETWINNAME as SZTICKET5_35_,
//esbticketw0_.SZIPADDRESS as SZIPADDR6_35_,
//esbticketw0_.DTSELLSTART as DTSELLST7_35_,
//esbticketw0_.DTSELLEND as DTSELLEND35_,
//esbticketw0_.BYWINTYPE as BYWINTYPE35_,
//esbticketw0_.BYISUSE as BYISUSE35_,
//esbticketw0_.SZMEMO as SZMEMO35_,
//esbticketw0_.IVERSION as IVERSION35_ from ESBTICKETWINTAB esbticketw0_ where esbticketw0_.ISCENICID=? and esbticketw0_.SZIPADDRESS=? and esbticketw0_.BYISUSE=?

public class TicketWindow{

	private static final long serialVersionUID = 1L;
	private String iticketwinid;//售票窗口id
	private String iticketstationid;//售票点id
	private String iscenicid;//景点id
	private String szticketwincode;//窗口代码
	private String szticketwinname;//名称
	private String szipaddress;//ip地址
	private String dtsellstart;//售票开始时间
	private String dtsellend;//售票结束时间
	private String bywintype;//类型，0业务代理，1售票
	private String byisuse;//0禁用，1启用
	private String szmemo;//备注
	private String iversion;//版本
	private String ipaddress;
	private String szstationname;
	private String szscenicname;

	public TicketWindow(String iticketwinid, String iticketstationid,
						String iscenicid, String szticketwincode, String szticketwinname,
						String szipaddress, String dtsellstart, String dtsellend,
						String bywintype, String byisuse, String szmemo, String iversion,
						String ipaddress,String szstationname,String szscenicname) {
		super();
		this.iticketwinid = iticketwinid;
		this.iticketstationid = iticketstationid;
		this.iscenicid = iscenicid;
		this.szticketwincode = szticketwincode;
		this.szticketwinname = szticketwinname;
		this.szipaddress = szipaddress;
		this.dtsellstart = dtsellstart;
		this.dtsellend = dtsellend;
		this.bywintype = bywintype;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.ipaddress = ipaddress;
		this.szstationname = szstationname;
		this.szscenicname = szscenicname;
	}
	public TicketWindow() {
	}
	public String getIticketwinid() {
		return iticketwinid;
	}
	public void setIticketwinid(String iticketwinid) {
		this.iticketwinid = iticketwinid;
	}
	public String getIticketstationid() {
		return iticketstationid;
	}
	public void setIticketstationid(String iticketstationid) {
		this.iticketstationid = iticketstationid;
	}
	public String getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}
	public String getSzticketwincode() {
		return szticketwincode;
	}
	public void setSzticketwincode(String szticketwincode) {
		this.szticketwincode = szticketwincode;
	}
	public String getSzticketwinname() {
		return szticketwinname;
	}
	public void setSzticketwinname(String szticketwinname) {
		this.szticketwinname = szticketwinname;
	}
	public String getSzipaddress() {
		return szipaddress;
	}
	public void setSzipaddress(String szipaddress) {
		this.szipaddress = szipaddress;
	}
	public String getDtsellstart() {
		return dtsellstart;
	}
	public void setDtsellstart(String dtsellstart) {
		this.dtsellstart = dtsellstart;
	}
	public String getDtsellend() {
		return dtsellend;
	}
	public void setDtsellend(String dtsellend) {
		this.dtsellend = dtsellend;
	}
	public String getBywintype() {
		return bywintype;
	}
	public void setBywintype(String bywintype) {
		this.bywintype = bywintype;
	}
	public String getByisuse() {
		return byisuse;
	}
	public void setByisuse(String byisuse) {
		this.byisuse = byisuse;
	}
	public String getSzmemo() {
		return szmemo;
	}
	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}
	public String getIversion() {
		return iversion;
	}
	public void setIversion(String iversion) {
		this.iversion = iversion;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getSzstationname() {
		return szstationname;
	}
	public void setSzstationname(String szstationname) {
		this.szstationname = szstationname;
	}
	public String getSzscenicname() {
		return szscenicname;
	}
	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

}
