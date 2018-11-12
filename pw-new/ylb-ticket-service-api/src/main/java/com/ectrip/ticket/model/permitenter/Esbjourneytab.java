package com.ectrip.ticket.model.permitenter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Esbjourneytab entity. @author MyEclipse Persistence Tools
 */

public class Esbjourneytab implements java.io.Serializable {

	// Fields

	private Long seq;//行程编号
	private String orid;//订单号
	private Long isceniaid;//起始点
	private Long iscenibid;//结束点
	private String isatime;//起始时间
	private String isbtime;//结束时间
	private Long numb;//人数
	private Long isby;//添加次序
	private Long isatype;//类型标志（1，入口，2，景点，3 码头）
	private Long isbtype;//类型标志（1，入口，2，景点，3 码头）

	//非数据库字段
	private String szsceinaname;//起始点名称
	private String szsceinbname;//结束点名称
	private String isa;//起始小时
	private String isb;//结束小时
	private String isadate;//起始分
	private String isbdate;//结束分
	private List triptopList;
	private List tripallList;

	// Constructors

	/** default constructor */
	public Esbjourneytab() {
	}

	/** minimal constructor */
	public Esbjourneytab(Long seq, String orid, Long isceniaid,
						 String isatime, Long numb) {
		this.seq = seq;
		this.orid = orid;
		this.isceniaid = isceniaid;
		this.isatime = isatime;
		this.numb = numb;
	}

	/** full constructor */
	public Esbjourneytab(Long seq, String orid, Long isceniaid,
						 Long iscenibid, String isatime, String isbtime,
						 Long numb,Long isby,Long isatype,Long isbtype) {
		this.seq = seq;
		this.orid = orid;
		this.isceniaid = isceniaid;
		this.iscenibid = iscenibid;
		this.isatime = isatime;
		this.isbtime = isbtime;
		this.numb = numb;
		this.isby = isby;
		this.isatype = isatype;
		this.isbtype = isbtype;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public Long getIsceniaid() {
		return this.isceniaid;
	}

	public void setIsceniaid(Long isceniaid) {
		this.isceniaid = isceniaid;
	}

	public Long getIscenibid() {
		return this.iscenibid;
	}

	public void setIscenibid(Long iscenibid) {
		this.iscenibid = iscenibid;
	}

	public String getIsatime() {
		return this.isatime;
	}

	public void setIsatime(String isatime) {
		this.isatime = isatime;
	}

	public String getIsbtime() {
		return this.isbtime;
	}

	public void setIsbtime(String isbtime) {
		this.isbtime = isbtime;
	}

	public Long getNumb() {
		return this.numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public String getSzsceinaname() {
		return szsceinaname;
	}

	public void setSzsceinaname(String szsceinaname) {
		this.szsceinaname = szsceinaname;
	}

	public String getSzsceinbname() {
		return szsceinbname;
	}

	public void setSzsceinbname(String szsceinbname) {
		this.szsceinbname = szsceinbname;
	}

	public String getIsa() {
		return isa;
	}

	public void setIsa(String isa) {
		this.isa = isa;
	}

	public String getIsb() {
		return isb;
	}

	public void setIsb(String isb) {
		this.isb = isb;
	}

	public String getIsadate() {
		return isadate;
	}

	public void setIsadate(String isadate) {
		this.isadate = isadate;
	}

	public String getIsbdate() {
		return isbdate;
	}

	public void setIsbdate(String isbdate) {
		this.isbdate = isbdate;
	}

	public Long getIsby() {
		return isby;
	}

	public void setIsby(Long isby) {
		this.isby = isby;
	}

	public Long getIsatype() {
		return isatype;
	}

	public void setIsatype(Long isatype) {
		this.isatype = isatype;
	}

	public Long getIsbtype() {
		return isbtype;
	}

	public void setIsbtype(Long isbtype) {
		this.isbtype = isbtype;
	}

	public List getTriptopList() {
		return triptopList;
	}

	public void setTriptopList(List triptopList) {
		this.triptopList = triptopList;
	}

	public List getTripallList() {
		return tripallList;
	}

	public void setTripallList(List tripallList) {
		this.tripallList = tripallList;
	}

}