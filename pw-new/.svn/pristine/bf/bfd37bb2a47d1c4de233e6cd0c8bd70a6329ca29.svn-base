package com.ectrip.ec.model.articlemanager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Columnmanagertab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Columnmanagertab implements java.io.Serializable {

	// Fields

	@Id
	private Long cmid;	//主键
	private String cmzhtopic;	//中文题目
	private String cmentopic;	//英文题目	
	private String cmdesc;	//简介
	private Long byisuse;	//是否使用
	private String dtmakedate;	//操作时间


	// Constructors

	/** default constructor */
	public Columnmanagertab() {
	}

	/** minimal constructor */
	public Columnmanagertab(Long cmid, String cmzhtopic,
			String cmentopic, Long byisuse) {
		this.cmid = cmid;
		this.cmzhtopic = cmzhtopic;
		this.cmentopic = cmentopic;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Columnmanagertab(Long cmid, String cmzhtopic,
			String cmentopic, String cmdesc, Long byisuse,
			String dtmakedate) {
		this.cmid = cmid;
		this.cmzhtopic = cmzhtopic;
		this.cmentopic = cmentopic;
		this.cmdesc = cmdesc;
		this.byisuse = byisuse;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public Long getCmid() {
		return this.cmid;
	}

	public void setCmid(Long cmid) {
		this.cmid = cmid;
	}

	public String getCmzhtopic() {
		return this.cmzhtopic;
	}

	public void setCmzhtopic(String cmzhtopic) {
		this.cmzhtopic = cmzhtopic;
	}

	public String getCmentopic() {
		return this.cmentopic;
	}

	public void setCmentopic(String cmentopic) {
		this.cmentopic = cmentopic;
	}

	public String getCmdesc() {
		return this.cmdesc;
	}

	public void setCmdesc(String cmdesc) {
		this.cmdesc = cmdesc;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}