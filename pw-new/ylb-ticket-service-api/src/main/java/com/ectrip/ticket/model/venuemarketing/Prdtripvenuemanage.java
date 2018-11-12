package com.ectrip.ticket.model.venuemarketing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Prdtripvenuemanage entity.
 * 产品趟次区域关系表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Prdtripvenuemanage")
public class Prdtripvenuemanage implements java.io.Serializable {

	// Fields

	@Id
	private Long productmanageid;            //关系ID
	@Column(name="ISCENICID")
	private Long iscenicid; 				//服务商ID
	@Column(name="TRIPID")
	private Long tripid;					//场次ID
	@Column(name="ITICKETTYPEID")
	private Long itickettypeid;				//产品ID
	@Column(name="ENDDATA")
	private String enddata;					//结束日期
	@Column(name="STARTDATA")
	private String startdata;				//开始日期
	@Column(name="STARTTIME")
	private String starttime;				// 开始时间
	@Column(name="ENDTIME")
	private String endtime;					// 结束时间
	@Column(name="IADVANCEMINUTE")
	private Long iadvanceminute;			// 提前时间
	@Column(name="ILAGMINUTE")
	private Long ilagminute;				 // 滞后时间
	@Column(name="ISKEEPMINUTE")
	private Long iskeepminute;				// 预留量保留距场次结束时间
	@Column(name="SALETYPE")
	private String saletype;				//销售类别
	@Column(name="IVENUEID")
	private Long ivenueid;					//场地ID
	@Column(name="IVENUEAREAID")
	private Long ivenueareaid;				//场馆区域ID
	@Column(name="MANAGETYPE")
	private String managetype;				//场地绑定类别
	@Column(name="ISHOT")
	private Long ishot;					//预定积分

	//数据库外字段
	@Transient
	private String tripname;       //场次名称
	@Transient
	private String venueidname;    //场地名称
	@Transient
	private String ivenueareaname; //场地区域名称
	@Transient
	private String sztickettypename;//产品名称
	@Transient
	private String szscenicname;    //服务商名称
	@Transient
	private String[] trips;    //服务商名称
	@Transient
	private String starthour;
	@Transient
	private String startminute;
	@Transient
	private String endthour;
	@Transient
	private String endtminute;
	@Transient
	private String oldstartdata;
	@Transient
	private String oldenddata;
	@Transient
	private String time;
	// Constructors

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOldstartdata() {
		return oldstartdata;
	}

	public void setOldstartdata(String oldstartdata) {
		this.oldstartdata = oldstartdata;
	}

	public String getOldenddata() {
		return oldenddata;
	}

	public void setOldenddata(String oldenddata) {
		this.oldenddata = oldenddata;
	}

	public String getTripname() {
		return tripname;
	}

	public void setTripname(String tripname) {
		this.tripname = tripname;
	}

	public String getVenueidname() {
		return venueidname;
	}

	public void setVenueidname(String venueidname) {
		this.venueidname = venueidname;
	}

	public String getIvenueareaname() {
		return ivenueareaname;
	}

	public void setIvenueareaname(String ivenueareaname) {
		this.ivenueareaname = ivenueareaname;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}


	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	/** default constructor */
	public Prdtripvenuemanage() {
	}


	/** minimal constructor */
	public Prdtripvenuemanage(Long productmanageid, Long iscenicid,
							  Long tripid, Long itickettypeid, String enddata, String startdata,
							  String starttime, String endtime, Long iadvanceminute,
							  Long ilagminute, Long iskeepminute, Long ivenueid,
							  Long ivenueareaid, String managetype) {

		this.productmanageid = productmanageid;
		this.iscenicid = iscenicid;
		this.tripid = tripid;
		this.itickettypeid = itickettypeid;
		this.enddata = enddata;
		this.startdata = startdata;
		this.starttime = starttime;
		this.endtime = endtime;
		this.iadvanceminute = iadvanceminute;
		this.ilagminute = ilagminute;
		this.iskeepminute = iskeepminute;
		this.ivenueid = ivenueid;
		this.ivenueareaid = ivenueareaid;
		this.managetype = managetype;
	}

	/** full constructor */
	public Prdtripvenuemanage(Long productmanageid, Long iscenicid,
							  Long tripid, Long itickettypeid, String enddata, String startdata,
							  String starttime, String endtime, Long iadvanceminute,
							  Long ilagminute, Long iskeepminute, String saletype, Long ivenueid,
							  Long ivenueareaid, String managetype) {
		this.productmanageid = productmanageid;
		this.iscenicid = iscenicid;
		this.tripid = tripid;
		this.itickettypeid = itickettypeid;
		this.enddata = enddata;
		this.startdata = startdata;
		this.starttime = starttime;
		this.endtime = endtime;
		this.iadvanceminute = iadvanceminute;
		this.ilagminute = ilagminute;
		this.iskeepminute = iskeepminute;
		this.saletype = saletype;
		this.ivenueid = ivenueid;
		this.ivenueareaid = ivenueareaid;
		this.managetype = managetype;

		this.saletype = saletype;

	}

	// Property accessors

	public Long getProductmanageid() {
		return this.productmanageid;
	}

	public void setProductmanageid(Long productmanageid) {
		this.productmanageid = productmanageid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getTripid() {
		return this.tripid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getEnddata() {
		return this.enddata;
	}

	public void setEnddata(String enddata) {
		this.enddata = enddata;
	}

	public String getStartdata() {
		return this.startdata;
	}

	public void setStartdata(String startdata) {
		this.startdata = startdata;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Long getIadvanceminute() {
		return this.iadvanceminute;
	}

	public void setIadvanceminute(Long iadvanceminute) {
		this.iadvanceminute = iadvanceminute;
	}



	public Long getIlagminute() {
		return this.ilagminute;

	}

	public void setIlagminute(Long ilagminute) {
		this.ilagminute = ilagminute;
	}


	public Long getIskeepminute() {
		return this.iskeepminute;
	}

	public void setIskeepminute(Long iskeepminute) {
		this.iskeepminute = iskeepminute;
	}

	public String getSaletype() {
		return this.saletype;
	}

	public void setSaletype(String saletype) {
		this.saletype = saletype;
	}

	public Long getIvenueid() {
		return this.ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}

	public Long getIvenueareaid() {
		return this.ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

	public String getManagetype() {
		return this.managetype;
	}

	public void setManagetype(String managetype) {
		this.managetype = managetype;
	}

	public String[] getTrips() {
		return trips;
	}

	public void setTrips(String[] trips) {
		this.trips = trips;
	}

	public String getStarthour() {
		return starthour;
	}

	public void setStarthour(String starthour) {
		this.starthour = starthour;
	}

	public String getStartminute() {
		return startminute;
	}

	public void setStartminute(String startminute) {
		this.startminute = startminute;
	}

	public String getEndthour() {
		return endthour;
	}

	public void setEndthour(String endthour) {
		this.endthour = endthour;
	}

	public String getEndtminute() {
		return endtminute;
	}

	public void setEndtminute(String endtminute) {
		this.endtminute = endtminute;
	}

	public Long getIshot() {
		return ishot;
	}

	public void setIshot(Long ishot) {
		this.ishot = ishot;
	}


}