package com.ectrip.ec.model.order;

/**
 * 
* @ClassName: QueryOrder 
* @Description: 我的订单-查询  参数
* @author Dicky
* @date 2011-10-27 上午09:26:31 
*
 */
public class QueryOrder {
    public String dateQuery;//日期查询方式
    public String startDate;//开始时间
    public String endDate;//结束时间
    public String orderPayState;//订单支付状态
    public String childCustom;//子用户
    public String itickettypeid;
    private Long iscenicid;//服务商编号
    private String orid;//订单号
    private String xrid;//退订单号
    private String ornm;//领票人姓名
	private String orhm;//领票人证件号码
private String sztravelbillno;//行程单号
private String usid; //游客编号id
private String payorid;//支付订单号
private String ddzt;//订单状态
private String strornm;//联系人
private String zffs;//支付方式
private String bank;//支付银行
private String dyusid;//导游id
private Long tripid;//趟次ID
private int radiobutton1;//用户选择方式   申请单查询时  0:所有用户  1:指定下单用户编号 2:指定下单用户名 3:不限
private int radiobutton2;//时间选择方式   申请单查询时 0:按首次游览日期查询  1:按订单支付日期查询 2:不限
private int radiobutton3;//其它选择方式  申请单查询时  0:按照竹筏趟次 1:不限
public String usids;//子用户列表，显示为(a,b)
private int errorsid;//错误标识
private String tdlb;//退订类别
private String tpfs;//退订方式
private String bussinessid;//业务ID
private String orfl;
private int isfy;//是否分页  0：分页  1：不分页
private String note;//备注



public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public int getIsfy() {
	return isfy;
}
public void setIsfy(int isfy) {
	this.isfy = isfy;
}
public String getBussinessid() {
    return bussinessid;
}
public void setBussinessid(String bussinessid) {
    this.bussinessid = bussinessid;
}
public String getTdlb() {
	return tdlb;
}
public void setTdlb(String tdlb) {
	this.tdlb = tdlb;
}

public String getTpfs() {
	return tpfs;
}
public void setTpfs(String tpfs) {
	this.tpfs = tpfs;
}
public String getItickettypeid() {
    return itickettypeid;
}
public void setItickettypeid(String itickettypeid) {
    this.itickettypeid = itickettypeid;
}
public String getDateQuery() {
	return dateQuery;
}
public void setDateQuery(String dateQuery) {
	this.dateQuery = dateQuery;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public String getOrderPayState() {
	return orderPayState;
}
public void setOrderPayState(String orderPayState) {
	this.orderPayState = orderPayState;
}
public String getChildCustom() {
	return childCustom;
}
public void setChildCustom(String childCustom) {
	this.childCustom = childCustom;
}
public String getOrid() {
	return orid;
}
public void setOrid(String orid) {
	this.orid = orid;
}
public String getOrnm() {
	return ornm;
}
public void setOrnm(String ornm) {
	this.ornm = ornm;
}
public String getOrhm() {
	return orhm;
}
public void setOrhm(String orhm) {
	this.orhm = orhm;
}
public String getSztravelbillno() {
	return sztravelbillno;
}
public void setSztravelbillno(String sztravelbillno) {
	this.sztravelbillno = sztravelbillno;
}
public String getUsid() {
	return usid;
}
public void setUsid(String usid) {
	this.usid = usid;
}
public String getPayorid() {
	return payorid;
}
public void setPayorid(String payorid) {
	this.payorid = payorid;
}
public String getDdzt() {
	return ddzt;
}
public void setDdzt(String ddzt) {
	this.ddzt = ddzt;
}

public String getStrornm() {
	return strornm;
}
public void setStrornm(String strornm) {
	this.strornm = strornm;
}
public int getRadiobutton1() {
	return radiobutton1;
}
public void setRadiobutton1(int radiobutton1) {
	this.radiobutton1 = radiobutton1;
}
public int getRadiobutton2() {
	return radiobutton2;
}
public void setRadiobutton2(int radiobutton2) {
	this.radiobutton2 = radiobutton2;
}
public int getRadiobutton3() {
	return radiobutton3;
}
public void setRadiobutton3(int radiobutton3) {
	this.radiobutton3 = radiobutton3;
}
public String getUsids() {
	return usids;
}
public void setUsids(String usids) {
	this.usids = usids;
}
public int getErrorsid() {
	return errorsid;
}
public void setErrorsid(int errorsid) {
	this.errorsid = errorsid;
}
public String getXrid() {
	return xrid;
}
public void setXrid(String xrid) {
	this.xrid = xrid;
}
public Long getIscenicid() {
	return iscenicid;
}
public void setIscenicid(Long iscenicid) {
	this.iscenicid = iscenicid;
}
public String getZffs() {
	return zffs;
}
public void setZffs(String zffs) {
	this.zffs = zffs;
}
public String getBank() {
	return bank;
}
public void setBank(String bank) {
	this.bank = bank;
}
public Long getTripid() {
	return tripid;
}
public void setTripid(Long tripid) {
	this.tripid = tripid;
}
public void setTripid(String tripid) {
	this.tripid = Long.parseLong(tripid);
}
public String getDyusid() {
	return dyusid;
}
public void setDyusid(String dyusid) {
	this.dyusid = dyusid;
}
public String getOrfl() {
	return orfl;
}
public void setOrfl(String orfl) {
	this.orfl = orfl;
}
 


}
