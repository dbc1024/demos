package com.ectrip.ticket.model.provider;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Edmticketcomposepricetab entity.
 * 
 * @author MyEclipse Persistence Tools
 * 组合票价格拆分 表
 */

@Entity
public class Edmticketcomposepricetab implements java.io.Serializable {

	// Fields

	@Id
	private EdmticketcomposepricetabId id;//主键
	private Long itickettypeid;//票类型id（产品id）
	private Double mactualsaleprice;//单价
	private Long numb;//数量
	private Double jsprice;//结算价
	private Long zpdno;  //所属服务商Id
	
	//数据库不存在的字段
	@Transient
	private String sztickettypename;//产品名称 
	@Transient
	private String szscenicname;//服务商名称
	@Transient
	private Long[] itickettypeids;// 子产品组合
	@Transient
	private boolean itickettypeidsTemp;//修改标识
	@Transient
	private Long icrowdkindpriceid; //售价id(关联售价表 Edmcrowdkindpricetab) ID 中的字段
	@Transient
	private Long iticketcomposepriceid; //ID中的字段 联合主键


	public boolean isItickettypeidsTemp() {
		return itickettypeidsTemp;
	}

	public void setItickettypeidsTemp(boolean itickettypeidsTemp) {
		this.itickettypeidsTemp = itickettypeidsTemp;
	}

	public Long[] getItickettypeids() {
		return itickettypeids;
	}

	public void setItickettypeids(Long[] itickettypeids) {
		this.itickettypeids = itickettypeids;
	}

	public Edmticketcomposepricetab() {
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

	
	public Long getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public Long getIticketcomposepriceid() {
		return iticketcomposepriceid;
	}

	public void setIticketcomposepriceid(Long iticketcomposepriceid) {
		this.iticketcomposepriceid = iticketcomposepriceid;
	}

	/** full constructor */
	public Edmticketcomposepricetab(EdmticketcomposepricetabId id,
			Long itickettypeid, Double mactualsaleprice, Long numb,Double jsprice) {
		this.id = id;
		this.itickettypeid = itickettypeid;
		this.mactualsaleprice = mactualsaleprice;
		this.numb = numb;
		this.jsprice = jsprice;
	}

	// Property accessors

	public EdmticketcomposepricetabId getId() {
		return this.id;
	}

	public void setId(EdmticketcomposepricetabId id) {
		this.id = id;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Double getMactualsaleprice() {
		return this.mactualsaleprice;
	}

	public void setMactualsaleprice(Double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}

	public Long getNumb() {
		return this.numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public Double getJsprice() {
		return jsprice;
	}

	public void setJsprice(Double jsprice) {
		this.jsprice = jsprice;
	}

	public Long getZpdno() {
		return zpdno;
	}

	public void setZpdno(Long zpdno) {
		this.zpdno = zpdno;
	}

}