package com.ectrip.ticket.model.card;

/**
 * 消费项目绑定表.pos机消费需根据mac添加检票设备
 * --消费类型(00先消费实际金额,01先消费赠送金额)
 * ESBGARDENGATETAB NOTE1填值守售票员id,NOTE2绑定窗口id
 * Bindingprice entity. @author MyEclipse Persistence Tools
 */

public class Bindingprice implements java.io.Serializable {

	// Fields

	/**
	 * Bindingprice.java
	 * liujianwen
	 * 20162016-5-4
	 */
	private static final long serialVersionUID = 1L;
	private Long bindingpriceid;//序列Bindingprice_seq
	private Long igardengateid;//ESBGARDENGATETAB园门id
	private Long icrowdkindpriceid;//外键 EDMCROWDKINDPRICETAB 消费项目
	private String xftp;//消费方式(00先消费实际金额,01先消费赠送金额,02只允许实际金额)
	private Long sortnum;
	private Long intervaltime;//消费时间
	private String jztp;//--记账类型(00即时消费,01记账,02计时)
	private String timekeeping;//--如果是计时消费，则以json格式填计时策略
	//    --unit 计时单位 1分钟 2小时 3天
	//    --unitprice 基本计时单价,单位元
	//    --delayed 计时延时时间
	//    --upperlimit 计时时间上限
	//    --discountprices 优惠计时策略列表。意为当计时满startTime至endTime时以discountprice为计时单价,包含以下字段
	//      --startTime 0为endTime以下时间
	//      --endTime   0为startTime以上时间
	//      --discountprice 优惠单价,单位元
	private String note;//备注
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	private Long inote1;
	private Long inote2;
	private Long inote3;
	private Long inote4;

	//自定义的字段
	private String szbusinessname;
	private String szcrowdkindname;
	private String sztickettypename;
	private String sztickettypecode;
	private String icrowdkindpricecode;
	private String szaddress;
	private Long byisuse;
	private String szgardengatename;
	private Double mactualsaleprice;
	// Constructors

	/** default constructor */
	public Bindingprice() {
	}


	/** minimal constructor */
	public Bindingprice(Long bindingpriceid, Long igardengateid,
						Long icrowdkindpriceid, String xftp) {
		this.bindingpriceid = bindingpriceid;
		this.igardengateid = igardengateid;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.xftp = xftp;
	}


	// Property accessors


	public String getJztp() {
		return jztp;
	}


	public void setJztp(String jztp) {
		this.jztp = jztp;
	}


	public String getTimekeeping() {
		return timekeeping;
	}


	public void setTimekeeping(String timekeeping) {
		this.timekeeping = timekeeping;
	}


	public Long getBindingpriceid() {
		return this.bindingpriceid;
	}

	public Bindingprice(Long bindingpriceid, Long igardengateid,
						Long icrowdkindpriceid, String xftp, String note1, String note2,
						String note3, String note4, Long inote1, Long inote2, Long inote3,
						Long inote4) {
		super();
		this.bindingpriceid = bindingpriceid;
		this.igardengateid = igardengateid;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.xftp = xftp;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.inote1 = inote1;
		this.inote2 = inote2;
		this.inote3 = inote3;
		this.inote4 = inote4;
	}


	public void setBindingpriceid(Long bindingpriceid) {
		this.bindingpriceid = bindingpriceid;
	}

	public Long getIgardengateid() {
		return this.igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getIcrowdkindpriceid() {
		return this.icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public String getXftp() {
		return this.xftp;
	}

	public void setXftp(String xftp) {
		this.xftp = xftp;
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


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * @return the szbusinessname
	 */
	public String getSzbusinessname() {
		return szbusinessname;
	}


	/**
	 * @return the szcrowdkindname
	 */
	public String getSzcrowdkindname() {
		return szcrowdkindname;
	}


	/**
	 * @return the sztickettypename
	 */
	public String getSztickettypename() {
		return sztickettypename;
	}





	/**
	 * @param szbusinessname the szbusinessname to set
	 */
	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}


	/**
	 * @param szcrowdkindname the szcrowdkindname to set
	 */
	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}


	/**
	 * @param sztickettypename the sztickettypename to set
	 */
	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}





	/**
	 * @return the sztickettypecode
	 */
	public String getSztickettypecode() {
		return sztickettypecode;
	}


	/**
	 * @param sztickettypecode the sztickettypecode to set
	 */
	public void setSztickettypecode(String sztickettypecode) {
		this.sztickettypecode = sztickettypecode;
	}








	/**
	 * @return the icrowdkindpricecode
	 */
	public String getIcrowdkindpricecode() {
		return icrowdkindpricecode;
	}


	/**
	 * @param icrowdkindpricecode the icrowdkindpricecode to set
	 */
	public void setIcrowdkindpricecode(String icrowdkindpricecode) {
		this.icrowdkindpricecode = icrowdkindpricecode;
	}


	/**
	 * @return the sortnum
	 */
	public Long getSortnum() {
		return sortnum;
	}


	/**
	 * @param sortnum the sortnum to set
	 */
	public void setSortnum(Long sortnum) {
		this.sortnum = sortnum;
	}


	/**
	 * @return the szaddress
	 */
	public String getSzaddress() {
		return szaddress;
	}


	/**
	 * @return the byisuse
	 */
	public Long getByisuse() {
		return byisuse;
	}


	/**
	 * @return the szgardengatename
	 */
	public String getSzgardengatename() {
		return szgardengatename;
	}


	/**
	 * @param szaddress the szaddress to set
	 */
	public void setSzaddress(String szaddress) {
		this.szaddress = szaddress;
	}


	/**
	 * @param byisuse the byisuse to set
	 */
	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}


	/**
	 * @param szgardengatename the szgardengatename to set
	 */
	public void setSzgardengatename(String szgardengatename) {
		this.szgardengatename = szgardengatename;
	}


	/**
	 * @return the intervaltime
	 */
	public Long getIntervaltime() {
		return intervaltime;
	}


	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}


	/**
	 * @param intervaltime the intervaltime to set
	 */
	public void setIntervaltime(Long intervaltime) {
		this.intervaltime = intervaltime;
	}


	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}


	/**
	 * @return the mactualsaleprice
	 */
	public Double getMactualsaleprice() {
		return mactualsaleprice;
	}


	/**
	 * @param mactualsaleprice the mactualsaleprice to set
	 */
	public void setMactualsaleprice(Double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}

}