package com.ectrip.ec.model.order;

/**
 * Reserversale entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Reserversale implements java.io.Serializable {

	// Fields

	private Long maxid;//����
	private String orid;//�������
	private String xftime;//����ʱ�� ����������
	private Long tripid;//�˴α��
	private Long ivenueid;//���ر��
	private Long ivenueareaid;//����������
	private Long iemployeeid;//����� Ա��ID
	private String auditdate;//�������
	private Long salenumber;//��������
	private String rafttime;// ��ʱ��

	// Constructors

	/** default constructor */
	public Reserversale() {
	}

	/** minimal constructor */
	public Reserversale(Long maxid) {
		this.maxid = maxid;
	}

	/** full constructor */
	public Reserversale(Long maxid, String orid, String xftime, Long tripid,
			Long ivenueid, Long ivenueareaid, Long iemployeeid, String auditdate,
			Long salenumber, String rafttime) {
		this.maxid = maxid;
		this.orid = orid;
		this.xftime = xftime;
		this.tripid = tripid;
		this.ivenueid = ivenueid;
		this.ivenueareaid = ivenueareaid;
		this.iemployeeid = iemployeeid;
		this.auditdate = auditdate;
		this.salenumber = salenumber;
		this.rafttime = rafttime;
	}

	// Property accessors

	public Long getMaxid() {
		return this.maxid;
	}

	public void setMaxid(Long maxid) {
		this.maxid = maxid;
	}

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getXftime() {
		return this.xftime;
	}

	public void setXftime(String xftime) {
		this.xftime = xftime;
	}

	public Long getTripid() {
		return this.tripid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}

	public Long getIvenueid() {
		return this.ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}

	public Long getIvenueareaid() {
		return this.ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getAuditdate() {
		return this.auditdate;
	}

	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}

	public Long getSalenumber() {
		return this.salenumber;
	}

	public void setSalenumber(Long salenumber) {
		this.salenumber = salenumber;
	}

	public String getRafttime() {
		return this.rafttime;
	}

	public void setRafttime(String rafttime) {
		this.rafttime = rafttime;
	}

}