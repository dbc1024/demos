package com.ectrip.ticket.model.salesmanager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Ospticketwinlimitstab entity. @author MyEclipse Persistence Tools 窗口限制
 */
@Entity
@Table
public class Ospticketwinlimitstab implements java.io.Serializable {

	// Fields
	@Id
	private Long iticketwinlimitsid;// 窗口限制id
	private Long iticketwinid;// 售票窗口id
	private Long icrowdkindpriceid;// 售价id
	//非表字段
	@Transient
	private String strticketwin;// 售票窗口名称
	@Transient
	private String strcrowdkindprice;// 售价名称
	@Transient
	private Long[] icrowdkindpriceids;// 价格数组
	@Transient
	private boolean icrowkindpriceTemp;// 价格选中标识
	// Constructors
	@Transient
	private Long[] iwinids;// 售票窗口数组
	@Transient
	private boolean iwinTemp;// 售票窗口选中标识

	public Long[] getIwinids() {
		return iwinids;
	}

	public void setIwinids(Long[] iwinids) {
		this.iwinids = iwinids;
	}

	public boolean isIwinTemp() {
		return iwinTemp;
	}

	public void setIwinTemp(boolean iwinTemp) {
		this.iwinTemp = iwinTemp;
	}

	/** default constructor */
	public Ospticketwinlimitstab() {
	}

	/** full constructor */
	public Ospticketwinlimitstab(Long iticketwinlimitsid, Long iticketwinid, Long icrowdkindpriceid) {
		this.iticketwinlimitsid = iticketwinlimitsid;
		this.iticketwinid = iticketwinid;
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	// Property accessors

	public Long getIticketwinlimitsid() {
		return this.iticketwinlimitsid;
	}

	public void setIticketwinlimitsid(Long iticketwinlimitsid) {
		this.iticketwinlimitsid = iticketwinlimitsid;
	}

	public Long getIticketwinid() {
		return this.iticketwinid;
	}

	public void setIticketwinid(Long iticketwinid) {
		this.iticketwinid = iticketwinid;
	}

	public Long getIcrowdkindpriceid() {
		return this.icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public String getStrticketwin() {
		return strticketwin;
	}

	public void setStrticketwin(String strticketwin) {
		this.strticketwin = strticketwin;
	}

	public String getStrcrowdkindprice() {
		return strcrowdkindprice;
	}

	public void setStrcrowdkindprice(String strcrowdkindprice) {
		this.strcrowdkindprice = strcrowdkindprice;
	}

	public Long[] getIcrowdkindpriceids() {
		return icrowdkindpriceids;
	}

	public void setIcrowdkindpriceids(Long[] icrowdkindpriceids) {
		this.icrowdkindpriceids = icrowdkindpriceids;
	}

	public boolean isIcrowkindpriceTemp() {
		return icrowkindpriceTemp;
	}

	public void setIcrowkindpriceTemp(boolean icrowkindpriceTemp) {
		this.icrowkindpriceTemp = icrowkindpriceTemp;
	}

}