package com.ectrip.ticket.service.model;

import java.util.Vector;

/**
 * 网络订单票信息
 * @author LiuJianwen
 *
 */
public class NetworkOrderTicket {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String dtenddate;
	private String icrowdkindid;
	private String yhamnt;
	private String pric;
	private String orderlistid;
	private String numb;//数量
	private String yhnumb;
	private String zdail;
	private String iscenicid;
	private String itickettypeid;
	private String szcrowdkindname;
	private String amnt;
	private String sztickettypename;
	private String orid;
	private String dtstartdate;
	private String icrowdkindpriceid;
	private String zzdail;//子票信息

	private String bymaketicketway;//出票方式“00”现场打印，“01”现场激活
	private String bymediatype;
	private String strmaketype;
	private String strmediatype;

	String barcode="";//当为现场激活时，需要输入条码，4.1，洛阳

	//////////////////////////////////////////
	String numb_;//原始数量，numb修改后的数量
	/////////////////////////////////////////

	Vector<String> tableData = new Vector<String>();//储存在table中的数据
	Vector<String> tableData1 = new Vector<String>();//储存在出票table中的数据
	String childName;//受限子票名
	String childId;//受限子票id
	String tripId;//趟次id
	String tripName;//趟次名
	String tripBeginTime;//趟次开始时间
	String tripEndTime;//趟次结束时间
	String tingPai;//是否停牌，当为1时此趟次才可用


	public NetworkOrderTicket(String dtenddate, String icrowdkindid,
							  String yhamnt, String pric, String orderlistid, String numb,
							  String yhnumb, String zdail, String iscenicid,
							  String itickettypeid, String szcrowdkindname, String amnt,
							  String sztickettypename, String orid, String dtstartdate,
							  String icrowdkindpriceid,String zzdail,
							  String bymaketicketway,
							  String bymediatype,
							  String strmaketype,
							  String strmediatype) {
		super();
		this.numb_ = numb;//备份原始数量
		this.dtenddate = dtenddate;
		this.icrowdkindid = icrowdkindid;
		this.yhamnt = yhamnt;
		this.pric = pric;
		this.orderlistid = orderlistid;
		this.numb = numb;
		this.yhnumb = yhnumb;
		this.zdail = zdail;
		this.iscenicid = iscenicid;
		this.itickettypeid = itickettypeid;
		this.szcrowdkindname = szcrowdkindname;
		this.amnt = amnt;
		this.sztickettypename = sztickettypename;
		this.orid = orid;
		this.dtstartdate = dtstartdate;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.zzdail = zzdail;
		this.bymaketicketway = bymaketicketway;
		this.bymediatype = bymediatype;
		this.strmaketype = strmaketype;
		this.strmediatype = strmediatype;
		if(zdail.length()>0 && !"null".equals(zdail)){
			try {

				String[] zhuFa = zdail.split("&");
				childId = zhuFa[0];
				childName = zhuFa[1];
				tripId = zhuFa[2];
				tripName = zhuFa[3];
				tripBeginTime = zhuFa[4];
				tripEndTime = zhuFa[5];
				tingPai = zhuFa[6];

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public NetworkOrderTicket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vector<String> getTableData() {
		//		dtenddate:2011-11-10★icrowdkindid:1★yhamnt:0★pric:70★orderlistid:3
		//		★numb:1★yhnumb:0★zdail:竹筏票&3&第二趟&2011-11-10 10:00:00&2011-11-10 13:00:00
		//		★iscenicid:1★itickettypeid:16
		//		★szcrowdkindname:成人★amnt:70★sztickettypename:一日游车票
		//		★orid:20111110000000017★dtstartdate:2011-11-10★

		//		"订单号","票种","游客类型","生效日期","失效日期","趟次","价格";
		tableData.clear();
		tableData.add(getOrid());
		tableData.add(getSztickettypename());
		tableData.add(getSzcrowdkindname());
		tableData.add(getDtstartdate());
		tableData.add(getDtenddate());
		tableData.add(getTripName()==null?"/":(getTripName()+"("+tripBeginTime+")"));
		tableData.add(getNumb());
		//		tableData.add(getAmnt());
		return tableData;
	}

	public Vector<String> getTableData1() {
		tableData1.clear();
		tableData1.add(getSztickettypename());
		tableData1.add(getSzcrowdkindname());
		tableData1.add(getNumb());
		return tableData1;
	}


	public String getDtenddate() {
		return dtenddate;
	}
	public void setDtenddate(String dtenddate) {
		this.dtenddate = dtenddate;
	}
	public String getIcrowdkindid() {
		return icrowdkindid;
	}
	public void setIcrowdkindid(String icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}
	public String getYhamnt() {
		return yhamnt;
	}
	public void setYhamnt(String yhamnt) {
		this.yhamnt = yhamnt;
	}
	public String getPric() {
		return pric;
	}
	public void setPric(String pric) {
		this.pric = pric;
	}
	public String getOrderlistid() {
		return orderlistid;
	}
	public void setOrderlistid(String orderlistid) {
		this.orderlistid = orderlistid;
	}
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	public String getYhnumb() {
		return yhnumb;
	}
	public void setYhnumb(String yhnumb) {
		this.yhnumb = yhnumb;
	}
	public String getZdail() {
		return zdail;
	}
	public void setZdail(String zdail) {
		this.zdail = zdail;
	}
	public String getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}
	public String getItickettypeid() {
		return itickettypeid;
	}
	public void setItickettypeid(String itickettypeid) {
		this.itickettypeid = itickettypeid;
	}
	public String getSzcrowdkindname() {
		return szcrowdkindname;
	}
	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}
	public String getAmnt() {
		return amnt;
	}
	public void setAmnt(String amnt) {
		this.amnt = amnt;
	}
	public String getSztickettypename() {
		return sztickettypename;
	}
	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}
	public String getOrid() {
		return orid;
	}
	public void setOrid(String orid) {
		this.orid = orid;
	}
	public String getDtstartdate() {
		return dtstartdate;
	}
	public void setDtstartdate(String dtstartdate) {
		this.dtstartdate = dtstartdate;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public String getTripBeginTime() {
		return tripBeginTime;
	}
	public void setTripBeginTime(String tripBeginTime) {
		this.tripBeginTime = tripBeginTime;
	}
	public String getTripEndTime() {
		return tripEndTime;
	}
	public void setTripEndTime(String tripEndTime) {
		this.tripEndTime = tripEndTime;
	}
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public String getTingPai() {
		return tingPai;
	}
	public void setTingPai(String tingPai) {
		this.tingPai = tingPai;
	}
	public String getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}
	public void setIcrowdkindpriceid(String icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public String getZzdail() {
		return zzdail;
	}
	public void setZzdail(String zzdail) {
		this.zzdail = zzdail;
	}
	public String getNumb_() {
		return numb_;
	}
	public void setNumb_(String numb_) {
		this.numb_ = numb_;
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
	public String getStrmaketype() {
		return strmaketype;
	}
	public void setStrmaketype(String strmaketype) {
		this.strmaketype = strmaketype;
	}
	public String getStrmediatype() {
		return strmediatype;
	}
	public void setStrmediatype(String strmediatype) {
		this.strmediatype = strmediatype;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


}

