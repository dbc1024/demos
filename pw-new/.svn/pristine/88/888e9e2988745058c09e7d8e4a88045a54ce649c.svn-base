package com.ectrip.sys.model.syspar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ectrip.base.util.PaginationSupport;


/**
 * Galsourceregiontab entity. @author MyEclipse Persistence Tools
 *客源地
 */

@Entity
@Table(name="GALSOURCEREGIONTAB")
public class Galsourceregiontab implements java.io.Serializable {

	// Fields

	@Id
	private Long iregionalid;//客源地id
	@Column
	private Long irootid=0L;//根节点id
	@Column
	private Long iparentid=0L;//父节点id
	@Column
	private Long ilevel;//层
	@Column
	private Long ilevelsequence;//层序号
	@Column
	private String bisleaf="0";//是否叶子节点
	@Column
	private String szinnerid=" ";//隶属关系id
	@Column
	private String szinnercode=" ";//隶属关系代码
	@Column
	private String szinnername=" ";//隶属关系名称
	@Column
	private String szregionalcode;//客源地代码
	@Column
	private String szregionalname;//客源地名称
	@Column
	private Long byisuse;//使用状态
	@Column
	private String szmemo=" ";//备注
	@Column
	private Long iversion=1L;//版本
	@Column
	private String bisdelete="0";//是否删除
	@Column
	private String bishistory="0";//是否历史数据
	
	
	//非数据库字段
	@Transient
	private PaginationSupport providerlist;//产口列表
	@Transient
    private  List  sceniclist;
	@Transient
    private List<String> childIds = new ArrayList<String>();//子节点

	public List getSceniclist() {
		return sceniclist;
	}



	public void setSceniclist(List sceniclist) {
		this.sceniclist = sceniclist;
	}



	public PaginationSupport getProviderlist() {
		return providerlist;
	}



	public void setProviderlist(PaginationSupport providerlist) {
		this.providerlist = providerlist;
	}



	// Constructors
	/** default constructor */
	public Galsourceregiontab() {
	}

	

	/** full constructor */
	public Galsourceregiontab(Long irootid, Long iparentid,
			Long ilevel, Long ilevelsequence, String bisleaf,
			String szinnerid, String szinnercode, String szinnername,
			String szregionalcode, String szregionalname, Long byisuse,
			String szmemo, Long iversion, String bisdelete,
			String bishistory) {
		this.irootid = irootid;
		this.iparentid = iparentid;
		this.ilevel = ilevel;
		this.ilevelsequence = ilevelsequence;
		this.bisleaf = bisleaf;
		this.szinnerid = szinnerid;
		this.szinnercode = szinnercode;
		this.szinnername = szinnername;
		this.szregionalcode = szregionalcode;
		this.szregionalname = szregionalname;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.bisdelete = bisdelete;
		this.bishistory = bishistory;
	}

	// Property accessors

	public Long getIregionalid() {
		return this.iregionalid;
	}

	public void setIregionalid(Long iregionalid) {
		this.iregionalid = iregionalid;
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

	public Long getIlevel() {
		return this.ilevel;
	}

	public void setIlevel(Long ilevel) {
		this.ilevel = ilevel;
	}

	public Long getIlevelsequence() {
		return this.ilevelsequence;
	}

	public void setIlevelsequence(Long ilevelsequence) {
		this.ilevelsequence = ilevelsequence;
	}

	public String getBisleaf() {
		return this.bisleaf;
	}

	public void setBisleaf(String bisleaf) {
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

	public String getSzregionalcode() {
		return this.szregionalcode;
	}

	public void setSzregionalcode(String szregionalcode) {
		this.szregionalcode = szregionalcode;
	}

	public String getSzregionalname() {
		return this.szregionalname;
	}

	public void setSzregionalname(String szregionalname) {
		this.szregionalname = szregionalname;
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

	public Long getIversion() {
		return this.iversion;
	}

	public void setIversion(Long iversion) {
		this.iversion = iversion;
	}

	public String getBisdelete() {
		return this.bisdelete;
	}

	public void setBisdelete(String bisdelete) {
		this.bisdelete = bisdelete;
	}

	public String getBishistory() {
		return this.bishistory;
	}

	public void setBishistory(String bishistory) {
		this.bishistory = bishistory;
	}

	public List<String> getChildIds() {
		return childIds;
	}

	public void setChildIds(List<String> childIds) {
		this.childIds = childIds;
	}
}