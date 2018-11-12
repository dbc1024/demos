package com.ectrip.ticket.model.permitenter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Reservechannel entity. @author MyEclipse Persistence Tools
 * 预约通道管理
 */
@Entity
@Table(name="Reservechannel")
public class Reservechannel implements java.io.Serializable {

	// Fields
	@Id
	private Long id;//主键ID
	@Column(name="CHANNELID")
	private Long channelid;//通道ID    Esbaccessequiptab
	@Column(name="ISCENICID")
	private Long iscenicid;//服务商ID  Esbscenicareatab
	@Column(name="IGARDENGATEID")
	private Long igardengateid;//园门ID   Esbgardengatetab
	@Column(name="DTMAKEDATE")
	private String dtmakedate;//创建时间
	//非数据库字段
	@Transient
	private String striscenicid;//服务商名称
	@Transient
	private String strigardengateid;//园门名称
	@Transient
	private String striaccessequipid;//设备名称


	// Constructors

	/** default constructor */
	public Reservechannel() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public String getStriaccessequipid() {
		return striaccessequipid;
	}

	public void setStriaccessequipid(String striaccessequipid) {
		this.striaccessequipid = striaccessequipid;
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public String getStrigardengateid() {
		return strigardengateid;
	}

	public void setStrigardengateid(String strigardengateid) {
		this.strigardengateid = strigardengateid;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChannelid() {
		return this.channelid;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIgardengateid() {
		return igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

}