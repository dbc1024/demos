package com.ectrip.ec.model.order;

import java.util.List;

/**
 * 购物车表
 * Shopcart entity. @author MyEclipse Persistence Tools
 */

public class Shopcart implements java.io.Serializable {

	// Fields

	private Long seq;//主键
	private String usid;//用户名
	private String cookeid;//cookieid
	private Long iscenicid;//服务商编号
	private String scenictype;//服务商类型
	private Long itickettypeid;//产品编号
	private Long icrowdkindpriceid;//价格编号
	private String rzti;//消费开始时间
	private String ldti;//消费结束时间
	private Long numb;//购票数
	private Long raftno;//趟次，场次编号
	private Long seatid;//坐位编号
	private String shpdate;//购物时间
	private Long rnumb;//就餐人数备注
	private String cdate;//就餐时间备注
	private Integer isa;//备用字段
	private Integer isb;
	private Long lga;
	private Long lgb;
	private Double dua;
	private Double dub;
	private String notea;//竹筏时间
	private String noteb;
	private String notec;
	private String noted;
	
	
	//非数据库字段
	private String szscenicname;//服务商名称
	private String sztickettypename;//产品名称
	private double mactualsaleprice;//实际价
	private double weekendprice;//周未价
//	private List<Edmcrowdkindpricetab> pricelist;
	private String pricelist;
	// Constructors

	/** default constructor */
	public Shopcart() {
	}

	/** minimal constructor */
	public Shopcart(Long seq, Long iscenicid, String scenictype,
			Long itickettypeid, Long icrowdkindpriceid,
			String rzti, String ldti, Long numb, String shpdate) {
		this.seq = seq;
		this.iscenicid = iscenicid;
		this.scenictype = scenictype;
		this.itickettypeid = itickettypeid;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.rzti = rzti;
		this.ldti = ldti;
		this.numb = numb;
		this.shpdate = shpdate;
	}

	/** full constructor */
	public Shopcart(Long seq, String usid, String cookeid,
			Long iscenicid, String scenictype, Long itickettypeid,
			Long icrowdkindpriceid, String rzti, String ldti,
			Long numb, Long raftno, Long seatid,
			String shpdate, Long rnumb, String cdate, Integer isa,
			Integer isb, Long lga, Long lgb, Double dua,
			Double dub, String notea, String noteb, String notec, String noted) {
		this.seq = seq;
		this.usid = usid;
		this.cookeid = cookeid;
		this.iscenicid = iscenicid;
		this.scenictype = scenictype;
		this.itickettypeid = itickettypeid;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.rzti = rzti;
		this.ldti = ldti;
		this.numb = numb;
		this.raftno = raftno;
		this.seatid = seatid;
		this.shpdate = shpdate;
		this.rnumb = rnumb;
		this.cdate = cdate;
		this.isa = isa;
		this.isb = isb;
		this.lga = lga;
		this.lgb = lgb;
		this.dua = dua;
		this.dub = dub;
		this.notea = notea;
		this.noteb = noteb;
		this.notec = notec;
		this.noted = noted;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getCookeid() {
		return this.cookeid;
	}

	public void setCookeid(String cookeid) {
		this.cookeid = cookeid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getScenictype() {
		return this.scenictype;
	}

	public void setScenictype(String scenictype) {
		this.scenictype = scenictype;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIcrowdkindpriceid() {
		return this.icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public String getRzti() {
		return this.rzti;
	}

	public void setRzti(String rzti) {
		this.rzti = rzti;
	}

	public String getLdti() {
		return this.ldti;
	}

	public void setLdti(String ldti) {
		this.ldti = ldti;
	}

	public Long getNumb() {
		return this.numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public Long getRaftno() {
		return this.raftno;
	}

	public void setRaftno(Long raftno) {
		this.raftno = raftno;
	}

	public Long getSeatid() {
		return this.seatid;
	}

	public void setSeatid(Long seatid) {
		this.seatid = seatid;
	}

	public String getShpdate() {
		return this.shpdate;
	}

	public void setShpdate(String shpdate) {
		this.shpdate = shpdate;
	}

	public Long getRnumb() {
		return this.rnumb;
	}

	public void setRnumb(Long rnumb) {
		this.rnumb = rnumb;
	}

	public String getCdate() {
		return this.cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public Integer getIsa() {
		return this.isa;
	}

	public void setIsa(Integer isa) {
		this.isa = isa;
	}

	public Integer getIsb() {
		return this.isb;
	}

	public void setIsb(Integer isb) {
		this.isb = isb;
	}

	public Long getLga() {
		return this.lga;
	}

	public void setLga(Long lga) {
		this.lga = lga;
	}

	public Long getLgb() {
		return this.lgb;
	}

	public void setLgb(Long lgb) {
		this.lgb = lgb;
	}

	public Double getDua() {
		return this.dua;
	}

	public void setDua(Double dua) {
		this.dua = dua;
	}

	public Double getDub() {
		return this.dub;
	}

	public void setDub(Double dub) {
		this.dub = dub;
	}

	public String getNotea() {
		return this.notea;
	}

	public void setNotea(String notea) {
		this.notea = notea;
	}

	public String getNoteb() {
		return this.noteb;
	}

	public void setNoteb(String noteb) {
		this.noteb = noteb;
	}

	public String getNotec() {
		return this.notec;
	}

	public void setNotec(String notec) {
		this.notec = notec;
	}

	public String getNoted() {
		return this.noted;
	}

	public void setNoted(String noted) {
		this.noted = noted;
	}

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public double getMactualsaleprice() {
		return mactualsaleprice;
	}

	public void setMactualsaleprice(double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}

	public double getWeekendprice() {
		return weekendprice;
	}

	public void setWeekendprice(double weekendprice) {
		this.weekendprice = weekendprice;
	}

	public String getPricelist() {
		return pricelist;
	}

	public void setPricelist(String pricelist) {
		this.pricelist = pricelist;
	}
}