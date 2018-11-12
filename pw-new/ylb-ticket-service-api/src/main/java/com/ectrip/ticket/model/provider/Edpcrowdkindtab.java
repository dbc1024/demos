package com.ectrip.ticket.model.provider;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Edpcrowdkindtab entity. @author MyEclipse Persistence Tools
 * 人群种类
 */
@Entity
@Table
public class Edpcrowdkindtab implements java.io.Serializable {

	// Fields
	@Id
	private Long icrowdkindid;//人群种类Id
	private Long irootid;//根节点id
	private Long iparentid;//父节点id
	private Integer ilevel;//层
	private Integer ilevelsequence;//层序号
	private Integer bisleaf;//是否叶子节点
	private String szinnerid;//隶属关系id
	private String szinnercode;//隶属关系代码
	private String szinnername;//隶属关系名称
	private String szcrowdkindcode;//人类种群代码
	private String szcrowdkindname;//人类种群名称
	private Integer byregfingerprintmode;//登记身份（0不登记，1检票时，2售票时）
	private Integer byregfingerprinttype;//身份识别（0无，1，指纹，2身份证，3指纹+身份证）
	private Integer bystorage;//存储方式（0联机存储，1卡内存储）
	private Integer byisuse;//使用状态（0禁用，1启用）
	private String szmemo;//备注
	private String ticketcolor;//票颜色

	//数据库中不存在字段
	@Transient
	private String strpareant;//父名称

	// Constructors

	/** default constructor */
	public Edpcrowdkindtab() {
	}

	/** full constructor */
	public Edpcrowdkindtab(Long icrowdkindid, Long irootid,
						   Long iparentid, Integer ilevel, Integer ilevelsequence,
						   Integer bisleaf, String szinnerid, String szinnercode,
						   String szinnername, String szcrowdkindcode, String szcrowdkindname,
						   Integer byregfingerprintmode, Integer byregfingerprinttype,
						   Integer bystorage, Integer byisuse, String szmemo,String ticketcolor) {
		this.icrowdkindid = icrowdkindid;
		this.irootid = irootid;
		this.iparentid = iparentid;
		this.ilevel = ilevel;
		this.ilevelsequence = ilevelsequence;
		this.bisleaf = bisleaf;
		this.szinnerid = szinnerid;
		this.szinnercode = szinnercode;
		this.szinnername = szinnername;
		this.szcrowdkindcode = szcrowdkindcode;
		this.szcrowdkindname = szcrowdkindname;
		this.byregfingerprintmode = byregfingerprintmode;
		this.byregfingerprinttype = byregfingerprinttype;
		this.bystorage = bystorage;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.ticketcolor = ticketcolor;
	}

	// Property accessors

	public Long getIcrowdkindid() {
		return this.icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public Long getIrootid() {
		return this.irootid;
	}

	public void setIrootid(Long irootid) {
		this.irootid = irootid;
	}

	public Long getIparentid() {
		return this.iparentid;
	}

	public void setIparentid(Long iparentid) {
		this.iparentid = iparentid;
	}

	public Integer getIlevel() {
		return this.ilevel;
	}

	public void setIlevel(Integer ilevel) {
		this.ilevel = ilevel;
	}

	public Integer getIlevelsequence() {
		return this.ilevelsequence;
	}

	public void setIlevelsequence(Integer ilevelsequence) {
		this.ilevelsequence = ilevelsequence;
	}

	public Integer getBisleaf() {
		return this.bisleaf;
	}

	public void setBisleaf(Integer bisleaf) {
		this.bisleaf = bisleaf;
	}

	public String getSzinnerid() {
		return this.szinnerid;
	}

	public void setSzinnerid(String szinnerid) {
		this.szinnerid = szinnerid;
	}

	public String getSzinnercode() {
		return this.szinnercode;
	}

	public void setSzinnercode(String szinnercode) {
		this.szinnercode = szinnercode;
	}

	public String getSzinnername() {
		return this.szinnername;
	}

	public void setSzinnername(String szinnername) {
		this.szinnername = szinnername;
	}

	public String getSzcrowdkindcode() {
		return this.szcrowdkindcode;
	}

	public void setSzcrowdkindcode(String szcrowdkindcode) {
		this.szcrowdkindcode = szcrowdkindcode;
	}

	public String getSzcrowdkindname() {
		return this.szcrowdkindname;
	}

	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}

	public Integer getByregfingerprintmode() {
		return this.byregfingerprintmode;
	}

	public void setByregfingerprintmode(Integer byregfingerprintmode) {
		this.byregfingerprintmode = byregfingerprintmode;
	}

	public Integer getByregfingerprinttype() {
		return this.byregfingerprinttype;
	}

	public void setByregfingerprinttype(Integer byregfingerprinttype) {
		this.byregfingerprinttype = byregfingerprinttype;
	}

	public Integer getBystorage() {
		return this.bystorage;
	}

	public void setBystorage(Integer bystorage) {
		this.bystorage = bystorage;
	}

	public Integer getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Integer byisuse) {
		this.byisuse = byisuse;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public String getStrpareant() {
		return strpareant;
	}

	public void setStrpareant(String strpareant) {
		this.strpareant = strpareant;
	}

	public String getTicketcolor() {
		return ticketcolor;
	}

	public void setTicketcolor(String ticketcolor) {
		this.ticketcolor = ticketcolor;
	}
}