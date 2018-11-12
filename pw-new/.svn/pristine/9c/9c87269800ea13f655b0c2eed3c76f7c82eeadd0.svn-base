package com.ectrip.ticket.model.provider;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Hotelprovider entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Hotelprovider implements java.io.Serializable {

	// Fields
	@Id
	private Long iscenicid;//酒店编号
	private String yule;//娱乐设施
	private String canying;//餐饮设施
	private String zxjb;//酒类类型
	private String jwqj;//价格区间
	private String meet;//会议设施 
	private String location;//交通位置
	private String credit;//可接受的信用卡
	private String hotelservice;//酒店固有服务(HTSS)
	
	private String noted1;  //景区固有服务  酒店商圈(HTSQ)
	private String noted2;  //景区娱乐设施  酒店品牌(HTPP)
	private String noted3;  //IC卡的销售控制  酒店频临的重要交通站点
	private String noted4; //装修时间
	private String noted5;
	private Long inoteger1;   //酒店是否推荐  0:推荐,1:不推荐
	private Long inoteger2;   //是否实名制
	private Long inoteger3;	  //是否直接出票
	private Long inoteger4;	  //是否通用售票
	private Long inoteger5;   //IC卡是否入库售票
	private double numter1;//是否可以修改订单
	private double numter2;
	private double numter3;
	private double numter4;
	private double numter5;
	
	private String noted6;  //附近景区
	private String noted7;  //附近酒店
	private String noted8;  //推荐产品
	private String noted9;
	private String noted10;
	private Long inoteger6;   //套票是否可单独退子票
	private Long inoteger7;   //是否允许现付
	private Long inoteger8;	  //服务商优惠类型
	private Long inoteger9;	  //可修改游览日期次数
	private Long inoteger10;   //预订成团人数
	//非数据库字段
	@Transient
	private String strzxjb;//酒类类型
	@Transient
	private String strjwqj;//价格区间
	@Transient
	private String[] hotelsvc;//酒店服务项
	@Transient
	private String szscenicname;//服务商名称
	@Transient
	private boolean hotelsvcTemp;//修改标识
	@Transient
	private List hotelsvclist;
	@Transient
	private String[] sqids;//商圈ID
	@Transient
	private String[] serviceids;//设施ID
	@Transient
	private String[] brandids;//品牌ID
	@Transient
	private String[] traids;//交通ID
	// Constructors

	public boolean isHotelsvcTemp() {
		return hotelsvcTemp;
	}

	public void setHotelsvcTemp(boolean hotelsvcTemp) {
		this.hotelsvcTemp = hotelsvcTemp;
	}

	/** default constructor */
	public Hotelprovider() {
	}

	/** minimal constructor */
	public Hotelprovider(Long iscenicid, String zxjb, String jwqj,
			String hotelservice) {
		this.iscenicid = iscenicid;
		this.zxjb = zxjb;
		this.jwqj = jwqj;
		this.hotelservice = hotelservice;
	}

	/** full constructor */
	public Hotelprovider(Long iscenicid, String yule, String canying,
			String zxjb, String jwqj, String meet, String location,
			String credit, String hotelservice,Long inoteger1) {
		this.iscenicid = iscenicid;
		this.yule = yule;
		this.canying = canying;
		this.zxjb = zxjb;
		this.jwqj = jwqj;
		this.meet = meet;
		this.location = location;
		this.credit = credit;
		this.hotelservice = hotelservice;
		this.inoteger1=inoteger1;
	}

	// Property accessors

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getYule() {
		return this.yule;
	}

	public void setYule(String yule) {
		this.yule = yule;
	}

	public String getCanying() {
		return this.canying;
	}

	public void setCanying(String canying) {
		this.canying = canying;
	}

	public String getZxjb() {
		return this.zxjb;
	}

	public void setZxjb(String zxjb) {
		this.zxjb = zxjb;
	}

	public String getJwqj() {
		return this.jwqj;
	}

	public void setJwqj(String jwqj) {
		this.jwqj = jwqj;
	}

	public String getMeet() {
		return this.meet;
	}

	public void setMeet(String meet) {
		this.meet = meet;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCredit() {
		return this.credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getHotelservice() {
		return this.hotelservice;
	}

	public void setHotelservice(String hotelservice) {
		this.hotelservice = hotelservice;
	}

	public String getStrzxjb() {
		return strzxjb;
	}

	public void setStrzxjb(String strzxjb) {
		this.strzxjb = strzxjb;
	}

	public String getStrjwqj() {
		return strjwqj;
	}

	public void setStrjwqj(String strjwqj) {
		this.strjwqj = strjwqj;
	}

	public String[] getHotelsvc() {
		return hotelsvc;
	}

	public void setHotelsvc(String[] hotelsvc) {
		this.hotelsvc = hotelsvc;
	}

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public List getHotelsvclist() {
		return hotelsvclist;
	}

	public void setHotelsvclist(List hotelsvclist) {
		this.hotelsvclist = hotelsvclist;
	}

	public String getNoted1() {
		return noted1;
	}

	public void setNoted1(String noted1) {
		this.noted1 = noted1;
	}

	public String getNoted2() {
		return noted2;
	}

	public void setNoted2(String noted2) {
		this.noted2 = noted2;
	}

	public String getNoted3() {
		return noted3;
	}

	public void setNoted3(String noted3) {
		this.noted3 = noted3;
	}

	public String getNoted4() {
		return noted4;
	}

	public void setNoted4(String noted4) {
		this.noted4 = noted4;
	}

	public String getNoted5() {
		return noted5;
	}

	public void setNoted5(String noted5) {
		this.noted5 = noted5;
	}

	public Long getInoteger1() {
		return inoteger1;
	}

	public void setInoteger1(Long inoteger1) {
		this.inoteger1 = inoteger1;
	}

	public Long getInoteger2() {
		return inoteger2;
	}

	public void setInoteger2(Long inoteger2) {
		this.inoteger2 = inoteger2;
	}

	public Long getInoteger3() {
		return inoteger3;
	}

	public void setInoteger3(Long inoteger3) {
		this.inoteger3 = inoteger3;
	}

	public Long getInoteger4() {
		return inoteger4;
	}

	public void setInoteger4(Long inoteger4) {
		this.inoteger4 = inoteger4;
	}

	public Long getInoteger5() {
		return inoteger5;
	}

	public void setInoteger5(Long inoteger5) {
		this.inoteger5 = inoteger5;
	}

	public double getNumter2() {
		return numter2;
	}

	public void setNumter2(double numter2) {
		this.numter2 = numter2;
	}

	public double getNumter3() {
		return numter3;
	}

	public void setNumter3(double numter3) {
		this.numter3 = numter3;
	}

	public double getNumter4() {
		return numter4;
	}

	public void setNumter4(double numter4) {
		this.numter4 = numter4;
	}

	public double getNumter5() {
		return numter5;
	}

	public void setNumter5(double numter5) {
		this.numter5 = numter5;
	}

	public double getNumter1() {
		return numter1;
	}

	public void setNumter1(double numter1) {
		this.numter1 = numter1;
	}

	public String getNoted6() {
		return noted6;
	}

	public void setNoted6(String noted6) {
		this.noted6 = noted6;
	}

	public String getNoted7() {
		return noted7;
	}

	public void setNoted7(String noted7) {
		this.noted7 = noted7;
	}

	public String getNoted8() {
		return noted8;
	}

	public void setNoted8(String noted8) {
		this.noted8 = noted8;
	}

	public String getNoted9() {
		return noted9;
	}

	public void setNoted9(String noted9) {
		this.noted9 = noted9;
	}

	public String getNoted10() {
		return noted10;
	}

	public void setNoted10(String noted10) {
		this.noted10 = noted10;
	}

	public Long getInoteger6() {
		return inoteger6;
	}

	public void setInoteger6(Long inoteger6) {
		this.inoteger6 = inoteger6;
	}

	public Long getInoteger7() {
		return inoteger7;
	}

	public void setInoteger7(Long inoteger7) {
		this.inoteger7 = inoteger7;
	}

	public Long getInoteger8() {
		return inoteger8;
	}

	public void setInoteger8(Long inoteger8) {
		this.inoteger8 = inoteger8;
	}

	public Long getInoteger9() {
		return inoteger9;
	}

	public void setInoteger9(Long inoteger9) {
		this.inoteger9 = inoteger9;
	}

	public Long getInoteger10() {
		return inoteger10;
	}

	public void setInoteger10(Long inoteger10) {
		this.inoteger10 = inoteger10;
	}

	public String[] getSqids() {
		return sqids;
	}

	public void setSqids(String[] sqids) {
		this.sqids = sqids;
	}

	public String[] getServiceids() {
		return serviceids;
	}

	public void setServiceids(String[] serviceids) {
		this.serviceids = serviceids;
	}

	public String[] getBrandids() {
		return brandids;
	}

	public void setBrandids(String[] brandids) {
		this.brandids = brandids;
	}

	public String[] getTraids() {
		return traids;
	}

	public void setTraids(String[] traids) {
		this.traids = traids;
	}

}