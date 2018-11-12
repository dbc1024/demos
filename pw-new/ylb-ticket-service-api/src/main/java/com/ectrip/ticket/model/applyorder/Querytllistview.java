package com.ectrip.ticket.model.applyorder;

public class Querytllistview  implements java.io.Serializable {

	/**
	 * 查询已出票的 t_orderlist 数据视图，涉及到有出票后退订的情况
	 */
	private static final long serialVersionUID = -7795420343341731268L;

	private Long numb;//数量

	private Long iscenicid;//服务商id

	private Long icrowdkindpriceid;//价格id

	private Long itickettypeid;//产品id

	private Long icrowdkindid;//人群种类id

	private Double pric;//价格

	private String orid;//订单号

	private String dtstartdate;//开始日期

	private String notec;

	private String stdt;//首次消费日期

	public Long getNumb() {
		return numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public Long getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIcrowdkindid() {
		return icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public Double getPric() {
		return pric;
	}

	public void setPric(Double pric) {
		this.pric = pric;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getDtstartdate() {
		return dtstartdate;
	}

	public void setDtstartdate(String dtstartdate) {
		this.dtstartdate = dtstartdate;
	}

	public String getNotec() {
		return notec;
	}

	public void setNotec(String notec) {
		this.notec = notec;
	}

	public String getStdt() {
		return stdt;
	}

	public void setStdt(String stdt) {
		this.stdt = stdt;
	}

	public Querytllistview() {
		super();
	}

	public Querytllistview(Long numb, Long iscenicid, Long icrowdkindpriceid,
						   Long itickettypeid, Long icrowdkindid, Double pric, String orid,
						   String dtstartdate,String notec,String stdt) {
		super();
		this.numb = numb;
		this.iscenicid = iscenicid;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.itickettypeid = itickettypeid;
		this.icrowdkindid = icrowdkindid;
		this.pric = pric;
		this.orid = orid;
		this.dtstartdate = dtstartdate;
		this.notec = notec;
		this.stdt=stdt;
	}



}
