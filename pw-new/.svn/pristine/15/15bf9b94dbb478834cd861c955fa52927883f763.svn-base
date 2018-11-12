package com.ectrip.ec.model.ticket;

import java.io.Serializable;
import java.util.List;

public class OrderPojo implements Serializable,Comparable{
	
	/**
	 * OrderPojo.java
	 * yangguang
	 * 20112011-11-4
	 */
	private static final long serialVersionUID = 1000000;
	private String itickettypeid;//产品ID
	private String iscenicid;//服务商ID
	private String iparentScenicid;
	private List<OrderPojo> price;//价格、数量列表
	private boolean isenough=true;//系统是否满足当前需求量
	private String ticketid;//含日期的票ID，一般是基础票含日期    而非
	private String ticketname;//票名
	private int  issale;//此票中含竹筏票的数量,最终用于判断此票是否含有竹筏票 只要此数大于0 就是含有竹筏票
	private String szcrowdkindname;//人群种类
	private String icrowdkindid;
	private Integer icrowdkindpriceid;
	private int ipeoplenumrange;//基数,用来做什么? 没明白
	private double listingprice;//挂牌价
	private double mactualsaleprice;//实际销售价格
	private List<OrderPojo> pricelist;//价格列表  即一个票可以针对人群有多种价格,比如：儿童价，成人价等。显示的时候用的,获取的时候用的price
	private String numb;//数量
	private String bycategorytype;
	private String productcontrolid;
	private List<OrderPojo> sonTicket;//获取需要日期等详细时间的子票列表
	private String tourdate;
	private String totalnum;	
	private String status;//当前可售状态 -1 不让卖 -2 数量不足
	private String wharf;
	private String cansalenum;
	private String starttime;
	private String parentticketid;
	private String small;//小计
	private String total;//总计
	private String tripname;
//	private String productmanageid;
	/**积分**/
	private String isyh;
	private String point="0";
	private String monthpoint="0";
	private String yearpoint="0";
	private String smallnum;//小计数量
	private String jflb;
	/***免票政策**/
	private int basnum;//基数
	private int scnum;//免数
	private String schemetype;//优惠类型
	private int scheme;//是否优惠
	private int freenum;
	private String fsstarttime;// 分时开始时间
	private String fsendtime;// 分时结束时间
	private Long fstimeid;// 分时预约时间段id
	
	
	public Long getFstimeid() {
		return fstimeid;
	}


	public void setFstimeid(Long fstimeid) {
		this.fstimeid = fstimeid;
	}


	public String getFsstarttime() {
		return fsstarttime;
	}


	public void setFsstarttime(String fsstarttime) {
		this.fsstarttime = fsstarttime;
	}


	public String getFsendtime() {
		return fsendtime;
	}


	public void setFsendtime(String fsendtime) {
		this.fsendtime = fsendtime;
	}


	public int getFreenum() {
		return freenum;
	}


	public void setFreenum(int freenum) {
		this.freenum = freenum;
	}


	public String getIparentScenicid() {
	    return iparentScenicid;
	}


	public void setIparentScenicid(String iparentScenicid) {
	    this.iparentScenicid = iparentScenicid;
	}


	public String getSmallnum() {
	    return smallnum;
	}


	public void setSmallnum(String smallnum) {
	    this.smallnum = smallnum;
	}


	public String getIsyh() {
	    return isyh;
	}


	public void setIsyh(String isyh) {
	    this.isyh = isyh;
	}
	
	

	public String getMonthpoint() {
		return monthpoint;
	}


	public void setMonthpoint(String monthpoint) {
		this.monthpoint = monthpoint;
	}


	public String getYearpoint() {
		return yearpoint;
	}


	public void setYearpoint(String yearpoint) {
		this.yearpoint = yearpoint;
	}


	public void setPricelist(List<OrderPojo> pricelist) {
		this.pricelist = pricelist;
	}


//	public String getProductmanageid() {
//		return productmanageid;
//	}
//
//
//	public void setProductmanageid(String productmanageid) {
//		this.productmanageid = productmanageid;
//	}


	public String getPoint() {
		return point;
	}


	public void setPoint(String point) {
		this.point = point;
	}


	public String getIcrowdkindid() {
		return icrowdkindid;
	}


	public String getTripname() {
		return tripname;
	}


	public void setTripname(String tripname) {
		this.tripname = tripname;
	}


	public String getWharf() {
		return wharf;
	}


	public void setWharf(String wharf) {
		this.wharf = wharf;
	}


	public String getCansalenum() {
		return cansalenum;
	}


	public void setCansalenum(String cansalenum) {
		this.cansalenum = cansalenum;
	}


