package com.ectrip.ticket.model.salesmanager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Ospsaleslimitstab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Ospsaleslimitstab  implements java.io.Serializable {


	// Fields
	@Id
	private Long isaleslimitsid;//销售权限id
	private Long icrowdkindpriceid;//售价
	private Long iemployeeid;//员工

	//非数据库字段
	@Transient
	private String stremployee;//员工名称
	@Transient
	private String strcrowdkindprice;//售价组合名称
	@Transient
	private Long[] icrowdkindpriceids;//价格数组
	@Transient
	private boolean icrowkindpriceTemp;//价格选中标识
	@Transient
	private Long[] iempids;//员工数组
	@Transient
	private boolean iempTemp;//员工选中标识
	// Constructors

	public Long[] getIempids() {
		return iempids;
	}


	public void setIempids(Long[] iempids) {
		this.iempids = iempids;
	}


	public boolean isIempTemp() {
		return iempTemp;
	}


	public void setIempTemp(boolean iempTemp) {
		this.iempTemp = iempTemp;
	}


	/** default constructor */
	public Ospsaleslimitstab() {
	}


	/** full constructor */
	public Ospsaleslimitstab(Long isaleslimitsid, Long icrowdkindpriceid, Long iemployeeid) {
		this.isaleslimitsid = isaleslimitsid;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.iemployeeid = iemployeeid;
	}


	// Property accessors

	public Long getIsaleslimitsid() {
		return this.isaleslimitsid;
	}

	public void setIsaleslimitsid(Long isaleslimitsid) {
		this.isaleslimitsid = isaleslimitsid;
	}
	public Long getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}


	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}


	public Long getIemployeeid() {
		return iemployeeid;
	}


	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}


	public String getStremployee() {
		return stremployee;
	}


	public void setStremployee(String stremployee) {
		this.stremployee = stremployee;
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