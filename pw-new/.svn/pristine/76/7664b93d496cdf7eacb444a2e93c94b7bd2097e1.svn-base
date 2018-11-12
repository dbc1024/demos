package com.ectrip.ticket.model.provider;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Edmcrowdkindpricetab entity. @author MyEclipse Persistence Tools
 * 售价表
 */
@Table(name="EDMCROWDKINDPRICETAB")
@Entity
public class Edmcrowdkindpricetab implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long icrowdkindpriceid;// 售价id
	private String icrowdkindpricecode; //售价编号
	private Long ipricecategoryid;// 售价类别
	private String startdata;// 价格开始日期
	private String enddata;// 价格结束日期
	private Long ipeoplenumrange;// 是否实名制
	private Double listingprice;// 挂牌价
	private Double weekendprice;// 周未价
	private Double mactualsaleprice;// 实际售价
	private Double jsprice;//结算价格
	private Long isequence;// 排序
	private Long byisuse;// 状态（0禁用，1启用）
	private String szmemo;// 备注
	private  Long icrowdkindid;//人群种类Id（关联人群种类 Edpcrowdkindtab）
	private  Long ibusinessid;//业务类型id （关联业务类型 Edmbusinesstab）
	private  Long itickettypeid;//产品id （关联产品表 Edmtickettypetab）
	private Long isnet;//是否可网上销售
	private Long isscene;//现场销售
	private Long isscancode=0L;//是否扫码购票 0否，1是
	private String note1;//价格分组
	private String note2;
	private String note3;//是否旅游卡购票,0否，1是
	private String note4;
	private Long inote1;  //是否售票登记指纹
	private Long inote2;//最大有效期是否以价格有效期为准
	private Long inote3; //旅游卡购票
	private Long inote4;  //畅游通用  与OTO对应的价格ID

	// 数据库不存在的字段
	@Transient
	private String strcrowdkind;// 人群种类名称
	@Transient
	private String strbusiness;// 业务类型名称
	@Transient
	private String strpricecategory;// 售价类型名称
	@Transient
	private String strpricename;// 价格组合名称
	@Transient
	private String scenictype;//服务商类别
	@Transient
	private String strtickettype;//产品名称
	@Transient
	private String crowdkindmemo;//人群种类说明
	@Transient
	private String sztickettypename;//消费类型名称
	@Transient
	private String szcrowdkindname;//人群种类
	@Transient
	private String szbusinessname;//业务类型


	public String getStrtickettype() {
		return strtickettype;
	}

	public void setStrtickettype(String strtickettype) {
		this.strtickettype = strtickettype;
	}

	public String getScenictype() {
		return scenictype;
	}

	public void setScenictype(String scenictype) {
		this.scenictype = scenictype;
	}

	/** default constructor */
	public Edmcrowdkindpricetab() {
	}

	/** minimal constructor */
	public Edmcrowdkindpricetab(Long icrowdkindpriceid,String icrowdkindpricecode,
								Long icrowdkindid,Long ibusinessid,Long itickettypeid ,String startdata,
								String enddata, Long ipeoplenumrange, Double listingprice,Long isscancode,
								Double mactualsaleprice,Double jsprice, Long isequence, Long byisuse,Long isnet,Long isscene,
								String szmemo) {
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.icrowdkindpricecode=icrowdkindpricecode;
		this.icrowdkindid = icrowdkindid;
		this.ibusinessid = ibusinessid;
		this.itickettypeid = itickettypeid;
		this.startdata = startdata;
		this.enddata = enddata;
		this.ipeoplenumrange = ipeoplenumrange;
		this.listingprice = listingprice;
		this.mactualsaleprice = mactualsaleprice;
		this.jsprice = jsprice;
		this.isequence = isequence;
		this.byisuse = byisuse;
		this.isnet = isnet;
		this.isscene = isscene;
		this.isscancode=isscancode;
		this.szmemo = szmemo;
	}

	/** full constructor */
	public Edmcrowdkindpricetab(Long icrowdkindpriceid,String icrowdkindpricecode,
								Long icrowdkindid,Long ibusinessid,Long itickettypeid , Long ipricecategoryid,
								String startdata, String enddata, Long ipeoplenumrange,Long isscancode,
								Double listingprice, Double weekendprice, Double mactualsaleprice,Double jsprice,
								Long isequence, Long byisuse,Long isnet,Long isscene, String szmemo, String note1,
								String note2, String note3,String note4, Long inote1,
								Long inote2, Long inote3, Long inote4) {
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.icrowdkindpricecode=icrowdkindpricecode;
		this.icrowdkindid = icrowdkindid;
		this.ibusinessid = ibusinessid;
		this.itickettypeid = itickettypeid;
		this.ipricecategoryid = ipricecategoryid;
		this.startdata = startdata;
		this.enddata = enddata;
		this.ipeoplenumrange = ipeoplenumrange;
		this.listingprice = listingprice;
		this.weekendprice = weekendprice;
		this.mactualsaleprice = mactualsaleprice;
		this.jsprice = jsprice;
		this.isequence = isequence;
		this.byisuse = byisuse;
		this.isnet = isnet;
		this.isscene = isscene;
		this.isscancode=isscancode;
		this.szmemo = szmemo;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.inote1 = inote1;
		this.inote2 = inote2;
		this.inote3 = inote3;
		this.inote4 = inote4;
	}

	// Property accessors

	public Long getIcrowdkindpriceid() {
		return this.icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public Long getIcrowdkindid() {
		return icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public Long getIbusinessid() {
		return ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public Long getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIpricecategoryid() {
		return this.ipricecategoryid;
	}

	public void setIpricecategoryid(Long ipricecategoryid) {
		this.ipricecategoryid = ipricecategoryid;
	}

	public String getStartdata() {
		return this.startdata;
	}

	public void setStartdata(String startdata) {
		this.startdata = startdata;
	}

	public String getEnddata() {
		return this.enddata;
	}

	public void setEnddata(String enddata) {
		this.enddata = enddata;
	}

	public Long getIpeoplenumrange() {
		return this.ipeoplenumrange;
	}

	public void setIpeoplenumrange(Long ipeoplenumrange) {
		this.ipeoplenumrange = ipeoplenumrange;
	}

	public Double getListingprice() {
		return this.listingprice;
	}

	public void setListingprice(Double listingprice) {
		this.listingprice = listingprice;
	}

	public Double getWeekendprice() {
		return this.weekendprice;
	}

	public void setWeekendprice(Double weekendprice) {
		this.weekendprice = weekendprice;
	}

	public Double getMactualsaleprice() {
		return this.mactualsaleprice;
	}

	public void setMactualsaleprice(Double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}

	public Double getJsprice() {
		return jsprice;
	}

	public void setJsprice(Double jsprice) {
		this.jsprice = jsprice;
	}

	public Long getIsequence() {
		return this.isequence;
	}

	public void setIsequence(Long isequence) {
		this.isequence = isequence;
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

	public String getStrcrowdkind() {
		return strcrowdkind;
	}

	public void setStrcrowdkind(String strcrowdkind) {
		this.strcrowdkind = strcrowdkind;
	}

	public String getStrbusiness() {
		return strbusiness;
	}

	public void setStrbusiness(String strbusiness) {
		this.strbusiness = strbusiness;
	}

	public String getStrpricecategory() {
		return strpricecategory;
	}

	public void setStrpricecategory(String strpricecategory) {
		this.strpricecategory = strpricecategory;
	}

	public String getStrpricename() {
		return strpricename;
	}

	public void setStrpricename(String strpricename) {
		this.strpricename = strpricename;
	}

	public String getIcrowdkindpricecode() {
		return icrowdkindpricecode;
	}

	public void setIcrowdkindpricecode(String icrowdkindpricecode) {
		this.icrowdkindpricecode = icrowdkindpricecode;
	}

	public Long getIsnet() {
		return isnet;
	}

	public void setIsnet(Long isnet) {
		this.isnet = isnet;
	}

	public Long getIsscene() {
		return isscene;
	}

	public void setIsscene(Long isscene) {
		this.isscene = isscene;
	}

	public String getCrowdkindmemo() {
		return crowdkindmemo;
	}

	public void setCrowdkindmemo(String crowdkindmemo) {
		this.crowdkindmemo = crowdkindmemo;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public Long getInote1() {
		return inote1;
	}

	public void setInote1(Long inote1) {
		this.inote1 = inote1;
	}

	public Long getInote2() {
		return inote2;
	}

	public void setInote2(Long inote2) {
		this.inote2 = inote2;
	}

	public Long getInote3() {
		return inote3;
	}

	public void setInote3(Long inote3) {
		this.inote3 = inote3;
	}

	public Long getInote4() {
		return inote4;
	}

	public void setInote4(Long inote4) {
		this.inote4 = inote4;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public String getSzcrowdkindname() {
		return szcrowdkindname;
	}

	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}

	public String getSzbusinessname() {
		return szbusinessname;
	}

	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}

	public Long getIsscancode() {
		return isscancode;
	}

	public void setIsscancode(Long isscancode) {
		this.isscancode = isscancode;
	}


}