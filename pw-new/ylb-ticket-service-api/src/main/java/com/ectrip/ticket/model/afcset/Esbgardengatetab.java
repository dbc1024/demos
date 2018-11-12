package com.ectrip.ticket.model.afcset;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Esbgardengatetab entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="Esbgardengatetab")
public class Esbgardengatetab implements java.io.Serializable {

	// Fields
	private static final String Esbgardengatetab = "Esbgardengatetab";

	@Id
	private EsbgardengatetabId id; // 园门ID
	private Long isgardengateid; // 父园门ID
	private String szgardengatecode; // 园门代码
	private String szgardengatename; // 园门名称
	private Long bygardengateindex; // 园门序号
	private String szaddress; // 实际地址
	private String szcontact; // 联系人
	private String szphone; // 联系电话
	private Long byisuse; // 使用状态0禁用1启用
	private Long byiscont; //园门类型  0 入口 1 码头
	private String szmemo; // 备注

	// 非数据库字段
	@Transient
	private Long igardengateid;
	@Transient
	private Long iscenicid;
	@Transient
	private String striscenicid;
	@Transient
	private String parentname;
	@Transient
	private String szscenicname;
	// Constructors

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	/** default constructor */
	public Esbgardengatetab() {
	}

	/** minimal constructor */
	public Esbgardengatetab(EsbgardengatetabId id, String szgardengatecode,
							String szgardengatename, Long bygardengateindex, Long byisuse,Long byiscont) {
		this.id = id;
		this.szgardengatecode = szgardengatecode;
		this.szgardengatename = szgardengatename;
		this.bygardengateindex = bygardengateindex;
		this.byisuse = byisuse;
		this.byiscont=byiscont;
	}

	/** full constructor */
	public Esbgardengatetab(EsbgardengatetabId id, Long isgardengateid,
							String szgardengatecode, String szgardengatename,
							Long bygardengateindex, String szaddress, String szcontact,
							String szphone, Long byisuse,Long byiscont, String szmemo, Set opwwicketsettabs,
							Set esbaccessequiptabs) {
		this.id = id;
		this.isgardengateid = isgardengateid;
		this.szgardengatecode = szgardengatecode;
		this.szgardengatename = szgardengatename;
		this.bygardengateindex = bygardengateindex;
		this.szaddress = szaddress;
		this.szcontact = szcontact;
		this.szphone = szphone;
		this.byisuse = byisuse;
		this.byiscont=byiscont;
		this.szmemo = szmemo;
	}

	// Property accessors

	public EsbgardengatetabId getId() {
		return this.id;
	}

	public void setId(EsbgardengatetabId id) {
		this.id = id;
	}

	public Long getIsgardengateid() {
		return this.isgardengateid;
	}

	public Long getIgardengateid() {
		return igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public void setIsgardengateid(String isgardengateid) {
		if (null != isgardengateid && !"".equals(isgardengateid))
			this.isgardengateid = new Long(isgardengateid);
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public void setIsgardengateid(Long isgardengateid) {
		this.isgardengateid = isgardengateid;
	}

	public String getSzgardengatecode() {
		return this.szgardengatecode;
	}

	public void setSzgardengatecode(String szgardengatecode) {
		this.szgardengatecode = szgardengatecode;
	}

	public String getSzgardengatename() {
		return this.szgardengatename;
	}

	public void setSzgardengatename(String szgardengatename) {
		this.szgardengatename = szgardengatename;
	}

	public Long getBygardengateindex() {
		return this.bygardengateindex;
	}

	public void setBygardengateindex(Long bygardengateindex) {
		this.bygardengateindex = bygardengateindex;
	}

	public String getSzaddress() {
		return this.szaddress;
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public void setSzaddress(String szaddress) {
		this.szaddress = szaddress;
	}

	public String getSzcontact() {
		return this.szcontact;
	}

	public void setSzcontact(String szcontact) {
		this.szcontact = szcontact;
	}

	public String getSzphone() {
		return this.szphone;
	}

	public void setSzphone(String szphone) {
		this.szphone = szphone;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(String byisuse) {
		this.byisuse = new Long(byisuse);
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

	public Long getByiscont() {
		return byiscont;
	}

	public void setByiscont(Long byiscont) {
		this.byiscont = byiscont;
	}

}