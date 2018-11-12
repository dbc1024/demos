package com.ectrip.ticket.model.stock;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Stockdetailtab entity. @author MyEclipse Persistence Tools
 */
@Table
@Entity
public class Stockdetailtab implements java.io.Serializable {

	// Fields
	@Id
	private Long seq;//主键
	private String orid;//订单号
	private Long providerid;//服务商id
	private Long productid;//产品id
	private Long priceid;//价格id
	private String usid;//用户id 旅行社保存分社，接待保存总社
	private Long numb;//数量
	private String consumedate;//消费日期
	private String dtmakedate;//创建日期
	private String jsondata;//其他属性
	private Long timeId;

	// Constructors

	/** default constructor */
	public Stockdetailtab() {
	}

	/** full constructor */
	public Stockdetailtab(String orid, Long providerid,
						  Long productid, Long priceid, String usid,
						  Long numb, String consumedate, String dtmakedate,
						  String jsondata) {
		this.orid = orid;
		this.providerid = providerid;
		this.productid = productid;
		this.priceid = priceid;
		this.usid = usid;
		this.numb = numb;
		this.consumedate = consumedate;
		this.dtmakedate = dtmakedate;
		this.jsondata = jsondata;
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

	public Long getProviderid() {
		return this.providerid;
	}

	public void setProviderid(Long providerid) {
		this.providerid = providerid;
	}

	public Long getProductid() {
		return this.productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public Long getPriceid() {
		return this.priceid;
	}

	public void setPriceid(Long priceid) {
		this.priceid = priceid;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getNumb() {
		return this.numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public String getConsumedate() {
		return this.consumedate;
	}

	public void setConsumedate(String consumedate) {
		this.consumedate = consumedate;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getJsondata() {
		return this.jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}
}