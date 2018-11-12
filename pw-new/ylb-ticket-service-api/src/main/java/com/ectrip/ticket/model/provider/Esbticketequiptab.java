package com.ectrip.ticket.model.provider;

/**
 * Esbticketequiptab entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Esbticketequiptab implements java.io.Serializable {

	// Fields

	private Long iticketequipid;//窗口设备ID
	private Long iticketstationid;//门票站点ID
	private Long iequiptypeid;//设备类型ID
	private Long iticketwinid;//窗口ID
	private Long iscenicid;//所属服务商
	private String szticketequipcode;//销售设备编号
	private String szticketequipname;//设备名称
	private String szfactorycode;//厂家编号
	private Long byisuse;//是否使用
	private String szmemo;//备注

	// Constructors

	/** default constructor */
	public Esbticketequiptab() {
	}

	/** full constructor */
	public Esbticketequiptab(Long iticketstationid,
							 Long iequiptypeid, Long iticketwinid, Long iscenicid,
							 String szticketequipcode, String szticketequipname,
							 String szfactorycode, Long byisuse, String szmemo) {
		this.iticketstationid = iticketstationid;
		this.iequiptypeid = iequiptypeid;
		this.iticketwinid = iticketwinid;
		this.iscenicid = iscenicid;
		this.szticketequipcode = szticketequipcode;
		this.szticketequipname = szticketequipname;
		this.szfactorycode = szfactorycode;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
	}

	// Property accessors

	public Long getIticketequipid() {
		return this.iticketequipid;
	}

	public void setIticketequipid(Long iticketequipid) {
		this.iticketequipid = iticketequipid;
	}

	public Long getIticketstationid() {
		return iticketstationid;
	}

	public void setIticketstationid(Long iticketstationid) {
		this.iticketstationid = iticketstationid;
	}

	public Long getIequiptypeid() {
		return this.iequiptypeid;
	}

	public void setIequiptypeid(Long iequiptypeid) {
		this.iequiptypeid = iequiptypeid;
	}

	public Long getIticketwinid() {
		return this.iticketwinid;
	}

	public void setIticketwinid(Long iticketwinid) {
		this.iticketwinid = iticketwinid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getSzticketequipcode() {
		return this.szticketequipcode;
	}

	public void setSzticketequipcode(String szticketequipcode) {
		this.szticketequipcode = szticketequipcode;
	}

	public String getSzticketequipname() {
		return this.szticketequipname;
	}

	public void setSzticketequipname(String szticketequipname) {
		this.szticketequipname = szticketequipname;
	}

	public String getSzfactorycode() {
		return this.szfactorycode;
	}

	public void setSzfactorycode(String szfactorycode) {
		this.szfactorycode = szfactorycode;
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