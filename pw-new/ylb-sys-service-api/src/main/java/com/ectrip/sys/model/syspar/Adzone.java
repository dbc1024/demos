package com.ectrip.sys.model.syspar;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * Adzone entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ADZONE")
public class Adzone implements java.io.Serializable {

	// Fields
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "increment")
	@GeneratedValue(generator = "idGenerator")
	private Long zoneid;

	@Column
	private String zonename;
	@Column
	private String zoneintro;
	@Column
	private Long zonetype;
	@Column
	private Long defaultsetting;
	@Column
	private String zonesetting;
	@Column
	private String sizetype;
	@Column
	private String zonesize;
	@Column
	private String zonewidth;
	@Column
	private String zoneheight;
	@Column
	private Long active;
	@Column
	private Long showtype;
	@Column
	private String creattime;
	@Column
	private String updatetime;
	@Column
	private String empid;

	// pop弹出窗口参数设置
	@Transient
	private String poppoptype;// 弹出方式
	@Transient
	private String popleft;// 弹出位置（左）
	@Transient
	private String poptop;// 弹出位置（上）
	@Transient
	private String popcookiehour;// 时间间隔
	// move随屏移动参数设置
	@Transient
	private String moveleft;// 广告位置（左）
	@Transient
	private String movetop;// 广告位置（上）
	@Transient
	private String movedelta;// 移动平滑度
	// fix固定位置参数设置
	@Transient
	private String fixedleft;// 广告位置（左）
	@Transient
	private String fixedtop;// 广告位置（上）
	// float漂浮移动参数设置
	@Transient
	private String floattype;// 漂浮类型
	@Transient
	private String floatleft;// 开始位置（左）
	@Transient
	private String floattop;// 开始位置（上）

	public String getPoppoptype() {
		return poppoptype;
	}

	public void setPoppoptype(String poppoptype) {
		this.poppoptype = poppoptype;
	}

	public String getPopleft() {
		return popleft;
	}

	public void setPopleft(String popleft) {
		this.popleft = popleft;
	}

	public String getPoptop() {
		return poptop;
	}

	public void setPoptop(String poptop) {
		this.poptop = poptop;
	}

	public String getPopcookiehour() {
		return popcookiehour;
	}

	public void setPopcookiehour(String popcookiehour) {
		this.popcookiehour = popcookiehour;
	}

	public String getMoveleft() {
		return moveleft;
	}

	public void setMoveleft(String moveleft) {
		this.moveleft = moveleft;
	}

	public String getMovetop() {
		return movetop;
	}

	public void setMovetop(String movetop) {
		this.movetop = movetop;
	}

	public String getMovedelta() {
		return movedelta;
	}

	public void setMovedelta(String movedelta) {
		this.movedelta = movedelta;
	}

	public String getFixedleft() {
		return fixedleft;
	}

	public void setFixedleft(String fixedleft) {
		this.fixedleft = fixedleft;
	}

	public String getFixedtop() {
		return fixedtop;
	}

	public void setFixedtop(String fixedtop) {
		this.fixedtop = fixedtop;
	}

	public String getFloattype() {
		return floattype;
	}

	public void setFloattype(String floattype) {
		this.floattype = floattype;
	}

	public String getFloatleft() {
		return floatleft;
	}

	public void setFloatleft(String floatleft) {
		this.floatleft = floatleft;
	}

	public String getFloattop() {
		return floattop;
	}

	public void setFloattop(String floattop) {
		this.floattop = floattop;
	}

	// Constructors

	/** default constructor */
	public Adzone() {
	}

	/** minimal constructor */
	public Adzone(String zonename, Long zonetype, Long defaultsetting, String zonesetting, String sizetype,
                  String zonesize, Long active, Long showtype, String creattime, String updatetime, String empid) {
		this.zonename = zonename;
		this.zonetype = zonetype;
		this.defaultsetting = defaultsetting;
		this.zonesetting = zonesetting;
		this.sizetype = sizetype;
		this.zonesize = zonesize;
		this.active = active;
		this.showtype = showtype;
		this.creattime = creattime;
		this.updatetime = updatetime;
		this.empid = empid;
	}

	/** full constructor */
	public Adzone(String zonename, String zoneintro, Long zonetype, Long defaultsetting, String zonesetting,
                  String sizetype, String zonesize, String zonewidth, String zoneheight, Long active, Long showtype,
                  String creattime, String updatetime, String empid) {
		this.zonename = zonename;
		this.zoneintro = zoneintro;
		this.zonetype = zonetype;
		this.defaultsetting = defaultsetting;
		this.zonesetting = zonesetting;
		this.sizetype = sizetype;
		this.zonesize = zonesize;
		this.zonewidth = zonewidth;
		this.zoneheight = zoneheight;
		this.active = active;
		this.showtype = showtype;
		this.creattime = creattime;
		this.updatetime = updatetime;
		this.empid = empid;
	}

	// Property accessors


	public Long getZoneid() {
		return this.zoneid;
	}

	public void setZoneid(Long zoneid) {
		this.zoneid = zoneid;
	}

	public String getZonename() {
		return this.zonename;
	}

	public void setZonename(String zonename) {
		this.zonename = zonename;
	}

	public String getZoneintro() {
		return this.zoneintro;
	}

	public void setZoneintro(String zoneintro) {
		this.zoneintro = zoneintro;
	}

	public Long getZonetype() {
		return this.zonetype;
	}

	public void setZonetype(Long zonetype) {
		this.zonetype = zonetype;
	}

	public Long getDefaultsetting() {
		return this.defaultsetting;
	}

	public void setDefaultsetting(Long defaultsetting) {
		this.defaultsetting = defaultsetting;
	}

	public String getZonesetting() {
		return this.zonesetting;
	}

	public void setZonesetting(String zonesetting) {
		this.zonesetting = zonesetting;
	}

	public String getSizetype() {
		return this.sizetype;
	}

	public void setSizetype(String sizetype) {
		this.sizetype = sizetype;
	}

	public String getZonesize() {
		return this.zonesize;
	}

	public void setZonesize(String zonesize) {
		this.zonesize = zonesize;
	}

	public String getZonewidth() {
		return this.zonewidth;
	}

	public void setZonewidth(String zonewidth) {
		this.zonewidth = zonewidth;
	}

	public String getZoneheight() {
		return this.zoneheight;
	}

	public void setZoneheight(String zoneheight) {
		this.zoneheight = zoneheight;
	}

	public Long getActive() {
		return this.active;
	}

	public void setActive(Long active) {
		this.active = active;
	}

	public Long getShowtype() {
		return this.showtype;
	}

	public void setShowtype(Long showtype) {
		this.showtype = showtype;
	}

	public String getCreattime() {
		return this.creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

}