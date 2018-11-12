package com.ectrip.sys.model.syspar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Orderlog implements java.io.Serializable {

	// Fields
	@Id
	private Long logid;//主键
	private String orid;//订单号
	private String stlg;//操作标识
	//0156 新增订单  0157 修改订单  0158 删除订单  //0159 门票重打印  0160订单出票
	private String brief;//简要说明
	private Long logtype; //0 前台 1 后台  2 出票口
	private String usid;//操作人
	private Long iemployeeid;//后台操作人
	private String logdatetime;//时间
	private String note;//详细说明
	
	//非数据表字段
	@Transient
	private String auname;			
	@Transient
	private String szemployeename;   
   
	// Constructors

	/** default constructor */
	public Orderlog() {
	}

	/** minimal constructor */
	public Orderlog(Long logid, String orid, String stlg, String brief,
			Long logtype) {
		this.logid = logid;
		this.orid = orid;
		this.stlg = stlg;
		this.brief = brief;
		this.logtype = logtype;
	}

	/** full constructor */
	public Orderlog(Long logid, String orid, String stlg, String brief,
			Long logtype, String usid, Long iemployeeid, String logdatetime,
			String note) {
		this.logid = logid;
		this.orid = orid;;
		this.stlg = stlg;
		this.brief = brief;
		this.logtype = logtype;
		this.usid = usid;
		this.iemployeeid = iemployeeid;
		this.logdatetime = logdatetime;
		this.note = note;
	}

	// Property accessors

	public Long getLogid() {
		return this.logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getStlg() {
		return this.stlg;
	}

	public void setStlg(String stlg) {
		this.stlg = stlg;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Long getLogtype() {
		return this.logtype;
	}

	public void setLogtype(Long logtype) {
		this.logtype = logtype;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getLogdatetime() {
		return this.logdatetime;
	}

	public void setLogdatetime(String logdatetime) {
		this.logdatetime = logdatetime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAuname() {
		return auname;
	}

	public void setAuname(String auname) {
		this.auname = auname;
	}

	public String getSzemployeename() {
		return szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
	}

}
