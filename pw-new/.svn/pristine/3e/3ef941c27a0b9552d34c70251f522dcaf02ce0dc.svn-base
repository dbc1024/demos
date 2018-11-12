package com.ectrip.ec.model.order;

import java.io.Serializable;

/**
 * 订单明细信息
 * 
 * OrderInfo
 * 
 * hejiahua hejiahua 2013-10-22 上午11:39:03
 * 
 * @version 1.0.0
 * 
 */
public class OrderInfo implements Serializable {

	// 业务类型
	private Long ibusinessid;
	// 人群种类
	private Long icrowdkindid;

	private String strcrowd;
	// 服务商id
	private Long iscenicid;
	// 分组
	private String note1;// 价格分组
	// 服务商名称
	private String szscenicname;
	// 产品id
	private Long itickettypeid;
	// 产品名称
	private String sztickettypename;
	// 游玩日期(开始时间)
	private String playtime;
	// 结束时间
	private String enddate;
	// 成人数
	private Long youngcount;
	// 儿童数
	private Long childcount;
	// 房间
	private Long roomcount;

	private String szcrowdkindname;
	
	private boolean status;
	// 小计
	private Double xjmoney;
	// 购买数量
	private Long num;
	//优惠数量
	private Long yhnumb;
	// 优惠ID
	private Long ioffersschemeid;

	private String szoffersschemename;
	
	private Long imultiples; // 优惠基数
	private Long ioffernum; // 优惠数量
	private Long priceId;

	// 实际价格
	private Double price;
	
	//add by koka on 20170727
	// 分時預約開始時間點
	private String starttime;
	// 分時預約結束時間點
	private String endtime;
	
	//分时预约时间段ID
	private Long timeid;

	private boolean realName;
	public OrderInfo() {

	};

	public OrderInfo(Long iscenicid, String szscenicname, Long itickettypeid,
                     String sztickettypename, String playtime, Long num,String starttime,String endtime,Long timeid,
                     Long ibusinessid, Long icrowdkindid, String note1, String enddate) {
		this.iscenicid = iscenicid;
		this.szscenicname = szscenicname;
		this.itickettypeid = itickettypeid;
		this.sztickettypename = sztickettypename;
		this.playtime = playtime;
		this.num = num;
		this.ibusinessid = ibusinessid;
		this.icrowdkindid = icrowdkindid;
		this.note1 = note1;
		this.enddate = enddate;
		this.starttime = starttime;
		this.endtime = endtime;
		this.timeid=timeid;
	}

	public OrderInfo(Long iscenicid, String szscenicname, Long itickettypeid,
                     String sztickettypename, String playtime, Long num,String starttime,String endtime,
                     Long ibusinessid, Long icrowdkindid, String note1, String enddate,Long timeid,
                     Long youngcount, Long childcount, Long roomcount) {
		this.iscenicid = iscenicid;
		this.szscenicname = szscenicname;
		this.itickettypeid = itickettypeid;
		this.sztickettypename = sztickettypename;
		this.playtime = playtime;
		this.num = num;
		this.ibusinessid = ibusinessid;
		this.icrowdkindid = icrowdkindid;
		this.note1 = note1;
		this.enddate = enddate;
		this.childcount = childcount;
		this.youngcount = youngcount;
		this.roomcount = roomcount;
		this.starttime = starttime;
		this.endtime = endtime;
		this.timeid=timeid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public Long getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public String getPlaytime() {
		return playtime;
	}

	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}

	public Double getXjmoney() {
		return xjmoney;
	}

	public void setXjmoney(Double xjmoney) {
		
			this.xjmoney = xjmoney;// 小计等于数量乘以单价
		
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getIbusinessid() {
		return ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public Long getIcrowdkindid() {
		return icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getStrcrowd() {
		return strcrowd;
	}

	public void setStrcrowd(String strcrowd) {
		this.strcrowd = strcrowd;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSzcrowdkindname() {
		return szcrowdkindname;
	}

	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}

	public Long getIoffersschemeid() {
		return ioffersschemeid;
	}

	public void setIoffersschemeid(Long ioffersschemeid) {
		this.ioffersschemeid = ioffersschemeid;
	}

	

	public Long getYhnumb() {
		return yhnumb;
	}

	public void setYhnumb(Long yhnumb) {
		this.yhnumb = yhnumb;
	}

	public Long getYoungcount() {
		return youngcount;
	}

	public void setYoungcount(Long youngcount) {
		this.youngcount = youngcount;
	}

	public Long getChildcount() {
		return childcount;
	}

	public void setChildcount(Long childcount) {
		this.childcount = childcount;
	}

	public Long getRoomcount() {
		return roomcount;
	}

	public void setRoomcount(Long roomcount) {
		this.roomcount = roomcount;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getSzoffersschemename() {
		return szoffersschemename;
	}

	public void setSzoffersschemename(String szoffersschemename) {
		this.szoffersschemename = szoffersschemename;
	}
	public Long getImultiples() {
		return imultiples;
	}

	public void setImultiples(Long imultiples) {
		this.imultiples = imultiples;
	}

	public Long getIoffernum() {
		return ioffernum;
	}

	public void setIoffernum(Long ioffernum) {
		this.ioffernum = ioffernum;
	}

	public boolean isRealName() {
		return realName;
	}

	public void setRealName(boolean realName) {
		this.realName = realName;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
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

	public Long getTimeid() {
		return timeid;
	}

	public void setTimeid(Long timeid) {
		this.timeid = timeid;
	}
	
	
	
}
