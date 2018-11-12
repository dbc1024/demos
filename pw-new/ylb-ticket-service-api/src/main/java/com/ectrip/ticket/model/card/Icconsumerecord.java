package com.ectrip.ticket.model.card;


/**
 * 一卡通消费记录表
 * Icconsumerecord entity. @author MyEclipse Persistence Tools
 */

public class Icconsumerecord  implements java.io.Serializable {


	/**
	 * Icconsumerecord.java
	 * liujianwen
	 * 20162016-5-4
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long icconsumerecordid;//主键
	private Long picconsumerecordid;//如果是撤销操作,存储原id
	private Long piemployeeid;//如果是撤销,存储售票人员id
	private String orid;//
	private String usid;//消费账号(内部卡号) Custom
	private Long icrowdkindpriceid;//消费产品
	private Double consumeamount;//消费金额
	private Long iaccessequipid;//消费设备
	private String xffs;//-消费方式(20实际金额消费,21赠送金额消费)
	private String consumedate;//消费时间
	private String checkoutdate;//結賬時間
	private Long iemployeeid;//如果是是非闸机操作,填操作人员id
	private String cztp;//操作类型系统参数CZTP(03即时消费,04消费撤销,06计时消费,07记账消费)
	private String timekeeping;//如果是计时消费，则以json格式复制原计时策略
	private String xfzt;//--00:已结账 01:记账中 02:计时中
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

	public String getTimekeeping() {
		return timekeeping;
	}

	public String getCheckoutdate() {
		return checkoutdate;
	}

	public void setCheckoutdate(String checkoutdate) {
		this.checkoutdate = checkoutdate;
	}

	public void setTimekeeping(String timekeeping) {
		this.timekeeping = timekeeping;
	}

	public String getXfzt() {
		return xfzt;
	}

	public void setXfzt(String xfzt) {
		this.xfzt = xfzt;
	}

	public Long getPiemployeeid() {
		return piemployeeid;
	}

	public void setPiemployeeid(Long piemployeeid) {
		this.piemployeeid = piemployeeid;
	}

	public Long getPicconsumerecordid() {
		return picconsumerecordid;
	}

	public void setPicconsumerecordid(Long picconsumerecordid) {
		this.picconsumerecordid = picconsumerecordid;
	}

	/** default constructor */
	public Icconsumerecord() {
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getConsumedate() {
		return consumedate;
	}

	public void setConsumedate(String consumedate) {
		this.consumedate = consumedate;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	/** minimal constructor */
	public Icconsumerecord(Long icconsumerecordid, String usid, Long icrowdkindpriceid, Double consumeamount, Long iaccessequipid, String xffs) {
		this.icconsumerecordid = icconsumerecordid;
		this.usid = usid;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.consumeamount = consumeamount;
		this.iaccessequipid = iaccessequipid;
		this.xffs = xffs;
	}



	public Icconsumerecord(Long icconsumerecordid, String orid, String usid,
						   Long icrowdkindpriceid, Double consumeamount, Long iaccessequipid,
						   String xffs, String consumedate, String note, String note1,
						   String note2, String note3, String note4, Long inote1, Long inote2,
						   Long inote3, Long inote4) {
		super();
		this.icconsumerecordid = icconsumerecordid;
		this.orid = orid;
		this.usid = usid;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.consumeamount = consumeamount;
		this.iaccessequipid = iaccessequipid;
		this.xffs = xffs;
		this.consumedate = consumedate;
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

	public Long getIcconsumerecordid() {
		return this.icconsumerecordid;
	}

	public void setIcconsumerecordid(Long icconsumerecordid) {
		this.icconsumerecordid = icconsumerecordid;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getIcrowdkindpriceid() {
		return this.icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}



	public Double getConsumeamount() {
		return consumeamount;
	}

	public void setConsumeamount(Double consumeamount) {
		this.consumeamount = consumeamount;
	}

	public Long getIaccessequipid() {
		return this.iaccessequipid;
	}

	public void setIaccessequipid(Long iaccessequipid) {
		this.iaccessequipid = iaccessequipid;
	}

	public String getXffs() {
		return this.xffs;
	}

	public void setXffs(String xffs) {
		this.xffs = xffs;
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

	public Long getIemployeeid() {
		return iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getCztp() {
		return cztp;
	}

	public void setCztp(String cztp) {
		this.cztp = cztp;
	}


}