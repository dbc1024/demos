package com.ectrip.ticket.model.salesmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ospforcedrefundsettab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Ospforcedrefundsettab implements java.io.Serializable {

	// Fields
	@Id
	private Long iforcedrefundid;// ID
	private String szforcedrefundcode;// 强行退票代码
	private String szforcedrefundname;// 强行退票名称
	@Column(name="BYISUSER")
	private Long byisuse;// 使用状态,0:禁用,1:启用
	private String szmemo;// 备注
	private Long iversion;// 版本
	private Long bisdelete;// 是否标记删除,0:已标记删除,1:未标记删除
	private Long bishistory;// 是否在历史套帐中,0为不存在,1为存在

	// Constructors

	/** default constructor */
	public Ospforcedrefundsettab() {
	}

	/** full constructor */
	public Ospforcedrefundsettab(Long iforcedrefundid,
								 String szforcedrefundcode, String szforcedrefundname,
								 Long byisuse, String szmemo, Long iversion,
								 Long bisdelete, Long bishistory) {
		this.iforcedrefundid = iforcedrefundid;
		this.szforcedrefundcode = szforcedrefundcode;
		this.szforcedrefundname = szforcedrefundname;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.bisdelete = bisdelete;
		this.bishistory = bishistory;
	}

	// Property accessors

	public Long getByisuse() {
		return byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public Long getIforcedrefundid() {
		return this.iforcedrefundid;
	}

	public void setIforcedrefundid(Long iforcedrefundid) {
		this.iforcedrefundid = iforcedrefundid;
	}

	public void setIforcedrefundid(String iforcedrefundid) {
		if (null != iforcedrefundid && !"".equals(iforcedrefundid))
			this.iforcedrefundid = new Long(iforcedrefundid);
	}

	public String getSzforcedrefundcode() {
		return this.szforcedrefundcode;
	}

	public void setSzforcedrefundcode(String szforcedrefundcode) {
		this.szforcedrefundcode = szforcedrefundcode;
	}

	public String getSzforcedrefundname() {
		return this.szforcedrefundname;
	}

	public void setSzforcedrefundname(String szforcedrefundname) {
		this.szforcedrefundname = szforcedrefundname;
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

	public void setIversion(String iversion) {
		if (null != iversion && !"".equals(iversion)) {
			this.iversion = new Long(iversion);
		} else {
			this.iversion = new Long(0);
		}
	}

	public Long getBisdelete() {
		return this.bisdelete;
	}

	public void setBisdelete(Long bisdelete) {
		this.bisdelete = bisdelete;
	}

	public void setBisdelete(String bisdelete) {
		if (null != bisdelete && !"".equals(bisdelete)) {
			this.bisdelete = new Long(bisdelete);
		} else {
			this.bisdelete = new Long(0);
		}
	}

	public Long getBishistory() {
		return this.bishistory;
	}

	public void setBishistory(String bishistory) {
		if (null != bishistory && !"".equals(bishistory)) {
			this.bishistory = new Long(bishistory);
		} else {
			this.bishistory = new Long(0);
		}
	}

	public void setBishistory(Long bishistory) {
		this.bishistory = bishistory;
	}

}