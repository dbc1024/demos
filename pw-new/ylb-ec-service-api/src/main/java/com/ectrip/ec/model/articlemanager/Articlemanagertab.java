package com.ectrip.ec.model.articlemanager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Articlemanagertab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Articlemanagertab implements java.io.Serializable {

	// Fields

	@Id
	private Long amid;	//主键
	private String amtopicf;	//主标题
	private String amtopics;	//副标题
	private Long cmid;			//所属栏目
	private String amdesc;		//简介
	private String amnote;		//内容
	private Long byisuse;		//是否使用
	private String dtmakedate;	//操作时间
	private String groupid;		//企业id

	//非数据表字段
	@Transient
	private String cmzhtopic;
	
	@Transient
	private String cmentopic;	//英文题目
	// Constructors

	public String getCmentopic() {
		return cmentopic;
	}

	public void setCmentopic(String cmentopic) {
		this.cmentopic = cmentopic;
	}

	/** default constructor */
	public Articlemanagertab() {
	}

	/** minimal constructor */
	public Articlemanagertab(Long amid, String amtopicf, Long cmid,
			String amnote, Long byisuse) {
		this.amid = amid;
		this.amtopicf = amtopicf;
		this.cmid = cmid;
		this.amnote = amnote;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Articlemanagertab(Long amid, String amtopicf, String amtopics,
			Long cmid, String amdesc, String amnote, Long byisuse,
			String dtmakedate,String groupid) {
		this.amid = amid;
		this.amtopicf = amtopicf;
		this.amtopics = amtopics;
		this.cmid = cmid;
		this.amdesc = amdesc;
		this.amnote = amnote;
		this.byisuse = byisuse;
		this.dtmakedate = dtmakedate;
		this.groupid = groupid;
	}

	// Property accessors

	public Long getAmid() {
		return this.amid;
	}

	public void setAmid(Long amid) {
		this.amid = amid;
	}

	public String getAmtopicf() {
		return this.amtopicf;
	}

	public void setAmtopicf(String amtopicf) {
		this.amtopicf = amtopicf;
	}

	public String getAmtopics() {
		return this.amtopics;
	}

	public void setAmtopics(String amtopics) {
		this.amtopics = amtopics;
	}

	public Long getCmid() {
		return this.cmid;
	}

	public void setCmid(Long cmid) {
		this.cmid = cmid;
	}

	public String getAmdesc() {
		return this.amdesc;
	}

	public void setAmdesc(String amdesc) {
		this.amdesc = amdesc;
	}

	public String getAmnote() {
		return this.amnote;
	}

	public void setAmnote(String amnote) {
		this.amnote = amnote;
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

	public String getCmzhtopic() {
		return cmzhtopic;
	}

	public void setCmzhtopic(String cmzhtopic) {
		this.cmzhtopic = cmzhtopic;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	

}