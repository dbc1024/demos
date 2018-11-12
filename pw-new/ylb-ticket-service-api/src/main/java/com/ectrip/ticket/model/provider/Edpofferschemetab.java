package com.ectrip.ticket.model.provider;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 优惠方案
 *
 * @author Jzhenhua <br>
 *         Date 2011-10-24
 */
@Table
@Entity
public class Edpofferschemetab implements java.io.Serializable {

	// Fields
	@Id
	private Long ioffersschemeid; // 优惠方案ID
	private Long iscenicid; // 服务商ID
	private Long ibusinessid; // 业务ID
	private String szoffersschemecode; // 优惠方案代码
	private String szoffersschemename; // 优惠方案名称
	private Long bycategorytype; // 优惠方式,详情系统参数OSTP
	private String enddata; // 结束日期
	private Long offersschemetype; //
	private String startdata; // 开始日期
	private Long imultiples; // 优惠基数
	private Long ioffernum; // 优惠数量
	private Long ioffertype; // 优惠对象方式,详情系统参数OOTP
	private Long itickettypeid; // 优惠产品ID
	private Long icrowdkindid; // 优惠人群种族ID
	private Long byisuse; // 使用状态
	private String szmemo; // 备注

	// no database fields
	@Transient
	private String striscenicid; // 服务商名
	@Transient
	private String stribusinessid; // 业务名
	@Transient
	private String stritickettypeid;// 产品名称
	@Transient
	private String stricrowdkindid;// 人群种族名称
	@Transient
	private String strbycategorytype;
	@Transient
	private String strioffertype;

	// Constructors

	/** default constructor */
	public Edpofferschemetab() {
	}

	/** minimal constructor */
	public Edpofferschemetab(Long ioffersschemeid, Long iscenicid,
							 Long ibusinessid, String szoffersschemecode,
							 String szoffersschemename, Long bycategorytype, Long imultiples,
							 Long ioffernum, Long ioffertype, Long byisuse, String szmemo) {
		this.ioffersschemeid = ioffersschemeid;
		this.iscenicid = iscenicid;
		this.ibusinessid = ibusinessid;
		this.szoffersschemecode = szoffersschemecode;
		this.szoffersschemename = szoffersschemename;
		this.bycategorytype = bycategorytype;
		this.imultiples = imultiples;
		this.ioffernum = ioffernum;
		this.ioffertype = ioffertype;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
	}

	/** full constructor */
	public Edpofferschemetab(Long ioffersschemeid, Long iscenicid,
							 Long ibusinessid, String szoffersschemecode,
							 String szoffersschemename, Long bycategorytype, String enddata,
							 Long offersschemetype, String startdata, Long imultiples,
							 Long ioffernum, Long ioffertype, Long itickettypeid,
							 Long icrowdkindid, Long byisuse, String szmemo) {
		this.ioffersschemeid = ioffersschemeid;
		this.iscenicid = iscenicid;
		this.ibusinessid = ibusinessid;
		this.szoffersschemecode = szoffersschemecode;
		this.szoffersschemename = szoffersschemename;
		this.bycategorytype = bycategorytype;
		this.enddata = enddata;
		this.offersschemetype = offersschemetype;
		this.startdata = startdata;
		this.imultiples = imultiples;
		this.ioffernum = ioffernum;
		this.ioffertype = ioffertype;
		this.itickettypeid = itickettypeid;
		this.icrowdkindid = icrowdkindid;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
	}

	// Property accessors

	public Long getIoffersschemeid() {
		return this.ioffersschemeid;
	}

	public void setIoffersschemeid(Long ioffersschemeid) {
		this.ioffersschemeid = ioffersschemeid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIbusinessid() {
		return this.ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public String getSzoffersschemecode() {
		return this.szoffersschemecode;
	}

	public void setSzoffersschemecode(String szoffersschemecode) {
		this.szoffersschemecode = szoffersschemecode;
	}

	public String getSzoffersschemename() {
		return this.szoffersschemename;
	}

	public void setSzoffersschemename(String szoffersschemename) {
		this.szoffersschemename = szoffersschemename;
	}

	public Long getBycategorytype() {
		return this.bycategorytype;
	}

	public void setBycategorytype(Long bycategorytype) {
		this.bycategorytype = bycategorytype;
	}

	public String getEnddata() {
		return this.enddata;
	}

	public void setEnddata(String enddata) {
		this.enddata = enddata;
	}

	public Long getOffersschemetype() {
		return this.offersschemetype;
	}

	public void setOffersschemetype(Long offersschemetype) {
		this.offersschemetype = offersschemetype;
	}

	public String getStartdata() {
		return this.startdata;
	}

	public void setStartdata(String startdata) {
		this.startdata = startdata;
	}

	public Long getImultiples() {
		return this.imultiples;
	}

	public void setImultiples(Long imultiples) {
		this.imultiples = imultiples;
	}

	public Long getIoffernum() {
		return this.ioffernum;
	}

	public void setIoffernum(Long ioffernum) {
		this.ioffernum = ioffernum;
	}

	public Long getIoffertype() {
		return this.ioffertype;
	}

	public void setIoffertype(Long ioffertype) {
		this.ioffertype = ioffertype;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIcrowdkindid() {
		return this.icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
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

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public String getStribusinessid() {
		return stribusinessid;
	}

	public void setStribusinessid(String stribusinessid) {
		this.stribusinessid = stribusinessid;
	}

	public String getStritickettypeid() {
		return stritickettypeid;
	}

	public void setStritickettypeid(String stritickettypeid) {
		this.stritickettypeid = stritickettypeid;
	}

	public String getStricrowdkindid() {
		return stricrowdkindid;
	}

	public void setStricrowdkindid(String stricrowdkindid) {
		this.stricrowdkindid = stricrowdkindid;
	}

	public String getStrbycategorytype() {
		return strbycategorytype;
	}

	public void setStrbycategorytype(String strbycategorytype) {
		this.strbycategorytype = strbycategorytype;
	}

	public String getStrioffertype() {
		return strioffertype;
	}

	public void setStrioffertype(String strioffertype) {
		this.strioffertype = strioffertype;
	}

}