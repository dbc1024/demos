package com.ectrip.ticket.model.permitenter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 检票设置表
 * 2017-07-26 王小华新增分时预约字段  issettimeticket  istimeticket
 * @author fengjie
 *
 */
@Entity
@Table
public class Opwwicketsettab implements java.io.Serializable {

	// Fields

	private static final String Opwwicketsettab = "Opwwicketsettab";

	@Id
	private Long iwicketsetid;                       //检票设置ID
	private Long itickettypeid;						//产品ID
	private Long izticktypeid;						//子产品ID
	private Long igardengateid;						//圆门ID
	
	@Column(name="iscenicid")
	private Long iscentcid;							//服务商ID
	private Long ilimittotaltimes;					//限制总通过次数
	private Double mlimitconsume;					//限制总消费金额
	private Long msingletimes;						//单次消费次数
	private Double msingleconsume;					//检票前置条件
	private String byconsumemode;					//消费方式
	private Long itimeinterval;						//检票间隔时间(秒)
	private String bywicketctrltype;				//检票控制方式
	private String bywicketconsumetype;				//检票通行方式 01:一检 一人 02:一检多人 03:一单一检 04：一次扣完  5:落杆检票
	private String byregfingerprinttype;			//身份识别类型 00:无 05：指纹识别+人像抓拍
	private String byticketnodeal;					//票号处理
	private Long byisuse;							//使用状态
	private Long issettimeticket;					//是否启用分时检票:0否，1是
	private Long istimeticket;						//分时预约检票设置：0为默认值，1检票时间不可提前不可延后，2检票时间可提前但不能延后，3检票时间不可提前但可延后，4检票时间可提前可延后

	//非数据库字段
	@Transient
	private String szgardengatename;  			//园门名称
	@Transient
	private String sztickettypename;			//产品名称
	@Transient
	private String szscenicname;				//服务商名称
	@Transient
	private String szname;						//显示子产品名称

	// Constructors

	/** default constructor */
	public Opwwicketsettab() {
	}

	/** full constructor */
	public Opwwicketsettab(Long iwicketsetid,
						   Long itickettypeid,Long izticktypeid,
						   Long igardengateid,Long iscentcid, Long ilimittotaltimes,
						   Double mlimitconsume, Long msingletimes, Double msingleconsume,
						   String byconsumemode, Long itimeinterval, String bywicketctrltype,
						   String bywicketconsumetype, String byregfingerprinttype,
						   String byticketnodeal, Long byisuse,Long istimeticket,Long issettimeticket) {
		this.iwicketsetid = iwicketsetid;
		this.itickettypeid = itickettypeid;
		this.izticktypeid=izticktypeid;
		this.igardengateid = igardengateid;
		this.iscentcid=iscentcid;
		this.ilimittotaltimes = ilimittotaltimes;
		this.mlimitconsume = mlimitconsume;
		this.msingletimes = msingletimes;
		this.msingleconsume = msingleconsume;
		this.byconsumemode = byconsumemode;
		this.itimeinterval = itimeinterval;
		this.bywicketctrltype = bywicketctrltype;
		this.bywicketconsumetype = bywicketconsumetype;
		this.byregfingerprinttype = byregfingerprinttype;
		this.byticketnodeal = byticketnodeal;
		this.byisuse = byisuse;
		this.istimeticket=istimeticket;
		this.issettimeticket=issettimeticket;
	}

	// Property accessors

	public Long getIwicketsetid() {
		return this.iwicketsetid;
	}

	public void setIwicketsetid(Long iwicketsetid) {
		this.iwicketsetid = iwicketsetid;
	}

	public Long getIlimittotaltimes() {
		return ilimittotaltimes;
	}

	public void setIlimittotaltimes(Long ilimittotaltimes) {
		this.ilimittotaltimes = ilimittotaltimes;
	}

	public Double getMlimitconsume() {
		return this.mlimitconsume;
	}

	public void setMlimitconsume(Double mlimitconsume) {
		this.mlimitconsume = mlimitconsume;
	}

	public Long getMsingletimes() {
		return this.msingletimes;
	}

	public void setMsingletimes(Long msingletimes) {
		this.msingletimes = msingletimes;
	}

	public Double getMsingleconsume() {
		return this.msingleconsume;
	}

	public void setMsingleconsume(Double msingleconsume) {
		this.msingleconsume = msingleconsume;
	}

	public String getByconsumemode() {
		return this.byconsumemode;
	}

	public void setByconsumemode(String byconsumemode) {
		this.byconsumemode = byconsumemode;
	}

	public Long getItimeinterval() {
		return this.itimeinterval;
	}

	public void setItimeinterval(Long itimeinterval) {
		this.itimeinterval = itimeinterval;
	}

	public String getBywicketctrltype() {
		return this.bywicketctrltype;
	}

	public void setBywicketctrltype(String bywicketctrltype) {
		this.bywicketctrltype = bywicketctrltype;
	}

	public String getBywicketconsumetype() {
		return this.bywicketconsumetype;
	}

	public void setBywicketconsumetype(String bywicketconsumetype) {
		this.bywicketconsumetype = bywicketconsumetype;
	}

	public String getByregfingerprinttype() {
		return this.byregfingerprinttype;
	}

	public void setByregfingerprinttype(String byregfingerprinttype) {
		this.byregfingerprinttype = byregfingerprinttype;
	}

	public String getByticketnodeal() {
		return this.byticketnodeal;
	}

	public void setByticketnodeal(String byticketnodeal) {
		this.byticketnodeal = byticketnodeal;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public Long getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIgardengateid() {
		return igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getIscentcid() {
		return iscentcid;
	}

	public void setIscentcid(Long iscentcid) {
		this.iscentcid = iscentcid;
	}

	public String getSzgardengatename() {
		return szgardengatename;
	}

	public void setSzgardengatename(String szgardengatename) {
		this.szgardengatename = szgardengatename;
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

	public Long getIzticktypeid() {
		return izticktypeid;
	}

	public void setIzticktypeid(Long izticktypeid) {
		this.izticktypeid = izticktypeid;
	}

	public String getSzname() {
		return szname;
	}

	public void setSzname(String szname) {
		this.szname = szname;
	}

	public Long getIstimeticket() {
		return istimeticket;
	}

	public void setIstimeticket(Long istimeticket) {
		this.istimeticket = istimeticket;
	}

	public Long getIssettimeticket() {
		return issettimeticket;
	}

	public void setIssettimeticket(Long issettimeticket) {
		this.issettimeticket = issettimeticket;
	}

}