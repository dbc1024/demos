package com.ectrip.ticket.service.model;



public class Ticket{

	private static final long serialVersionUID = 1L;
	private String itickettypeid;//产品id
	private String iscenicid ;//服务商id(关联服务商表)
	private String iticketnoruleid;//票号规则id(关联票号规则表）
	private String sztickettypecode;//产品代码
	private String sztickettypename;//产品名称
	private String bycategorytype;//产品种类
	private String byusage;//使用人群（0 -- 常规票, 1-- 员工卡,2 -- 居民卡,3 -- 充值票,4 -- 结算卡,5 -- 贵宾票,6 -- 贵宾票+ 结算）
	private String byuselimit;//使用限制（0一次性，1长期性）
	private String bymaketicketway;//出票方式（00现场激活,01现场打印，02打印激活03不做打印）
	private String bymediatype;//介质类型（00条码，01感应卡RI型，02感应卡RWI型，03感应卡RWII型）
	private String szticketprintcode;//印刷代码
	private String mcostprice;//采购价
	private String mnominalfee;//工本费
	private String byisuse;//状态
	private String szmemo;//备注
	private String bispersontimestat;//是否参与入园统计
	private String validityday;//有效天数
	private String issale;//銷售是否受限制
	private String iscansale;//銷售受限产品无受限数据是否直接销售
	private String iscontrol;//數量是否受限制
	private String iscontrolsale;//數量受限产品无受限数据是否直接销售
	private String isequence;//排序
	private String szscenicname;//服务商名称

	////////////////////////////////////////////////////////////
	String tourDate;//游览日期(基础子票，如竹筏票)
	String tripid = "0";//如果子票是基础竹筏票，则包含趟次

//	JLabel tourDateLabel;
//	JTextField tourDateField;//受限子票游览日期对应的JTEXTFIELD
//	JLabel tripiLabel;
//	JComboBox tripidCombBox;//受限子票对应的趟次jComboBox;
	/*******************************************************************/

	String count = "1";//购买数量，保存ORDERS时用到

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ticket(String itickettypeid, String iscenicid,
				  String iticketnoruleid, String sztickettypecode,
				  String sztickettypename, String bycategorytype, String byusage,
				  String byuselimit, String bymaketicketway, String bymediatype,
				  String szticketprintcode, String mcostprice, String mnominalfee,
				  String byisuse, String szmemo, String bispersontimestat,
				  String validityday, String issale, String iscansale,
				  String iscontrol, String iscontrolsale, String isequence,
				  String szscenicname) {
		super();
		this.itickettypeid = itickettypeid;
		this.iscenicid = iscenicid;
		this.iticketnoruleid = iticketnoruleid;
		this.sztickettypecode = sztickettypecode;
		this.sztickettypename = sztickettypename;
		this.bycategorytype = bycategorytype;
		this.byusage = byusage;
		this.byuselimit = byuselimit;
		this.bymaketicketway = bymaketicketway;
		this.bymediatype = bymediatype;
		this.szticketprintcode = szticketprintcode;
		this.mcostprice = mcostprice;
		this.mnominalfee = mnominalfee;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.bispersontimestat = bispersontimestat;
		this.validityday = validityday;
		this.issale = issale;
		this.iscansale = iscansale;
		this.iscontrol = iscontrol;
		this.iscontrolsale = iscontrolsale;
		this.isequence = isequence;
		this.szscenicname = szscenicname;
	}
	public String getItickettypeid() {
		return itickettypeid;
	}
	public void setItickettypeid(String itickettypeid) {
		this.itickettypeid = itickettypeid;
	}
	public String getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}
	public String getIticketnoruleid() {
		return iticketnoruleid;
	}
	public void setIticketnoruleid(String iticketnoruleid) {
		this.iticketnoruleid = iticketnoruleid;
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
	public String getBycategorytype() {
		return bycategorytype;
	}
	public void setBycategorytype(String bycategorytype) {
		this.bycategorytype = bycategorytype;
	}
	public String getByusage() {
		return byusage;
	}
	public void setByusage(String byusage) {
		this.byusage = byusage;
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
	public String getBymediatype() {
		return bymediatype;
	}
	public void setBymediatype(String bymediatype) {
		this.bymediatype = bymediatype;
	}
	public String getSzticketprintcode() {
		return szticketprintcode;
	}
	public void setSzticketprintcode(String szticketprintcode) {
		this.szticketprintcode = szticketprintcode;
	}
	public String getMcostprice() {
		return mcostprice;
	}
	public void setMcostprice(String mcostprice) {
		this.mcostprice = mcostprice;
	}
	public String getMnominalfee() {
		return mnominalfee;
	}
	public void setMnominalfee(String mnominalfee) {
		this.mnominalfee = mnominalfee;
	}
	public String getByisuse() {
		return byisuse;
	}
	public void setByisuse(String byisuse) {
		this.byisuse = byisuse;
	}
	public String getSzmemo() {
		return szmemo;
	}
	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}
	public String getBispersontimestat() {
		return bispersontimestat;
	}
	public void setBispersontimestat(String bispersontimestat) {
		this.bispersontimestat = bispersontimestat;
	}
	public String getValidityday() {
		return validityday;
	}
	public void setValidityday(String validityday) {
		this.validityday = validityday;
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
	public String getIsequence() {
		return isequence;
	}
	public void setIsequence(String isequence) {
		this.isequence = isequence;
	}
	public String getSzscenicname() {
		return szscenicname;
	}
	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}
	public String getTourDate() {
		return tourDate;
	}
	public void setTourDate(String tourDate) {
		this.tourDate = tourDate;
	}
	public String getTripid() {
		return tripid;
	}
	public void setTripid(String tripid) {
		this.tripid = tripid;
	}

	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	////////////////////////////////////////////////////////////
	//	String tourDate;//游览日期(基础子票，如竹筏票)
	//	String tripid;//如果子票是基础竹筏票，则包含趟次
	//
	//	JTextField tourDateField;//受限子票游览日期对应的JTEXTFIELD
	//	JComboBox tripidCombBox;//受限子票对应的趟次jComboBox;
	/*******************************************************************/
	public Ticket clone(){
		return new Ticket( itickettypeid,  iscenicid,
				iticketnoruleid,  sztickettypecode,
				sztickettypename,  bycategorytype,  byusage,
				byuselimit,  bymaketicketway,  bymediatype,
				szticketprintcode,  mcostprice,  mnominalfee,
				byisuse,  szmemo,  bispersontimestat,
				validityday,  issale,  iscansale,
				iscontrol,  iscontrolsale,  isequence,
				szscenicname);
	}


}

