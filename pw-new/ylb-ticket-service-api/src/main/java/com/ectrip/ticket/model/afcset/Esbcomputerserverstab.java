package com.ectrip.ticket.model.afcset;


/**
 * Esbcomputerserverstab entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Esbcomputerserverstab implements java.io.Serializable {

	// Fields

	private Long iserverid;
	private Long iscenicid;
	private String szservercode;
	private String szservername;
	private String szipaddress;
	private String sznetmask;
	private String szgateway;
	private String szdnsip;
	private Long byisuse;
	private String szmemo;

	// Constructors
	private String szscenicname;//服务商名称
	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	/** default constructor */
	public Esbcomputerserverstab() {
	}

	/** minimal constructor */
	public Esbcomputerserverstab(Long iserverid,
								 Long iscenicid, String szservercode,
								 String szservername, String szipaddress, String sznetmask,
								 String szgateway, String szdnsip, Long byisuse) {
		this.iserverid = iserverid;
		this.iscenicid = iscenicid;
		this.szservercode = szservercode;
		this.szservername = szservername;
		this.szipaddress = szipaddress;
		this.sznetmask = sznetmask;
		this.szgateway = szgateway;
		this.szdnsip = szdnsip;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Esbcomputerserverstab(Long iserverid,
								 Long iscenicid, String szservercode,
								 String szservername, String szipaddress, String sznetmask,
								 String szgateway, String szdnsip, Long byisuse, String szmemo) {
		this.iserverid = iserverid;
		this.iscenicid = iscenicid;
		this.szservercode = szservercode;
		this.szservername = szservername;
		this.szipaddress = szipaddress;
		this.sznetmask = sznetmask;
		this.szgateway = szgateway;
		this.szdnsip = szdnsip;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
	}

	// Property accessors

	public Long getIserverid() {
		return this.iserverid;
	}

	public void setIserverid(Long iserverid) {
		this.iserverid = iserverid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getSzservercode() {
		return this.szservercode;
	}

	public void setSzservercode(String szservercode) {
		this.szservercode = szservercode;
	}

	public String getSzservername() {
		return this.szservername;
	}

	public void setSzservername(String szservername) {
		this.szservername = szservername;
	}

	public String getSzipaddress() {
		return this.szipaddress;
	}

	public void setSzipaddress(String szipaddress) {
		this.szipaddress = szipaddress;
	}

	public String getSznetmask() {
		return this.sznetmask;
	}

	public void setSznetmask(String sznetmask) {
		this.sznetmask = sznetmask;
	}

	public String getSzgateway() {
		return this.szgateway;
	}

	public void setSzgateway(String szgateway) {
		this.szgateway = szgateway;
	}

	public String getSzdnsip() {
		return this.szdnsip;
	}

	public void setSzdnsip(String szdnsip) {
		this.szdnsip = szdnsip;
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