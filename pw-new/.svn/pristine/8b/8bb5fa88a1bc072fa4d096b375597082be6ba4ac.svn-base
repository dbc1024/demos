package com.ectrip.ticket.model.provider;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Ticketxgz entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Ticketxgz implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long gzid;              //规则iD
	private Long iscenicid;			//服务商ID
	private Long itickettypeid;		//产品ID
	private String gzname;			//规则名称
	private String gztype;			//规则类别  整团退票 部分退票
	private String lgtp;			//用户类型  散客 旅行社
	private String jsfs;			//计算方式  按小时  按天  常年
	private String jstp;			//计算种类  按照服务商  按照产品  若按照服务商 产品为空
	private Long isvalid;			//使用状态 
	private Double xyjs;			//信用基数 
	private Double xyrate;			//信用率
	private Long xyjs2;				//过期票是否允许出票口直接退票  0-- 不能   1 --能       
	private Double xyrate2;			//过期票退票费率
	private Long xyjs3;				//信用基数 3
	private Double xyrate3;			//信用率 3
	private Long xyjs4;				//信用基数 4
	private Double xyrate4;			//信用率 4
	private String sznote;			//备注
	private String sznote2;			//备注 2
	private String sznote3;			//备注 3
	private String sznote4;			//备注 4
	private String sznote5;			//备注 5
	
	//非数据字段
	@Transient
	private String szscenicname;    //服务商名称
	@Transient
	private String sztickettypename; //产品名称
	@Transient
	private List<Changebackrate> changebackrate;  //退票费率

	// Constructors

	/** default constructor */
	public Ticketxgz() {
	}

	/** minimal constructor */
	public Ticketxgz(Long gzid, Long itickettypeid) {
		this.gzid = gzid;
		this.itickettypeid = itickettypeid;
	}

	/** full constructor */
	public Ticketxgz(Long gzid, Long iscenicid, Long itickettypeid,
			String gzname, String gztype, String lgtp, String jsfs,
			String jstp, Long isvalid, Double xyjs, Double xyrate, Long xyjs2,
			Double xyrate2, Long xyjs3, Double xyrate3, Long xyjs4,
			Double xyrate4, String sznote, String sznote2, String sznote3,
			String sznote4, String sznote5) {
		this.gzid = gzid;
		this.iscenicid = iscenicid;
		this.itickettypeid = itickettypeid;
		this.gzname = gzname;
		this.gztype = gztype;
		this.lgtp = lgtp;
		this.jsfs = jsfs;
		this.jstp = jstp;
		this.isvalid = isvalid;
		this.xyjs = xyjs;
		this.xyrate = xyrate;
		this.xyjs2 = xyjs2;
		this.xyrate2 = xyrate2;
		this.xyjs3 = xyjs3;
		this.xyrate3 = xyrate3;
		this.xyjs4 = xyjs4;
		this.xyrate4 = xyrate4;
		this.sznote = sznote;
		this.sznote2 = sznote2;
		this.sznote3 = sznote3;
		this.sznote4 = sznote4;
		this.sznote5 = sznote5;
	}

	// Property accessors

	public Long getGzid() {
		return this.gzid;
	}

	public void setGzid(Long gzid) {
		this.gzid = gzid;
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

	public String getGzname() {
		return this.gzname;
	}

	public void setGzname(String gzname) {
		this.gzname = gzname;
	}

	public String getGztype() {
		return this.gztype;
	}

	public void setGztype(String gztype) {
		this.gztype = gztype;
	}

	public String getLgtp() {
		return this.lgtp;
	}

	public void setLgtp(String lgtp) {
		this.lgtp = lgtp;
	}

	public String getJsfs() {
		return this.jsfs;
	}

	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}

	public String getJstp() {
		return this.jstp;
	}

	public void setJstp(String jstp) {
		this.jstp = jstp;
	}

	public Long getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Long isvalid) {
		this.isvalid = isvalid;
	}

	public Double getXyjs() {
		return xyjs;
	}

	public void setXyjs(Double xyjs) {
		this.xyjs = xyjs;
	}

	public Double getXyrate() {
		return this.xyrate;
	}

	public void setXyrate(Double xyrate) {
		this.xyrate = xyrate;
	}

	public Long getXyjs2() {
		return this.xyjs2;
	}

	public void setXyjs2(Long xyjs2) {
		this.xyjs2 = xyjs2;
	}

	public Double getXyrate2() {
		return this.xyrate2;
	}

	public void setXyrate2(Double xyrate2) {
		this.xyrate2 = xyrate2;
	}

	public Long getXyjs3() {
		return this.xyjs3;
	}

	public void setXyjs3(Long xyjs3) {
		this.xyjs3 = xyjs3;
	}

	public Double getXyrate3() {
		return this.xyrate3;
	}

	public void setXyrate3(Double xyrate3) {
		this.xyrate3 = xyrate3;
	}

	public Long getXyjs4() {
		return this.xyjs4;
	}

	public void setXyjs4(Long xyjs4) {
		this.xyjs4 = xyjs4;
	}

	public Double getXyrate4() {
		return this.xyrate4;
	}

	public void setXyrate4(Double xyrate4) {
		this.xyrate4 = xyrate4;
	}

	public String getSznote() {
		return this.sznote;
	}

	public void setSznote(String sznote) {
		this.sznote = sznote;
	}

	public String getSznote2() {
		return this.sznote2;
	}

	public void setSznote2(String sznote2) {
		this.sznote2 = sznote2;
	}

	public String getSznote3() {
		return this.sznote3;
	}

	public void setSznote3(String sznote3) {
		this.sznote3 = sznote3;
	}

	public String getSznote4() {
		return this.sznote4;
	}

	public void setSznote4(String sznote4) {
		this.sznote4 = sznote4;
	}

	public String getSznote5() {
		return this.sznote5;
	}

	public void setSznote5(String sznote5) {
		this.sznote5 = sznote5;
	}

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public List<Changebackrate> getChangebackrate() {
		return changebackrate;
	}

	public void setChangebackrate(List<Changebackrate> changebackrate) {
		this.changebackrate = changebackrate;
	}

}