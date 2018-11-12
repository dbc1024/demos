package com.ectrip.ticket.model.venuemarketing;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Description:场地
 *
 * @author Jzhenhua<br>
 *         Date 2011-10-17
 */
@Entity
@Table(name="Venue")
public class Venue implements java.io.Serializable {

	// 数据库字段
	@Id
	private Long ivenueid; // 场地编号
	@Column(name="ISCENICID")
	private Long iscenicid; // 服务商编号
	@Column(name="VENUEIDCODE")
	private String venueidcode; // 场地代码
	@Column(name="VENUEIDNAME")
	private String venueidname; // 场地名称
	@Column(name="VENUETYPE")
	private String venuetype; // 场地类型,'01':景区,'02':景区观光车,'03':竹筏(漂流),'04':剧院,'05':索道
	@Column(name="BYSALETYPE")
	private Long bysaletype;//是否按座位售票    0:否,1：是
	@Column(name="SIMPLEINTRODUCTION")
	private String simpleintroduction;//简介
	@Column(name="DISTRIBUTION")
	private String distribution;//分布
	@Column(name="SZMEMO")
	private String szmemo; // 备注
	@Column(name="BYISUSE")
	private Long byisuse; // 使用状态,0:禁用,1:激活
	@Column(name="IROWSCOUNT")
	private Long irowscount; // 行数
	@Column(name="ICOLUMNSCOUNT")
	private Long icolumnscount; // 列数

	// 非数据库字段
	@Transient
	private String striscenicid; // 服务商名称
	@Transient
	private String providertype; // 服务商类型
	@Transient
	private String venuetypename;
	@Transient
	private String[] upids;
	@Transient
	private List list;

	// Constructors

	/** default constructor */
	public Venue() {
	}

	/** minimal constructor */
	public Venue(Long ivenueid, Long iscenicid, String venueidcode,
				 String venueidname) {
		this.ivenueid = ivenueid;
		this.iscenicid = iscenicid;
		this.venueidcode = venueidcode;
		this.venueidname = venueidname;
	}

	/** full constructor */
	public Venue(Long ivenueid, Long iscenicid, String venueidcode,
				 String venueidname, String venuetype, String szmemo, Long byisuse) {
		this.ivenueid = ivenueid;
		this.iscenicid = iscenicid;
		this.venueidcode = venueidcode;
		this.venueidname = venueidname;
		this.venuetype = venuetype;
		this.szmemo = szmemo;
		this.byisuse = byisuse;
	}

	// Property accessors

	public String getVenueidcode() {
		return this.venueidcode;
	}

	public void setVenueidcode(String venueidcode) {
		this.venueidcode = venueidcode;
	}

	public String getVenueidname() {
		return this.venueidname;
	}

	public void setVenueidname(String venueidname) {
		this.venueidname = venueidname;
	}

	public String getVenuetype() {
		return this.venuetype;
	}

	public void setVenuetype(String venuetype) {
		this.venuetype = venuetype;
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public Long getIvenueid() {
		return ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}

	public String getProvidertype() {
		return providertype;
	}

	public void setProvidertype(String providertype) {
		this.providertype = providertype;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getByisuse() {
		return byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public Long getBysaletype() {
		return bysaletype;
	}

	public void setBysaletype(Long bysaletype) {
		this.bysaletype = bysaletype;
	}

	public String getSimpleintroduction() {
		return simpleintroduction;
	}

	public void setSimpleintroduction(String simpleintroduction) {
		this.simpleintroduction = simpleintroduction;
	}

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public String[] getUpids() {
		return upids;
	}

	public void setUpids(String[] upids) {
		this.upids = upids;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getVenuetypename() {
		return venuetypename;
	}

	public void setVenuetypename(String venuetypename) {
		this.venuetypename = venuetypename;
	}

	public Long getIrowscount() {
		return irowscount;
	}

	public void setIrowscount(Long irowscount) {
		this.irowscount = irowscount;
	}

	public Long getIcolumnscount() {
		return icolumnscount;
	}

	public void setIcolumnscount(Long icolumnscount) {
		this.icolumnscount = icolumnscount;
	}

}