	public String getStarttime() {
		return starttime;
	}


	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}


	public String getSmall() {
		return small;
	}


	public void setSmall(String small) {
		this.small = small;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}


	public void setIcrowdkindid(String icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}


	public String getIscenicid() {
		return iscenicid;
	}


	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}


	public void OrderPojo(){
		this.isenough=true;
	}
	
	
	


	public String getParentticketid() {
		return parentticketid;
	}


	public void setParentticketid(String parentticketid) {
		this.parentticketid = parentticketid;
	}

	public void setPrice(List<OrderPojo> price) {
		this.price = price;
	}


	public String getItickettypeid() {
		return itickettypeid;
	}


	public void setItickettypeid(String itickettypeid) {
		this.itickettypeid = itickettypeid;
	}


	public boolean getIsenough() {
		return isenough;
	}


	public void setIsenough(boolean isenough) {
		this.isenough = isenough;
	}


	public String getTicketid() {
		return ticketid;
	}


	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}


	public String getTicketname() {
		return ticketname;
	}


	public void setTicketname(String ticketname) {
		this.ticketname = ticketname;
	}


	public int getIssale() {
		return issale;
	}


	public void setIssale(int issale) {
		this.issale = issale;
	}


	public String getSzcrowdkindname() {
		return szcrowdkindname;
	}


	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}


	public Integer getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}


	public void setIcrowdkindpriceid(Integer icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
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


	public List<OrderPojo> getPricelist() {
		return pricelist;
	}

	public String getNumb() {
		return numb;
	}


	public void setNumb(String numb) {
		this.numb = numb;
	}


	public String getBycategorytype() {
		return bycategorytype;
	}


	public void setBycategorytype(String bycategorytype) {
		this.bycategorytype = bycategorytype;
	}

	public String getProductcontrolid() {
		return productcontrolid;
	}


	public void setProductcontrolid(String productcontrolid) {
		this.productcontrolid = productcontrolid;
	}


	public List<OrderPojo> getSonTicket() {
		return sonTicket;
	}


	public void setSonTicket(List<OrderPojo> sonTicket) {
		this.sonTicket = sonTicket;
	}


	public String getTourdate() {
		return tourdate;
	}


	public void setTourdate(String tourdate) {
		this.tourdate = tourdate;
	}


	public String getTotalnum() {
		return totalnum;
	}


	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}


	public List<OrderPojo> getPrice() {
		return price;
	}
	
	
	
	
	public String getJflb() {
		return jflb;
	}


	public void setJflb(String jflb) {
		this.jflb = jflb;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public boolean myEquals(Object o){
		if(!(o instanceof OrderPojo)){
			return false;
		}else{
			OrderPojo pojo=(OrderPojo) o;
			if((getItickettypeid()==null&&pojo.getItickettypeid()!=null)||(getItickettypeid()!=null&&pojo.getItickettypeid()==null)){
				return false;
			}
			if((getProductcontrolid()==null&&pojo.getProductcontrolid()!=null)||(getProductcontrolid()!=null&&pojo.getProductcontrolid()==null)){
				return false;
			}
			if((getTourdate()==null&&pojo.getTourdate()!=null)||(getTourdate()!=null&&pojo.getTourdate()==null)){
				return false;
			}
			if(pojo.getParentticketid().equals(getParentticketid())&&pojo.getTicketid().toString().equals(getTicketid().toString())&&pojo.getProductcontrolid().equals(getProductcontrolid())&&pojo.getTourdate().equals(getTourdate())){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public boolean myEqualsSameDays(Object o){
		if(!(o instanceof OrderPojo)){
			return false;
		}else{
			OrderPojo pojo=(OrderPojo) o;
			if((getItickettypeid()==null&&pojo.getItickettypeid()!=null)||(getItickettypeid()!=null&&pojo.getItickettypeid()==null)){
				return false;
			}
			if((getTourdate()==null&&pojo.getTourdate()!=null)||(getTourdate()!=null&&pojo.getTourdate()==null)){
				return false;
			}
			if(pojo.getParentticketid().equals(getParentticketid())&&pojo.getTicketid().toString().equals(getTicketid().toString())&&pojo.getProductcontrolid().equals(getProductcontrolid())&&pojo.getTourdate().equals(getTourdate())){
				return true;
			}else{
				return false;
			}
		}
	}


	public int compareTo(Object o) {
		OrderPojo op=(OrderPojo) o;
		if(Integer.parseInt(this.getIscenicid())>Integer.parseInt(op.getIscenicid())){
			return 1;
		}else{
			return -1;
		}
	}


	public int getBasnum() {
		return basnum;
	}


	public void setBasnum(int basnum) {
		this.basnum = basnum;
	}


	public int getScnum() {
		return scnum;
	}


	public void setScnum(int scnum) {
		this.scnum = scnum;
	}

	public String getSchemetype() {
		return schemetype;
	}


	public void setSchemetype(String schemetype) {
		this.schemetype = schemetype;
	}


	public int getScheme() {
		return scheme;
	}


	public void setScheme(int scheme) {
		this.scheme = scheme;
	}
	
	
}

