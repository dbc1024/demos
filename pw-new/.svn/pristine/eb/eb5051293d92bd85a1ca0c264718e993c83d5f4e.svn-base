package com.ectrip.ticket.model.order;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Stscomticketsalesdetailstab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Stscomticketsalesdetailstab implements java.io.Serializable {

	// Fields
	@Id
	private StscomticketsalesdetailstabId id;
	private Long icrowdkindpriceid;
	private Long itickettypeid;
	private Long iztickettypeid;
	private Long tripid;
	private Long ivenueid;
	private Long ivenueareaid;
	private Long ivenueseatsid;
	private String dtstartdate;
	private String dtenddate;
	private Long isplitamount;
	private Double msplitprice;//单价
	private Double msplitmoney;//总价
	private Long iversion;
	private Double mhandcharge;
	private Double mderatemoney;// 减免金额
	private Long ideratenums;//减免数量
	private String dtmakedate;
	private String timeStart; //分时开始时间
	private String timeEnd; //分时结束时间
	private Long timeId;
	@Transient
	private String seatsid;//座位集合
	@Transient
	private Long itripprdcontrolid;//排班ID
	@Transient
	private List seatlist;
	@Transient
	private String bycategorytype; //子票种类
	@Transient
	private String sztickettypename;//主票名称
	@Transient
	private String szztickettypename;//子票名称
	@Transient
	private Double tdfl;
	public String getSeatsid() {
		return seatsid;
	}

	public void setSeatsid(String seatsid) {
		this.seatsid = seatsid;
	}

	public Long getItripprdcontrolid() {
		return itripprdcontrolid;
	}

	public void setItripprdcontrolid(Long itripprdcontrolid) {
		this.itripprdcontrolid = itripprdcontrolid;
	}

	public List getSeatlist() {
		return seatlist;
	}

	public void setSeatlist(List seatlist) {
		this.seatlist = seatlist;
	}

	public Long getIderatenums() {
		return ideratenums;
	}

	public void setIderatenums(Long ideratenums) {
		this.ideratenums = ideratenums;
	}

	public Double getMderatemoney() {
		return mderatemoney;
	}

	public void setMderatemoney(Double mderatemoney) {
		this.mderatemoney = mderatemoney;
	}
	// Constructors
	public Double getTdfl() {
		return tdfl;
	}
	
	public void setTdfl(Double tdfl) {
		this.tdfl = tdfl;
	}

	/** default constructor */
	public Stscomticketsalesdetailstab() {
	}

	/** minimal constructor */
	public Stscomticketsalesdetailstab(StscomticketsalesdetailstabId id,
			Long icrowdkindpriceid, Long itickettypeid, String dtstartdate,
			String dtenddate, Long isplitamount, Double msplitprice,
			Double msplitmoney, Long iversion, Double mhandcharge) {
		this.id = id;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.itickettypeid = itickettypeid;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.isplitamount = isplitamount;
		this.msplitprice = msplitprice;
		this.msplitmoney = msplitmoney;
		this.iversion = iversion;
		this.mhandcharge = mhandcharge;
	}

	/** full constructor */
	public Stscomticketsalesdetailstab(StscomticketsalesdetailstabId id,
			Long icrowdkindpriceid, Long itickettypeid, Long iztickettypeid,
			Long tripid, Long ivenueid, Long ivenueareaid, Long ivenueseatsid,
			String dtstartdate, String dtenddate, Long isplitamount,
			Double msplitprice, Double msplitmoney, Long iversion, Double mhandcharge) {
		this.id = id;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.itickettypeid = itickettypeid;
		this.iztickettypeid = iztickettypeid;
		this.tripid = tripid;
		this.ivenueid = ivenueid;
		this.ivenueareaid = ivenueareaid;
		this.ivenueseatsid = ivenueseatsid;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.isplitamount = isplitamount;
		this.msplitprice = msplitprice;
		this.msplitmoney = msplitmoney;
		this.iversion = iversion;
		this.mhandcharge = mhandcharge;
	}

	// Property accessors

	public StscomticketsalesdetailstabId getId() {
		return this.id;
	}

	public void setId(StscomticketsalesdetailstabId id) {
		this.id = id;
	}

	public Long getIcrowdkindpriceid() {
		return this.icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIztickettypeid() {
		return this.iztickettypeid;
	}

	public void setIztickettypeid(Long iztickettypeid) {
		this.iztickettypeid = iztickettypeid;
	}

	public Long getTripid() {
		return this.tripid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
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

	public Long getIvenueseatsid() {
		return this.ivenueseatsid;
	}

	public void setIvenueseatsid(Long ivenueseatsid) {
		this.ivenueseatsid = ivenueseatsid;
	}

	public String getDtstartdate() {
		return this.dtstartdate;
	}

	public void setDtstartdate(String dtstartdate) {
		this.dtstartdate = dtstartdate;
	}

	public String getDtenddate() {
		return this.dtenddate;
	}

	public void setDtenddate(String dtenddate) {
		this.dtenddate = dtenddate;
	}

	public Long getIsplitamount() {
		return this.isplitamount;
	}

	public void setIsplitamount(Long isplitamount) {
		this.isplitamount = isplitamount;
	}

	public Double getMsplitprice() {
		return this.msplitprice;
	}

	public void setMsplitprice(Double msplitprice) {
		this.msplitprice = msplitprice;
	}

	public Double getMsplitmoney() {
		return this.msplitmoney;
	}

	public void setMsplitmoney(Double msplitmoney) {
		this.msplitmoney = msplitmoney;
	}

	public Long getIversion() {
		return this.iversion;
	}

	public void setIversion(Long iversion) {
		this.iversion = iversion;
	}

	public Double getMhandcharge() {
		return this.mhandcharge;
	}

	public void setMhandcharge(Double mhandcharge) {
		this.mhandcharge = mhandcharge;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public String getSzztickettypename() {
		return szztickettypename;
	}

	public void setSzztickettypename(String szztickettypename) {
		this.szztickettypename = szztickettypename;
	}

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getBycategorytype() {
		return bycategorytype;
	}

	public void setBycategorytype(String bycategorytype) {
		this.bycategorytype = bycategorytype;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}
}