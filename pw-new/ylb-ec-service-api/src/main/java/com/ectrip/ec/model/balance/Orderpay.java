package com.ectrip.ec.model.balance;

/**
 * Orderpay entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Orderpay implements java.io.Serializable {

	// Fields

	private String orid;  //订单号码
	private String orda;  //订单日期
	private String orti;  //订单时间
	private String usid;  //游客编号
	private Double mont;  //订单金额
	private String strmont; //订单金额格式化串
	public String getStrmont() {
		return strmont;
	}

	public void setStrmont(String strmont) {
		this.strmont = strmont;
	}

	private String ddzt;  //订单状态
	private String bank;  //支付银行
	private String note;  //订单备注
	private String banktime;  //银行返回时间
	private String bankorid;  //银行返回订单号
	private String sclb;  //充值类别
	private String payorid;  //充值支付订单
	private String cpayorid;  //C2C单号.
	private String cname;  //C2C单号
	private String telno;  //C2C单号.

	//非数据库字段
	private String stryfklb;//预付款来源



	public String getStryfklb() {
		return stryfklb;
	}

	public void setStryfklb(String stryfklb) {
		this.stryfklb = stryfklb;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}




	// Constructors

	/** default constructor */
	public Orderpay() {
	}

	/** minimal constructor */
	public Orderpay(String orid, String orda, String orti, String usid,
					Double mont, String ddzt, String sclb) {
		this.orid = orid;
		this.orda = orda;
		this.orti = orti;
		this.usid = usid;
		this.mont = mont;
		this.ddzt = ddzt;
		this.sclb = sclb;
	}

	/** full constructor */
	public Orderpay(String orid, String orda, String orti, String usid,
					Double mont, String ddzt, String bank, String note,
					String banktime, String bankorid, String sclb, String payorid,
					String cpayorid) {
		this.orid = orid;
		this.orda = orda;
		this.orti = orti;
		this.usid = usid;
		this.mont = mont;
		this.ddzt = ddzt;
		this.bank = bank;
		this.note = note;
		this.banktime = banktime;
		this.bankorid = bankorid;
		this.sclb = sclb;
		this.payorid = payorid;
		this.cpayorid = cpayorid;
	}

	// Property accessors

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getOrda() {
		return this.orda;
	}

	public void setOrda(String orda) {
		this.orda = orda;
	}

	public String getOrti() {
		return this.orti;
	}

	public void setOrti(String orti) {
		this.orti = orti;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Double getMont() {
		return this.mont;
	}

	public void setMont(Double mont) {
		this.mont = mont;
	}

	public String getDdzt() {
		return this.ddzt;
	}

	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getBanktime() {
		return this.banktime;
	}

	public void setBanktime(String banktime) {
		this.banktime = banktime;
	}

	public String getBankorid() {
		return this.bankorid;
	}

	public void setBankorid(String bankorid) {
		this.bankorid = bankorid;
	}

	public String getSclb() {
		return this.sclb;
	}

	public void setSclb(String sclb) {
		this.sclb = sclb;
	}

	public String getPayorid() {
		return this.payorid;
	}

	public void setPayorid(String payorid) {
		this.payorid = payorid;
	}

	public String getCpayorid() {
		return this.cpayorid;
	}

	public void setCpayorid(String cpayorid) {
		this.cpayorid = cpayorid;
	}

}