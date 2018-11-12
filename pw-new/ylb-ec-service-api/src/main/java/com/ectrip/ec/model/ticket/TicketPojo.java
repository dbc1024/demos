package com.ectrip.ec.model.ticket;

import java.util.List;


public class TicketPojo {
	
	private Integer ticketid;//票ID
	private String ticketname;//票名
	private String sztickettypecode;
	private int  issale;//此票中含竹筏票的数量,最终用于判断此票是否含有竹筏票 只要此数大于0 就是含有竹筏票
	private String szcrowdkindname;//人群种类
	private Integer icrowdkindpriceid;
	private int ipeoplenumrange;//基数,用来做什么? 没明白
	private double listingprice;//挂牌价
	private double mactualsaleprice;//实际销售价格
	private List pricelist;//价格列表  即一个票可以针对人群有多种价格,比如：儿童价，成人价等。
	private String numb;//数量
	private String bycategorytype;
	private String productmanageid;
	private List<OrderPojo> sonTicket;//获取需要日期等详细时间的子票列表
	private String tourdate;
	private String totalnum;
	private String iscenicid;
	private String validityday;
	private String tripname;
	private String cansalenum;
	private String type;//1:日控制不足 2:趟次控制不足 3:过期
	private String wharf;
	private String starttime;
	private String endtime;
	private String tripid;
	private Long ivenueid;
	private Long ivenueareaid;
	private Long ivenueseatsid;
	private String point;//积分
	private String isyh;//是否优惠产品
	private String szmemo;
	private String time;
	
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSztickettypecode() {
		return sztickettypecode;
	}
	public void setSztickettypecode(String sztickettypecode) {
		this.sztickettypecode = sztickettypecode;
	}
	public String getSzmemo() {
	    return szmemo;
	}
	public void setSzmemo(String szmemo) {
	    this.szmemo = szmemo;
	}
	public String getIsyh() {
	    return isyh;
	}
	public void setIsyh(String isyh) {
	    this.isyh = isyh;
	}
	public String getPoint() {
	    return point;
	}
	public void setPoint(String point) {
	    this.point = point;
	}
	public Long getIvenueid() {
		return ivenueid;
	}
	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}
	public Long getIvenueareaid() {
		return ivenueareaid;
	}
	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}
	public Long getIvenueseatsid() {
		return ivenueseatsid;
	}
	public void setIvenueseatsid(Long ivenueseatsid) {
		this.ivenueseatsid = ivenueseatsid;
	}
	public String getTripid() {
		return tripid;
	}
	public void setTripid(String tripid) {
		this.tripid = tripid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getWharf() {
		return wharf;
	}
	public void setWharf(String wharf) {
		this.wharf = wharf;
	}
	public String getTripname() {
		return tripname;
	}
	public void setTripname(String tripname) {
		this.tripname = tripname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCansalenum() {
		return cansalenum;
	}
	public void setCansalenum(String cansalenum) {
		this.cansalenum = cansalenum;
	}
	public String getValidityday() {
		return validityday;
	}
	public void setValidityday(String validityday) {
		this.validityday = validityday;
	}
	public String getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}
	public String getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}
	public String getTourdate() {
		return tourdate;
	}
	public void setTourdate(String tourdate) {
		this.tourdate = tourdate;
	}
	public String getProductmanageid() {
		return productmanageid;
	}
	public void setProductmanageid(String productmanageid) {
		this.productmanageid = productmanageid;
	}
	public String getBycategorytype() {
		return bycategorytype;
	}
	public void setBycategorytype(String bycategorytype) {
		this.bycategorytype = bycategorytype;
	}
	
	public List getSonTicket() {
		return sonTicket;
	}
	public void setSonTicket(List<OrderPojo> sonTicket) {
		this.sonTicket = sonTicket;
	}
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	public Integer getTicketid() {
		return ticketid;
	}
	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}
	public int getIssale() {
		return issale;
	}
	public void setIssale(int issale) {
		this.issale = issale;
	}
	public String getTicketname() {
		return ticketname;
	}
	public void setTicketname(String ticketname) {
		this.ticketname = ticketname;
	}
	public String getSzcrowdkindname() {
		return szcrowdkindname;
	}
	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}
	public int getIpeoplenumrange() {
		return ipeoplenumrange;
	}
	public void setIpeoplenumrange(int ipeoplenumrange) {
		this.ipeoplenumrange = ipeoplenumrange;
	}
	public double getListingprice() {
		return listingprice;
	}
	public void setListingprice(double listingprice) {
		this.listingprice = listingprice;
	}
	public double getMactualsaleprice() {
		return mactualsaleprice;
	}
	public void setMactualsaleprice(double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}
	public List getPricelist() {
		return pricelist;
	}
	public void setPricelist(List pricelist) {
		this.pricelist = pricelist;
	}
	public Integer getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}
	public void setIcrowdkindpriceid(Integer icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

}

