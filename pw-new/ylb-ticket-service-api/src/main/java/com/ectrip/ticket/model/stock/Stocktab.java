package com.ectrip.ticket.model.stock;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Stocktab entity. @author MyEclipse Persistence Tools
 */
@Table
@Entity
public class Stocktab implements java.io.Serializable {

	// Fields
	@Id
	private Long seq;//主键
	private String stocktype;//库存类型  01:服务商  02:产品  03:价格  04:用户
	private String id;//绑定类型id
	private String startdate;//开始日期
	private String enddate;//结束日期
	private Long stocknumb;//库存数量
	private String dateType;//日期类型  01:日期库存  02:区间库存
	private String jsondata;//其他属性
	private String dtmakedate;//创建日期
	private Long iemployeeid;//创建者
	private String saletype;//销售类型(备用)
	private String customStockType;//用户库存类型 01:服务商库存 02:产品库存
	private String customStockId;//customStockId:对应服务商ID或者产品ID
	// Constructors

	/** default constructor */
	public Stocktab() {
	}
	@Transient
	private String keyname;





	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	/** full constructor */
	public Stocktab(String stocktype, String id, String startdate,
					String enddate, Long stocknumb, String jsondata,
					String dtmakedate, Long iemployeeid, String saletype) {
		this.stocktype = stocktype;
		this.id = id;
		this.startdate = startdate;
		this.enddate = enddate;
		this.stocknumb = stocknumb;
		this.jsondata = jsondata;
		this.dtmakedate = dtmakedate;
		this.iemployeeid = iemployeeid;
		this.saletype = saletype;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getStocktype() {
		return this.stocktype;
	}

	public void setStocktype(String stocktype) {
		this.stocktype = stocktype;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Long getStocknumb() {
		return this.stocknumb;
	}

	public void setStocknumb(Long stocknumb) {
		this.stocknumb = stocknumb;
	}

	public String getJsondata() {
		return this.jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getSaletype() {
		return this.saletype;
	}

	public void setSaletype(String saletype) {
		this.saletype = saletype;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getCustomStockType() {
		return customStockType;
	}

	public void setCustomStockType(String customStockType) {
		this.customStockType = customStockType;
	}

	public String getCustomStockId() {
		return customStockId;
	}

	public void setCustomStockId(String customStockId) {
		this.customStockId = customStockId;
	}
}