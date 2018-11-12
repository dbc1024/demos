package com.ectrip.ticket.model.venuemarketing;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Productcontrol entity.
 * 产品数量控制表
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="trip")
public class Productcontrol implements java.io.Serializable {

	// Fields
	@Id
	private Long productcontrolid; // 控制ID
	@Column(name="ISCENICID")
	private Long iscenicid; // 服务商ID
	@Column(name="ITICKETTYPEID")
	private Long itickettypeid; // 产品ID
	@Column(name="TRIPID")
	private Long tripid; // 趟次ID
	@Column(name="IVENUEID")
	private Long ivenueid; // 场地ID
	@Column(name="IVENUEAREAID")
	private Long ivenueareaid; // 场地区域ID
	@Column(name="CONTROLTYPE")
	private String controltype; // 数量控制模式
	@Column(name="STDATA")
	private String stdata; // 日期
	@Column(name="SALABLENUMBER")
	private Long salablenumber; // 可售数量
	@Column(name="SOLDNUMBER")
	private Long soldnumber; // 已售数量
	@Column(name="RESERVEDNUMBER")
	private Long reservednumber; // 预留量
	@Column(name="RESERVEDSALENUMBER")
	private Long reservedsalenumber; // 已售预留量
	@Column(name="CHANGEINNUMBER")
	private Long changeinnumber; // 改签进入量
	@Column(name="CHANGEOUTNUMBER")
	private Long changeoutnumber; // 改签出量
	@Column(name="BYISUSE")
	private Long byisuse; // 使用状态  默认为0 审核通过byisuse=1,审核不通过时 byisuse=2;
	@Column(name="BYSTATE")
	private Long bystate; // 场地状态 1 正常 0 开始停排 -1 确认停排
	@Column(name="BYISDUTY")
	private Long byisduty; // 0--二者都行 1--现金 2--网上 （趟次控制时为0 日期控制为2）
	@Column(name="ISXIANJIN")
	private Long isxianjin;//现金是否销售单竹筏
	@Column(name="VERSION")
	private Long version;
	// 数据库外字段
	@Transient
	private String tripname; // 场次名称
	@Transient
	private String venueidname; // 场地名称
	@Transient
	private String ivenueareaname; // 场地区域名称
	@Transient
	private String sztickettypename;// 产品名称
	@Transient
	private String szscenicname; // 服务商名称
	@Transient
	private String starttime;
	@Transient
	private String endtime;
	@Transient
	private Long wcpnumber;//未出票数
	@Transient
	private Long yjnumber;
	@Transient
	private List<Reservecontrol> reservecontrols; // 预留量分配表
	@Transient
	private Long istate;    //审核状态
	@Transient
	private String productmanageid;//排班编号
	// Constructors
	/**
	 * Productcontrol.java
	 * yuanchengjun
	 * 20122012-2-27
	 */

	private Long soldednumber;//单次数据存于此
	public Long getSoldednumber() {
		return soldednumber;
	}

	public void setSoldednumber(Long soldednumber) {
		this.soldednumber = soldednumber;
	}

	public Long getIstate() {
		return istate;
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

	public void setIstate(Long istate) {
		this.istate = istate;
	}

	/** default constructor */
	public Productcontrol() {
	}

	/** minimal constructor */
	public Productcontrol(Long productcontrolid, Long iscenicid,
						  Long itickettypeid, String controltype, String stdata,
						  Long salablenumber, Long soldnumber, Long reservednumber,
						  Long reservedsalenumber, Long byisuse, Long bystate, Long byisduty) {
		this.productcontrolid = productcontrolid;
		this.iscenicid = iscenicid;
		this.itickettypeid = itickettypeid;
		this.controltype = controltype;
		this.stdata = stdata;
		this.salablenumber = salablenumber;
		this.soldnumber = soldnumber;
		this.reservednumber = reservednumber;
		this.reservedsalenumber = reservedsalenumber;
		this.byisuse = byisuse;
		this.bystate = bystate;
		this.byisduty = byisduty;
	}

	/** full constructor */
	public Productcontrol(Long productcontrolid, Long iscenicid,
						  Long itickettypeid, Long tripid, Long ivenueid, Long ivenueareaid,
						  String controltype, String stdata, Long salablenumber,
						  Long soldnumber, Long reservednumber, Long reservedsalenumber,
						  Long byisuse, Long bystate, Long byisduty) {
		this.productcontrolid = productcontrolid;
		this.iscenicid = iscenicid;
		this.itickettypeid = itickettypeid;
		this.tripid = tripid;
		this.ivenueid = ivenueid;
		this.ivenueareaid = ivenueareaid;
		this.controltype = controltype;
		this.stdata = stdata;
		this.salablenumber = salablenumber;
		this.soldnumber = soldnumber;
		this.reservednumber = reservednumber;
		this.reservedsalenumber = reservedsalenumber;
		this.byisuse = byisuse;
		this.bystate = bystate;
		this.byisduty = byisduty;
	}

	// Property accessors

	public Long getProductcontrolid() {
		return this.productcontrolid;
	}

	public void setProductcontrolid(Long productcontrolid) {
		this.productcontrolid = productcontrolid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
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

	public String getControltype() {
		return this.controltype;
	}

	public void setControltype(String controltype) {
		this.controltype = controltype;
	}

	public String getStdata() {
		return this.stdata;
	}

	public void setStdata(String stdata) {
		this.stdata = stdata;
	}

	public Long getSalablenumber() {
		return this.salablenumber;
	}

	public void setSalablenumber(Long salablenumber) {
		this.salablenumber = salablenumber;
	}

	public Long getSoldnumber() {
		return this.soldnumber;
	}

	public void setSoldnumber(Long soldnumber) {
		this.soldnumber = soldnumber;
	}

	public Long getReservednumber() {
		return this.reservednumber;
	}

	public void setReservednumber(Long reservednumber) {
		this.reservednumber = reservednumber;
	}

	public Long getReservedsalenumber() {
		return this.reservedsalenumber;
	}

	public void setReservedsalenumber(Long reservedsalenumber) {
		this.reservedsalenumber = reservedsalenumber;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public Long getBystate() {
		return this.bystate;
	}

	public void setBystate(Long bystate) {
		this.bystate = bystate;
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

	public Long getByisduty() {
		return byisduty;
	}

	public void setByisduty(Long byisduty) {
		this.byisduty = byisduty;
	}

	public List<Reservecontrol> getReservecontrols() {
		return reservecontrols;
	}

	public void setReservecontrols(List<Reservecontrol> reservecontrols) {
		this.reservecontrols = reservecontrols;
	}

	public String getProductmanageid() {
		return productmanageid;
	}

	public void setProductmanageid(String productmanageid) {
		this.productmanageid = productmanageid;
	}

	public Long getChangeinnumber() {
		return changeinnumber;
	}

	public void setChangeinnumber(Long changeinnumber) {
		this.changeinnumber = changeinnumber;
	}

	public Long getChangeoutnumber() {
		return changeoutnumber;
	}

	public void setChangeoutnumber(Long changeoutnumber) {
		this.changeoutnumber = changeoutnumber;
	}

	public Long getWcpnumber() {
		return wcpnumber;
	}

	public void setWcpnumber(Long wcpnumber) {
		this.wcpnumber = wcpnumber;
	}

	public Long getYjnumber() {
		return yjnumber;
	}

	public void setYjnumber(Long yjnumber) {
		this.yjnumber = yjnumber;
	}

	public Long getIsxianjin() {
		return isxianjin;
	}

	public void setIsxianjin(Long isxianjin) {
		this.isxianjin = isxianjin;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}