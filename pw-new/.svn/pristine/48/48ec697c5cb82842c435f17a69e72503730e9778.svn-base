package com.ectrip.ticket.model.venuemarketing;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 场地区域。
 *
 * @author Jzhenhua<br>
 *         Date 2011-10-19
 */
@Entity
@Table(name="Venuearea")
public class Venuearea implements java.io.Serializable {

	// Fields
	@Id
	private Long ivenueareaid; // 场地区域ID
	@Column(name="IVENUEAREACODE")
	private String ivenueareacode; // 场地区域代码
	@Column(name="IVENUEAREANAME")
	private String ivenueareaname; // 场地区域名称
	@Column(name="ISVENUEAREAID")
	private Long isvenueareaid; // 上级场地区域ID
	@Column(name="IVENUEID")
	private Long ivenueid; // 场地ID
	@Column(name="ISCENICID")
	private Long iscenicid; // 服务商ID
	@Column(name="VENUETYPE")
	private String venuetype; // 场地类别
	@Column(name="IFILESDEPOSITORYID")
	private Long ifilesdepositoryid; // 对应文件
	@Column(name="MAXNUMBER")
	private Long maxnumber; // 最大可售数量
	@Column(name="IROWSCOUNT")
	private Long irowscount; // 校验行数
	@Column(name="ICOLUMNSCOUNT")
	private Long icolumnscount; // 校验列数
	@Column(name="ISEATSCOUNT")
	private Long iseatscount; // 座位数
	@Column(name="SZALLPOINT")
	private String szallpoint; // 点集合
	@Column(name="BYISUSE")
	private Long byisuse; // 使用状态,0:禁用,1:启用
	@Column(name="IAREATYPE")
	private Long iareatype; // 是否舞台
	@Column(name="SZMEMO")
	private String szmemo; // 备注
	@Column(name="IVERSION")
	private Long iversion; // 版本
	@Column(name="BISDELETE")
	private Long bisdelete; // 是否标记删除
	@Column(name="BISHISTORY")
	private Long bishistory; // 是否历史数据

	// Non-database fields
	@Transient
	private String striscenicid; // 服务商名称
	@Transient
	private String strisvenueareaid;
	@Transient
	private String parentname; // 上级地域名称
	@Transient
	private String strivenueid;//场馆名称
	@Transient
	private String venuetypename;//场馆类型名称

	// Constructors

	/** default constructor */
	public Venuearea() {
	}

	/** minimal constructor */
	public Venuearea(Long ivenueareaid, String ivenueareacode,
					 String ivenueareaname, Long isvenueareaid, Long ivenueid,
					 String venuetype, Long ifilesdepositoryid, Long maxnumber,
					 Long irowscount, Long icolumnscount, Long iseatscount,
					 Long byisuse, Long iareatype, Long iversion, Long bisdelete,
					 Long bishistory) {
		this.ivenueareaid = ivenueareaid;
		this.ivenueareacode = ivenueareacode;
		this.ivenueareaname = ivenueareaname;
		this.isvenueareaid = isvenueareaid;
		this.ivenueid = ivenueid;
		this.venuetype = venuetype;
		this.ifilesdepositoryid = ifilesdepositoryid;
		this.maxnumber = maxnumber;
		this.irowscount = irowscount;
		this.icolumnscount = icolumnscount;
		this.iseatscount = iseatscount;
		this.byisuse = byisuse;
		this.iareatype = iareatype;
		this.iversion = iversion;
		this.bisdelete = bisdelete;
		this.bishistory = bishistory;
	}

	/** full constructor */
	public Venuearea(Long ivenueareaid, String ivenueareacode,
					 String ivenueareaname, Long isvenueareaid, Long ivenueid,
					 Long iscenicid, String venuetype, Long ifilesdepositoryid,
					 Long maxnumber, Long irowscount, Long icolumnscount,
					 Long iseatscount, String szallpoint, Long byisuse, Long iareatype,
					 String szmemo, Long iversion, Long bisdelete, Long bishistory) {
		this.ivenueareaid = ivenueareaid;
		this.ivenueareacode = ivenueareacode;
		this.ivenueareaname = ivenueareaname;
		this.isvenueareaid = isvenueareaid;
		this.ivenueid = ivenueid;
		this.iscenicid = iscenicid;
		this.venuetype = venuetype;
		this.ifilesdepositoryid = ifilesdepositoryid;
		this.maxnumber = maxnumber;
		this.irowscount = irowscount;
		this.icolumnscount = icolumnscount;
		this.iseatscount = iseatscount;
		this.szallpoint = szallpoint;
		this.byisuse = byisuse;
		this.iareatype = iareatype;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.bisdelete = bisdelete;
		this.bishistory = bishistory;
	}

	// Property accessors

	public Long getIvenueareaid() {
		return this.ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

	public String getIvenueareacode() {
		return this.ivenueareacode;
	}

	public void setIvenueareacode(String ivenueareacode) {
		this.ivenueareacode = ivenueareacode;
	}

	public String getIvenueareaname() {
		return this.ivenueareaname;
	}

	public void setIvenueareaname(String ivenueareaname) {
		this.ivenueareaname = ivenueareaname;
	}

	public Long getIsvenueareaid() {
		return this.isvenueareaid;
	}

	public void setIsvenueareaid(Long isvenueareaid) {
		this.isvenueareaid = isvenueareaid;
	}

	public Long getIvenueid() {
		return this.ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getVenuetype() {
		return this.venuetype;
	}

	public void setVenuetype(String venuetype) {
		this.venuetype = venuetype;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public Long getIfilesdepositoryid() {
		return this.ifilesdepositoryid;
	}

	public void setIfilesdepositoryid(Long ifilesdepositoryid) {
		this.ifilesdepositoryid = ifilesdepositoryid;
	}

	public Long getMaxnumber() {
		return this.maxnumber;
	}

	public void setMaxnumber(Long maxnumber) {
		this.maxnumber = maxnumber;
	}

	public Long getIrowscount() {
		return this.irowscount;
	}

	public void setIrowscount(Long irowscount) {
		this.irowscount = irowscount;
	}

	public Long getIcolumnscount() {
		return this.icolumnscount;
	}

	public void setIcolumnscount(Long icolumnscount) {
		this.icolumnscount = icolumnscount;
	}

	public Long getIseatscount() {
		return this.iseatscount;
	}

	public void setIseatscount(Long iseatscount) {
		this.iseatscount = iseatscount;
	}

	public String getSzallpoint() {
		return this.szallpoint;
	}

	public void setSzallpoint(String szallpoint) {
		this.szallpoint = szallpoint;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public String getStrisvenueareaid() {
		return strisvenueareaid;
	}

	public void setStrisvenueareaid(String strisvenueareaid) {
		this.strisvenueareaid = strisvenueareaid;
	}

	public Long getIareatype() {
		return this.iareatype;
	}

	public void setIareatype(Long iareatype) {
		this.iareatype = iareatype;
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

	public Long getBisdelete() {
		return this.bisdelete;
	}

	public void setBisdelete(Long bisdelete) {
		this.bisdelete = bisdelete;
	}

	public Long getBishistory() {
		return this.bishistory;
	}

	public void setBishistory(Long bishistory) {
		this.bishistory = bishistory;
	}

	public String getStrivenueid() {
		return strivenueid;
	}

	public void setStrivenueid(String strivenueid) {
		this.strivenueid = strivenueid;
	}

	public String getVenuetypename() {
		return venuetypename;
	}

	public void setVenuetypename(String venuetypename) {
		this.venuetypename = venuetypename;
	}

}