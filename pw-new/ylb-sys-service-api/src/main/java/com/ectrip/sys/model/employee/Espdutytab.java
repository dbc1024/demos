package com.ectrip.sys.model.employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Espdutytab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Espdutytab implements java.io.Serializable {

	// Fields
	@Id
	private Long idutyid;
	private Long irootid;
	private Long iparentid;
	private Long ilevel;
	private Long ilevelsequence;
	private Long bisleaf;
	private String szinnerid;
	private String szinnercode;
	private String szinnername;
	private String szdutycode;
	private String szdutyname;
	private String bisshowimage;
	private String szimagesrc;
	private String szgotopage;
	private String bypurviewtype;//权限类型
	private String bycategorytype;//职责类型
	private Long byisuse;
	private String szloadresource;
	private String szmemo;
	private Long iversion;
	private Long szorderby;
	private String dutype;//职责类别
	private Long issellticket;

	
	
	private String strdutype;//职责类别
	private String strbypurviewtype;//权限类型
	private String strbycategorytype;//职责类型
	
	//何安洲新加，2017-05-02，在职责管理中
	//添加了菜单图标图片的上传
	private Long picidf; // 图片ID
	private String picurlf; // 图片链接
	private Long picids; // 图片ID
	private String picurls; // 图片链接
	
	// Constructors

	/** default constructor */
	public Espdutytab() {
	}
	
	//何安洲新加，2017-05-02，有参构造
	public Espdutytab(Long idutyid, Long irootid, Long iparentid, Long ilevel,
			Long ilevelsequence, Long bisleaf, String szinnerid,
			String szinnercode, String szinnername, String szdutycode,
			String szdutyname, String bisshowimage, String szimagesrc,
			String szgotopage, String bypurviewtype, String bycategorytype,
			Long byisuse, String szloadresource, String szmemo, Long iversion,
			Long szorderby, String dutype, Long issellticket, String strdutype,
			String strbypurviewtype, String strbycategorytype, Long picidf,
			String picurlf,Long picids,String picurls) {
		super();
		this.idutyid = idutyid;
		this.irootid = irootid;
		this.iparentid = iparentid;
		this.ilevel = ilevel;
		this.ilevelsequence = ilevelsequence;
		this.bisleaf = bisleaf;
		this.szinnerid = szinnerid;
		this.szinnercode = szinnercode;
		this.szinnername = szinnername;
		this.szdutycode = szdutycode;
		this.szdutyname = szdutyname;
		this.bisshowimage = bisshowimage;
		this.szimagesrc = szimagesrc;
		this.szgotopage = szgotopage;
		this.bypurviewtype = bypurviewtype;
		this.bycategorytype = bycategorytype;
		this.byisuse = byisuse;
		this.szloadresource = szloadresource;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.szorderby = szorderby;
		this.dutype = dutype;
		this.issellticket = issellticket;
		this.strdutype = strdutype;
		this.strbypurviewtype = strbypurviewtype;
		this.strbycategorytype = strbycategorytype;
		this.picidf = picidf;
		this.picurlf = picurlf;
		this.picids = picids;
		this.picurls = picurls;
	}







	/** minimal constructor */
	public Espdutytab(Long idutyid, Long irootid, Long iparentid, Long ilevel,
			Long ilevelsequence, Long bisleaf, String szdutycode, String szdutyname,
			String bisshowimage, String szimagesrc, String szgotopage,
			String bypurviewtype, String bycategorytype, Long byisuse,
			String szloadresource, String szmemo, Long iversion, Long szorderby,
			String dutype, Long issellticket) {
		this.idutyid = idutyid;
		this.irootid = irootid;
		this.iparentid = iparentid;
		this.ilevel = ilevel;
		this.ilevelsequence = ilevelsequence;
		this.bisleaf = bisleaf;
		this.szdutycode = szdutycode;
		this.szdutyname = szdutyname;
		this.bisshowimage = bisshowimage;
		this.szimagesrc = szimagesrc;
		this.szgotopage = szgotopage;
		this.bypurviewtype = bypurviewtype;
		this.bycategorytype = bycategorytype;
		this.byisuse = byisuse;
		this.szloadresource = szloadresource;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.szorderby = szorderby;
		this.dutype = dutype;
		this.issellticket = issellticket;
	}

	/** full constructor */
	public Espdutytab(Long idutyid, Long irootid, Long iparentid, Long ilevel,
			Long ilevelsequence, Long bisleaf, String szinnerid, String szinnercode,
			String szinnername, String szdutycode, String szdutyname,
			String bisshowimage, String szimagesrc, String szgotopage,
			String bypurviewtype, String bycategorytype, Long byisuse,
			String szloadresource, String szmemo, Long iversion, Long szorderby,
			String dutype, Long issellticket) {
		this.idutyid = idutyid;
		this.irootid = irootid;
		this.iparentid = iparentid;
		this.ilevel = ilevel;
		this.ilevelsequence = ilevelsequence;
		this.bisleaf = bisleaf;
		this.szinnerid = szinnerid;
		this.szinnercode = szinnercode;
		this.szinnername = szinnername;
		this.szdutycode = szdutycode;
		this.szdutyname = szdutyname;
		this.bisshowimage = bisshowimage;
		this.szimagesrc = szimagesrc;
		this.szgotopage = szgotopage;
		this.bypurviewtype = bypurviewtype;
		this.bycategorytype = bycategorytype;
		this.byisuse = byisuse;
		this.szloadresource = szloadresource;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.szorderby = szorderby;
		this.dutype = dutype;
		this.issellticket = issellticket;
	}

	// Property accessors

	
	//何安洲新加，2017-05-02，新加属性的get、set方法	

	
	public Long getPicidf() {
		return picidf;
	}

	public void setPicidf(Long picidf) {
		this.picidf = picidf;
	}

	public String getPicurlf() {
		return picurlf;
	}

	public void setPicurlf(String picurlf) {
		this.picurlf = picurlf;
	}

	public Long getPicids() {
		return picids;
	}

	public void setPicids(Long picids) {
		this.picids = picids;
	}

	public String getPicurls() {
		return picurls;
	}

	public void setPicurls(String picurls) {
		this.picurls = picurls;
	}
	
	
	
	
	
	
	
	public Long getIdutyid() {
		return this.idutyid;
	}

	public void setIdutyid(Long idutyid) {
		this.idutyid = idutyid;
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

	public Long getBisleaf() {
		return this.bisleaf;
	}

	public void setBisleaf(Long bisleaf) {
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

	public String getSzdutycode() {
		return this.szdutycode;
	}

	public void setSzdutycode(String szdutycode) {
		this.szdutycode = szdutycode;
	}

	public String getSzdutyname() {
		return this.szdutyname;
	}

	public void setSzdutyname(String szdutyname) {
		this.szdutyname = szdutyname;
	}

	public String getBisshowimage() {
		return this.bisshowimage;
	}

	public void setBisshowimage(String bisshowimage) {
		this.bisshowimage = bisshowimage;
	}

	public String getSzimagesrc() {
		return this.szimagesrc;
	}

	public void setSzimagesrc(String szimagesrc) {
		this.szimagesrc = szimagesrc;
	}

	public String getSzgotopage() {
		return this.szgotopage;
	}

	public void setSzgotopage(String szgotopage) {
		this.szgotopage = szgotopage;
	}

	public String getBypurviewtype() {
		return this.bypurviewtype;
	}

	public void setBypurviewtype(String bypurviewtype) {
		this.bypurviewtype = bypurviewtype;
	}

	public String getBycategorytype() {
		return this.bycategorytype;
	}

	public void setBycategorytype(String bycategorytype) {
		this.bycategorytype = bycategorytype;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getSzloadresource() {
		return this.szloadresource;
	}

	public void setSzloadresource(String szloadresource) {
		this.szloadresource = szloadresource;
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

	public Long getSzorderby() {
		return this.szorderby;
	}

	public void setSzorderby(Long szorderby) {
		this.szorderby = szorderby;
	}

	public String getDutype() {
		return this.dutype;
	}

	public void setDutype(String dutype) {
		this.dutype = dutype;
	}

	public Long getIssellticket() {
		return this.issellticket;
	}

	public void setIssellticket(Long issellticket) {
		this.issellticket = issellticket;
	}
	public String getStrdutype() {
		return strdutype;
	}

	public void setStrdutype(String strdutype) {
		this.strdutype = strdutype;
	}

	public String getStrbypurviewtype() {
		return strbypurviewtype;
	}

	public void setStrbypurviewtype(String strbypurviewtype) {
		this.strbypurviewtype = strbypurviewtype;
	}

	public String getStrbycategorytype() {
		return strbycategorytype;
	}

	public void setStrbycategorytype(String strbycategorytype) {
		this.strbycategorytype = strbycategorytype;
	}

}