package com.ectrip.ticket.model.provider;

import java.sql.Blob;
import java.util.List;
import java.util.Map;



/**
 * 线路附属属性
 * Lineproduct entity. @author MyEclipse Persistence Tools
 */

public class Lineproduct  implements java.io.Serializable {

	/**
	 * Lineproduct.java
	 * liujianwen
	 * 20122012-6-29
	 */
	private static final long serialVersionUID = 1L;
	private Long itickettypeid;//id
	private Long datanumber;//天数
	private String departure;//出发地，外键
	private String destination;//目的地,外键
	private String startingmethod;//出发方式
	private String startingdata;//出发日期
	private Long deposittype;//预定方式（复选框）(支付方式 0全额1定金  )
	private Double deposit;//定金
	private String dtmakedate;//操作时间
	private Long daynum;//提前预定天数
	private String status;//审核状态(付款减库存 1否 0是)
	private String prdstatus;//线路属性
	private String linenota;//费用包含(改推荐路线  0 否 1是)
	private String linenotb;//费用不包含（标题）
	private String linemmqt;//可选服务 ( 0否  1是  改订单是否允许修改 2014年2月26日 10:19:55)
	private String desnomn;//预定须知
	private String audiorum;//温馨提示(改费用说明2014-01-13 14:04:59)
	private Blob note;//线路简介(改行程描述2014-01-13 14:05:16)

	///////////////////////////
	//非数据库的字段
	private String strdeparture;//出发地名称
	private String strdestination;//目的地名称
	private String strstarting;//出发方式名称
	private Long pinglunshu;//评论数
	private String pingfen;//评分

	public Long getPinglunshu() {
		return pinglunshu;
	}




	public void setPinglunshu(Long pinglunshu) {
		this.pinglunshu = pinglunshu;
	}




	public String getPingfen() {
		return pingfen;
	}




	public void setPingfen(String pingfen) {
		this.pingfen = pingfen;
	}




	private List<Linetravel> linetravles;//行程
	private List<Edmcrowdkindpricetab> edmcrowdkindpricetabs;//价格
	private Map<String,String> datePrices;//界面显示时封装的时间对应的价格
	private Esbscenicareatab provider;//服务商



	// Constructors

	/** default constructor */
	public Lineproduct() {
	}




	// Property accessors

	public Map<String,String> getDatePrices() {
		return datePrices;
	}




	public void setDatePrices(Map<String,String> datePrices) {
		this.datePrices = datePrices;
	}




	public List<Edmcrowdkindpricetab> getEdmcrowdkindpricetabs() {
		return edmcrowdkindpricetabs;
	}




	public void setEdmcrowdkindpricetabs(
			List<Edmcrowdkindpricetab> edmcrowdkindpricetabs) {
		this.edmcrowdkindpricetabs = edmcrowdkindpricetabs;
	}




	public List<Linetravel> getLinetravles() {
		return linetravles;
	}




	public void setLinetravles(List<Linetravel> linetravles) {
		this.linetravles = linetravles;
	}




	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getDatanumber() {
		return this.datanumber;
	}

	public void setDatanumber(Long datanumber) {
		this.datanumber = datanumber;
	}




	public Esbscenicareatab getProvider() {
		return provider;
	}




	public void setProvider(Esbscenicareatab provider) {
		this.provider = provider;
	}




	public String getDeparture() {
		return departure;
	}




	public void setDeparture(String departure) {
		this.departure = departure;
	}




	public String getDestination() {
		return destination;
	}




	public void setDestination(String destination) {
		this.destination = destination;
	}




	public String getStartingmethod() {
		return this.startingmethod;
	}

	public void setStartingmethod(String startingmethod) {
		this.startingmethod = startingmethod;
	}

	public String getStartingdata() {
		return this.startingdata;
	}

	public void setStartingdata(String startingdata) {
		this.startingdata = startingdata;
	}

	public Long getDeposittype() {
		return this.deposittype;
	}

	public void setDeposittype(Long deposittype) {
		this.deposittype = deposittype;
	}

	public Double getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getDaynum() {
		return this.daynum;
	}

	public void setDaynum(Long daynum) {
		this.daynum = daynum;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrdstatus() {
		return this.prdstatus;
	}

	public void setPrdstatus(String prdstatus) {
		this.prdstatus = prdstatus;
	}

	public String getLinenota() {
		return this.linenota;
	}

	public void setLinenota(String linenota) {
		this.linenota = linenota;
	}

	public String getLinenotb() {
		return this.linenotb;
	}

	public void setLinenotb(String linenotb) {
		this.linenotb = linenotb;
	}

	public String getLinemmqt() {
		return this.linemmqt;
	}

	public void setLinemmqt(String linemmqt) {
		this.linemmqt = linemmqt;
	}

	public String getDesnomn() {
		return this.desnomn;
	}

	public void setDesnomn(String desnomn) {
		this.desnomn = desnomn;
	}

	public String getAudiorum() {
		return this.audiorum;
	}

	public void setAudiorum(String audiorum) {
		this.audiorum = audiorum;
	}


	public Blob getNote() {
		return note;
	}

	public void setNote(Blob note) {
		this.note = note;
	}

	public String getStrdeparture() {
		return strdeparture;
	}

	public void setStrdeparture(String strdeparture) {
		this.strdeparture = strdeparture;
	}

	public String getStrdestination() {
		return strdestination;
	}

	public void setStrdestination(String strdestination) {
		this.strdestination = strdestination;
	}

	public String getStrstarting() {
		return strstarting;
	}

	public void setStrstarting(String strstarting) {
		this.strstarting = strstarting;
	}

}