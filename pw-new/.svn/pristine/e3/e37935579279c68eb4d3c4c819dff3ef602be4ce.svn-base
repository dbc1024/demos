package com.ectrip.ticket.model.card;


/**
 * Icrechargerecord entity. @author MyEclipse Persistence Tools
 */
/**
 * --售票员营收变动记录表,售票员统计用
 * @author liujianwen
 */
public class Icrechargerecord implements java.io.Serializable {

	// Fields

	/**
	 * Icrechargerecord.java
	 * liujianwen
	 * 20162016-5-4
	 */
	private static final long serialVersionUID = 1L;
	private Long icrechargerecordid;//序列Icrechargerecord_seq
	private Long picrechargerecordid;//如果是撤销操作,存储原id
	private Long piemployeeid;//如果是撤销,存储售票人员id
	private String orid;
	private Long iemployeeid;//售票员id
	private Long iticketwinid;//窗口id
	private String szticketprintno;//外键内部卡号(对应Custom 中USID)
	private String newszticketprintno;//如果是补卡,则填新票号
	private String cardno;//卡号
	private String oldcardno;//如果是补卡,则存旧卡号
	private String oldszticketprintno;//如果是补卡,则存旧内部卡号,弃用
	private String cztp;//操作类型系统参数CZTP(00充值,01补卡,02退卡,05充值撤销)
	private Double amount;//变动金额,
	private Double accpointzs;//如果是充值,则填赠送金额,
	private String zffs;//支付方式
	private String rechargedate;//时间
	private String note;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	private Long inote1;
	private Long inote2;
	private Long inote3;
	private Long inote4;

	// Constructors

	/** default constructor */
	public Icrechargerecord() {
	}

	public Long getPicrechargerecordid() {
		return picrechargerecordid;
	}

	public Long getPiemployeeid() {
		return piemployeeid;
	}

	public void setPiemployeeid(Long piemployeeid) {
		this.piemployeeid = piemployeeid;
	}

	public void setPicrechargerecordid(Long picrechargerecordid) {
		this.picrechargerecordid = picrechargerecordid;
	}

	public String getNote() {
		return note;
	}

	public String getNewszticketprintno() {
		return newszticketprintno;
	}

	public void setNewszticketprintno(String newszticketprintno) {
		this.newszticketprintno = newszticketprintno;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRechargedate() {
		return rechargedate;
	}

	public void setRechargedate(String rechargedate) {
		this.rechargedate = rechargedate;
	}

	/** minimal constructor */


	public String getOrid() {
		return orid;
	}

	public Icrechargerecord(Long icrechargerecordid, String orid,
							Long iemployeeid, Long iticketwinid, String szticketprintno,
							String newszticketprintno, String cardno, String oldcardno,
							String oldszticketprintno, String cztp, Double amount,
							Double accpointzs, String zffs, String rechargedate, String note,
							String note1, String note2, String note3, String note4,
							Long inote1, Long inote2, Long inote3, Long inote4) {
		super();
		this.icrechargerecordid = icrechargerecordid;
		this.orid = orid;
		this.iemployeeid = iemployeeid;
		this.iticketwinid = iticketwinid;
		this.szticketprintno = szticketprintno;
		this.newszticketprintno = newszticketprintno;
		this.cardno = cardno;
		this.oldcardno = oldcardno;
		this.oldszticketprintno = oldszticketprintno;
		this.cztp = cztp;
		this.amount = amount;
		this.accpointzs = accpointzs;
		this.zffs = zffs;
		this.rechargedate = rechargedate;
		this.note = note;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.inote1 = inote1;
		this.inote2 = inote2;
		this.inote3 = inote3;
		this.inote4 = inote4;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getZffs() {
		return zffs;
	}

	public void setZffs(String zffs) {
		this.zffs = zffs;
	}

	public Long getIcrechargerecordid() {
		return this.icrechargerecordid;
	}

	public void setIcrechargerecordid(Long icrechargerecordid) {
		this.icrechargerecordid = icrechargerecordid;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public Long getIticketwinid() {
		return this.iticketwinid;
	}

	public void setIticketwinid(Long iticketwinid) {
		this.iticketwinid = iticketwinid;
	}

	public String getSzticketprintno() {
		return this.szticketprintno;
	}

	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getOldcardno() {
		return this.oldcardno;
	}

	public void setOldcardno(String oldcardno) {
		this.oldcardno = oldcardno;
	}

	public String getOldszticketprintno() {
		return this.oldszticketprintno;
	}

	public void setOldszticketprintno(String oldszticketprintno) {
		this.oldszticketprintno = oldszticketprintno;
	}

	public String getCztp() {
		return this.cztp;
	}

	public void setCztp(String cztp) {
		this.cztp = cztp;
	}


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAccpointzs() {
		return accpointzs;
	}

	public void setAccpointzs(Double accpointzs) {
		this.accpointzs = accpointzs;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return this.note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return this.note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public Long getInote1() {
		return this.inote1;
	}

	public void setInote1(Long inote1) {
		this.inote1 = inote1;
	}

	public Long getInote2() {
		return this.inote2;
	}

	public void setInote2(Long inote2) {
		this.inote2 = inote2;
	}

	public Long getInote3() {
		return this.inote3;
	}

	public void setInote3(Long inote3) {
		this.inote3 = inote3;
	}

	public Long getInote4() {
		return this.inote4;
	}

	public void setInote4(Long inote4) {
		this.inote4 = inote4;
	}

}