package com.ectrip.ticket.service.model;




public class TicketPrice {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String icrowdkindpriceid;// 售价id
	private String ipeoplenumrange;// 基数
	private String mactualsaleprice;// 实际售价
	private String itickettypeid;//产品ID
	private String sztickettypecode;//产品代码
	private String sztickettypename;//产品名称
	private String issale;//銷售是否受限制
	private String iscansale;//銷售受限产品无受限数据是否直接销售
	private String iscontrol;//數量是否受限制
	private String iscontrolsale;//數量受限产品无受限数据是否直接销售
	private String validityday;//有效天数
	private String byuselimit;//使用限制（0一次性，1长期性）
	private String bymaketicketway;//出票方式（00现场打印，01现场激活,02打印激活,03不做打印）
	private String  strmaketype;//出票方式名称，介质类型
	private String bycategorytype;//产品种类编号(0010 套票)
	private String strcategory;//产品种类名称
	private String icrowdkindid;//人群种类Id
	private String szcrowdkindcode;//人类种群代码
	private String szcrowdkindname;//人类种群名称
	private String ibusinessid;//业务类型id
	private String szbusinesscode;//业务类型代码
	private String szbusinessname;//业务类型名称
	private String products;//当bycategorytype='0010' 时 该字段存的是套票里面的子票 以:隔开
	private String icrowdkindpricecode;//快捷鍵編號
	private String bymediatype;//介质类型（00条码，01感应卡RI型，02感应卡RWI型，03感应卡RWII型）
	private String strmediatype;//介质类型名称


	public String getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}
	public void setIcrowdkindpriceid(String icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}
	public String getIpeoplenumrange() {
		return ipeoplenumrange;
	}
	public void setIpeoplenumrange(String ipeoplenumrange) {
		this.ipeoplenumrange = ipeoplenumrange;
	}
	public String getMactualsaleprice() {
		return mactualsaleprice;
	}
	public void setMactualsaleprice(String mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}
	public String getItickettypeid() {
		return itickettypeid;
	}
	public void setItickettypeid(String itickettypeid) {
		this.itickettypeid = itickettypeid;
	}
	public String getSztickettypecode() {
		return sztickettypecode;
	}
	public void setSztickettypecode(String sztickettypecode) {
		this.sztickettypecode = sztickettypecode;
	}
	public String getSztickettypename() {
		return sztickettypename;
	}
	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}
	public String getIssale() {
		return issale;
	}
	public void setIssale(String issale) {
		this.issale = issale;
	}
	public String getIscansale() {
		return iscansale;
	}
	public void setIscansale(String iscansale) {
		this.iscansale = iscansale;
	}
	public String getIscontrol() {
		return iscontrol;
	}
	public void setIscontrol(String iscontrol) {
		this.iscontrol = iscontrol;
	}
	public String getIscontrolsale() {
		return iscontrolsale;
	}
	public void setIscontrolsale(String iscontrolsale) {
		this.iscontrolsale = iscontrolsale;
	}
	public String getValidityday() {
		return validityday;
	}
	public void setValidityday(String validityday) {
		this.validityday = validityday;
	}
	public String getByuselimit() {
		return byuselimit;
	}
	public void setByuselimit(String byuselimit) {
		this.byuselimit = byuselimit;
	}
	public String getBymaketicketway() {
		return bymaketicketway;
	}
	public void setBymaketicketway(String bymaketicketway) {
		this.bymaketicketway = bymaketicketway;
	}
	public String getStrmaketype() {
		return strmaketype;
	}
	public void setStrmaketype(String strmaketype) {
		this.strmaketype = strmaketype;
	}
	public String getBycategorytype() {
		return bycategorytype;
	}
	public void setBycategorytype(String bycategorytype) {
		this.bycategorytype = bycategorytype;
	}
	public String getStrcategory() {
		return strcategory;
	}
	public void setStrcategory(String strcategory) {
		this.strcategory = strcategory;
	}
	public String getIcrowdkindid() {
		return icrowdkindid;
	}
	public void setIcrowdkindid(String icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}
	public String getSzcrowdkindcode() {
		return szcrowdkindcode;
	}
	public void setSzcrowdkindcode(String szcrowdkindcode) {
		this.szcrowdkindcode = szcrowdkindcode;
	}
	public String getSzcrowdkindname() {
		return szcrowdkindname;
	}
	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}
	public String getIbusinessid() {
		return ibusinessid;
	}
	public void setIbusinessid(String ibusinessid) {
		this.ibusinessid = ibusinessid;
	}
	public String getSzbusinesscode() {
		return szbusinesscode;
	}
	public void setSzbusinesscode(String szbusinesscode) {
		this.szbusinesscode = szbusinesscode;
	}
	public String getSzbusinessname() {
		return szbusinessname;
	}
	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public String getIcrowdkindpricecode() {
		return icrowdkindpricecode;
	}
	public void setIcrowdkindpricecode(String icrowdkindpricecode) {
		this.icrowdkindpricecode = icrowdkindpricecode;
	}
	public TicketPrice() {
		super();
	}

	public String getBymediatype() {
		return bymediatype;
	}
	public void setBymediatype(String bymediatype) {
		this.bymediatype = bymediatype;
	}
	public String getStrmediatype() {
		return strmediatype;
	}
	public void setStrmediatype(String strmediatype) {
		this.strmediatype = strmediatype;
	}
	public TicketPrice(String icrowdkindpriceid,
					   String ipeoplenumrange, String mactualsaleprice,
					   String itickettypeid, String sztickettypecode,
					   String sztickettypename, String issale, String iscansale,
					   String iscontrol, String iscontrolsale, String validityday,
					   String byuselimit, String bymaketicketway, String strmaketype,
					   String bycategorytype, String strcategory, String icrowdkindid,
					   String szcrowdkindcode, String szcrowdkindname, String ibusinessid,
					   String szbusinesscode, String szbusinessname, String products,
					   String icrowdkindpricecode,String bymediatype,//介质类型（00条码，01感应卡RI型，02感应卡RWI型，03感应卡RWII型）
					   String strmediatype) {
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.ipeoplenumrange = ipeoplenumrange;
		this.mactualsaleprice = mactualsaleprice;
		this.itickettypeid = itickettypeid;
		this.sztickettypecode = sztickettypecode;
		this.sztickettypename = sztickettypename;
		this.issale = issale;
		this.iscansale = iscansale;
		this.iscontrol = iscontrol;
		this.iscontrolsale = iscontrolsale;
		this.validityday = validityday;
		this.byuselimit = byuselimit;
		this.bymaketicketway = bymaketicketway;
		this.strmaketype = strmaketype;
		this.bycategorytype = bycategorytype;
		this.strcategory = strcategory;
		this.icrowdkindid = icrowdkindid;
		this.szcrowdkindcode = szcrowdkindcode;
		this.szcrowdkindname = szcrowdkindname;
		this.ibusinessid = ibusinessid;
		this.szbusinesscode = szbusinesscode;
		this.szbusinessname = szbusinessname;
		this.products = products;
		this.icrowdkindpricecode = icrowdkindpricecode;
		this.bymediatype = bymediatype;
		this.strmediatype = strmediatype;
	}



}

