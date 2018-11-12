package com.ectrip.ticket.model.venuemarketing;

/**
 * Seatlocktab ������λ��
 * @author luoxin
 * @Date: 131127
 * @remark:���û�������λʹ��
 * */

/**
 * Seatlocktab 锁定座位表
 * @author luoxin
 * @Date: 131127
 * @remark:供用户定制座位使用
 * */

public class Seatlocktab implements java.io.Serializable{

	private Long iseatlockid;//锁定座位ID
	private Long iemployeeid;//锁定用户ID
	private String usname;//锁定关系用户姓名
	private String usid;//旅行社
	private Long tel;//电话
	private String collectname;//取票人姓名
	private String idnumber;//证件号码
	private Long iprogramid;//节目ID
	private Long ivenueid;//场馆ID
	private Long ivenueareaid;//区域ID
	private Long itripid;//演出场次
	private String startdate;//演出日期
	private Long seatcounts;//座位数
	private String status;//状态：00 释放(释放未出票的座位)， 01 出票(未出完票)，02 锁定(已出完票)

	//出票
	private Long isalesvoucherid;
	private Long iticketstationid;

	public Long getIseatlockid() {
		return iseatlockid;
	}
	public void setIseatlockid(Long iseatlockid) {
		this.iseatlockid = iseatlockid;
	}
	public Long getIemployeeid() {
		return iemployeeid;
	}
	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}
	public String getUsname() {
		return usname;
	}
	public void setUsname(String usname) {
		this.usname = usname;
	}
	public String getUsid() {
		return usid;
	}
	public void setUsid(String usid) {
		this.usid = usid;
	}
	public Long getTel() {
		return tel;
	}
	public void setTel(Long tel) {
		this.tel = tel;
	}
	public String getCollectname() {
		return collectname;
	}
	public void setCollectname(String collectname) {
		this.collectname = collectname;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public Long getIprogramid() {
		return iprogramid;
	}
	public void setIprogramid(Long iprogramid) {
		this.iprogramid = iprogramid;
	}
	public Long getIvenueid() {
		return ivenueid;
	}
	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}
	public Long getIvenueareaid() {
		return ivenueareaid;
	}
	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}
	public Long getItripid() {
		return itripid;
	}
	public void setItripid(Long itripid) {
		this.itripid = itripid;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public Long getSeatcounts() {
		return seatcounts;
	}
	public void setSeatcounts(Long seatcounts) {
		this.seatcounts = seatcounts;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getIsalesvoucherid() {
		return isalesvoucherid;
	}
	public void setIsalesvoucherid(Long isalesvoucherid) {
		this.isalesvoucherid = isalesvoucherid;
	}
	public Long getIticketstationid() {
		return iticketstationid;
	}
	public void setIticketstationid(Long iticketstationid) {
		this.iticketstationid = iticketstationid;
	}


}
