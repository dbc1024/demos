package com.ectrip.ticket.model.permitenter;

/**
 * Upsalesetlement entity. @author MyEclipse Persistence Tools
 */

public class Upsalesetlement implements java.io.Serializable {

	// Fields

	private Long seq;
	private Long iemployeeid;  //售票员ID
	private String mentdata; //售票日期
	private String szstartcode; //起始票号
	private String szendcode; //截止票号
	private Long mont; //数量
	private Double money; //金额
	private String yzffs; //原结算方式
	private String uzffs; //修改后结算方式
	private String dtmakedate;
	private Long ida;   //修改数量
	private Long idb;   //操作人ID
	private Long idc;
	private String isa;
	private String isb;
	private String isc;

	// Constructors

	/** default constructor */
	public Upsalesetlement() {
	}

	/** minimal constructor */
	public Upsalesetlement(Long seq, Long iemployeeid,
						   String mentdata, String szstartcode, String szendcode,
						   Long mont, Double money, String yzffs, String uzffs) {
		this.seq = seq;
		this.iemployeeid = iemployeeid;
		this.mentdata = mentdata;
		this.szstartcode = szstartcode;
		this.szendcode = szendcode;
		this.mont = mont;
		this.money = money;
		this.yzffs = yzffs;
		this.uzffs = uzffs;
	}

	/** full constructor */
	public Upsalesetlement(Long seq, Long iemployeeid,
						   String mentdata, String szstartcode, String szendcode,
						   Long mont, Double money, String yzffs, String uzffs,
						   String dtmakedate, Long ida, Long idb, Long idc,
						   String isa, String isb, String isc) {
		this.seq = seq;
		this.iemployeeid = iemployeeid;
		this.mentdata = mentdata;
		this.szstartcode = szstartcode;
		this.szendcode = szendcode;
		this.mont = mont;
		this.money = money;
		this.yzffs = yzffs;
		this.uzffs = uzffs;
		this.dtmakedate = dtmakedate;
		this.ida = ida;
		this.idb = idb;
		this.idc = idc;
		this.isa = isa;
		this.isb = isb;
		this.isc = isc;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getMentdata() {
		return this.mentdata;
	}

	public void setMentdata(String mentdata) {
		this.mentdata = mentdata;
	}

	public String getSzstartcode() {
		return this.szstartcode;
	}

	public void setSzstartcode(String szstartcode) {
		this.szstartcode = szstartcode;
	}

	public String getSzendcode() {
		return this.szendcode;
	}

	public void setSzendcode(String szendcode) {
		this.szendcode = szendcode;
	}

	public Long getMont() {
		return this.mont;
	}

	public void setMont(Long mont) {
		this.mont = mont;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getYzffs() {
		return this.yzffs;
	}

	public void setYzffs(String yzffs) {
		this.yzffs = yzffs;
	}

	public String getUzffs() {
		return this.uzffs;
	}

	public void setUzffs(String uzffs) {
		this.uzffs = uzffs;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getIda() {
		return this.ida;
	}

	public void setIda(Long ida) {
		this.ida = ida;
	}

	public Long getIdb() {
		return this.idb;
	}

	public void setIdb(Long idb) {
		this.idb = idb;
	}

	public Long getIdc() {
		return this.idc;
	}

	public void setIdc(Long idc) {
		this.idc = idc;
	}

	public String getIsa() {
		return this.isa;
	}

	public void setIsa(String isa) {
		this.isa = isa;
	}

	public String getIsb() {
		return this.isb;
	}

	public void setIsb(String isb) {
		this.isb = isb;
	}

	public String getIsc() {
		return this.isc;
	}

	public void setIsc(String isc) {
		this.isc = isc;
	}

}