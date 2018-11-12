package com.ectrip.ticket.model.applyorder;


/**
 * Applytzl entity. @author MyEclipse Persistence Tools
 * 申报产品明细子票表  对应 T_zorderlist
 */

public class Applytzl  implements java.io.Serializable {


	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 1714923113886893457L;

	private ApplytzlId id;
	private Long zorderlistid;//明细流水子表id
	private Long orderlistid;//明细流水id
	private String aorid;//订单号
	private Long iscenicid;//服务商id
	private Long itickettypeid;//产品ID
	private Long iztickettypeid;//子产品ID
	private Long icrowdkindpriceid;//价格ID
	private Long icrowdkindid;//人群种类ID
	private String dtstartdate;//开始日期
	private String dtenddate;//截止日期
	private Double zpric;//子票价格
	private Long znumb;//子票数量
	private Long zyhnumb;//子票优惠数量
	private Double zamnt;//金额
	private Double zyhamnt;//优惠金额
	private Long notea;
	private Long noteb;
	private Long notec;
	private String noteh;
	private String notei;
	private String notej;
	private String seatno;//座位明
	private String seatids="";//座位ids
	private String tiseqs="";//游客ids
	private Long tiseq;//游客id
	private Long seatid;//座位id
	private Long iprogramid;//界面id
	private Long venueid;//场馆id
	private Long tripid;//场次id
	private Long ivenueareaid;//场馆区域id
	private Long ivenueseatsid;
	private Long ise;
	// Constructors

	/** default constructor */
	public Applytzl() {
	}

	public ApplytzlId getId() {
		return id;
	}

	public void setId(ApplytzlId id) {
		this.id = id;
	}

	public Long getZorderlistid() {
		return zorderlistid;
	}

	public void setZorderlistid(Long zorderlistid) {
		this.zorderlistid = zorderlistid;
	}

	public Long getOrderlistid() {
		return orderlistid;
	}

	public void setOrderlistid(Long orderlistid) {
		this.orderlistid = orderlistid;
	}

	public String getAorid() {
		return aorid;
	}

	public void setAorid(String aorid) {
		this.aorid = aorid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIztickettypeid() {
		return iztickettypeid;
	}

	public void setIztickettypeid(Long iztickettypeid) {
		this.iztickettypeid = iztickettypeid;
	}

	public Long getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public Long getIcrowdkindid() {
		return icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public String getDtstartdate() {
		return dtstartdate;
	}

	public void setDtstartdate(String dtstartdate) {
		this.dtstartdate = dtstartdate;
	}

	public String getDtenddate() {
		return dtenddate;
	}

	public void setDtenddate(String dtenddate) {
		this.dtenddate = dtenddate;
	}

	public Double getZpric() {
		return zpric;
	}

	public void setZpric(Double zpric) {
		this.zpric = zpric;
	}



	public Long getZnumb() {
		return znumb;
	}

	public void setZnumb(Long znumb) {
		this.znumb = znumb;
	}

	public Long getZyhnumb() {
		return zyhnumb;
	}

	public void setZyhnumb(Long zyhnumb) {
		this.zyhnumb = zyhnumb;
	}

	public Double getZamnt() {
		return zamnt;
	}

	public void setZamnt(Double zamnt) {
		this.zamnt = zamnt;
	}

	public Double getZyhamnt() {
		return zyhamnt;
	}

	public void setZyhamnt(Double zyhamnt) {
		this.zyhamnt = zyhamnt;
	}

	public Long getNotea() {
		return notea;
	}

	public void setNotea(Long notea) {
		this.notea = notea;
	}

	public Long getNoteb() {
		return noteb;
	}

	public void setNoteb(Long noteb) {
		this.noteb = noteb;
	}

	public Long getNotec() {
		return notec;
	}

	public void setNotec(Long notec) {
		this.notec = notec;
	}

	public String getNoteh() {
		return noteh;
	}

	public void setNoteh(String noteh) {
		this.noteh = noteh;
	}

	public String getNotei() {
		return notei;
	}

	public void setNotei(String notei) {
		this.notei = notei;
	}

	public String getNotej() {
		return notej;
	}

	public void setNotej(String notej) {
		this.notej = notej;
	}

	public String getSeatno() {
		return seatno;
	}

	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}

	public String getSeatids() {
		return seatids;
	}

	public void setSeatids(String seatids) {
		this.seatids = seatids;
	}

	public Long getSeatid() {
		return seatid;
	}

	public void setSeatid(Long seatid) {
		this.seatid = seatid;
	}

	public Long getIprogramid() {
		return iprogramid;
	}

	public void setIprogramid(Long iprogramid) {
		this.iprogramid = iprogramid;
	}

	public Long getVenueid() {
		return venueid;
	}

	public void setVenueid(Long venueid) {
		this.venueid = venueid;
	}

	public Long getTripid() {
		return tripid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}

	public Long getIvenueareaid() {
		return ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

	public Long getIse() {
		return ise;
	}

	public void setIse(Long ise) {
		this.ise = ise;
	}

	public Long getIvenueseatsid() {
		return ivenueseatsid;
	}

	public void setIvenueseatsid(Long ivenueseatsid) {
		this.ivenueseatsid = ivenueseatsid;
	}

	public String getTiseqs() {
		return tiseqs;
	}

	public void setTiseqs(String tiseqs) {
		this.tiseqs = tiseqs;
	}

	public Long getTiseq() {
		return tiseq;
	}

	public void setTiseq(Long tiseq) {
		this.tiseq = tiseq;
	}



}