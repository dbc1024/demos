package com.ectrip.ticket.model.provider;


/**
 * Changebackrate entity. @author MyEclipse Persistence Tools
 */

public class Changebackrate implements java.io.Serializable {

	// Fields

	private Long changebackrateid;  //�˶�����ID
	private Long gzid;				//����ID
	private Long time;             //ʱ��
	private Double tdfl;			//�˶�����

	// Constructors

	/** default constructor */
	public Changebackrate() {
	}

	/** full constructor */
	public Changebackrate(Long changebackrateid,
			Long gzid,Long time,Double tdfl) {
		this.changebackrateid = changebackrateid;
		this.gzid=gzid;
		this.time=time;
		this.tdfl=tdfl;
	}

	// Property accessors
	
	
	public Long getChangebackrateid() {
		return changebackrateid;
	}

	public void setChangebackrateid(Long changebackrateid) {
		this.changebackrateid = changebackrateid;
	}

	public Long getGzid() {
		return gzid;
	}

	public void setGzid(Long gzid) {
		this.gzid = gzid;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Double getTdfl() {
		return tdfl;
	}

	public void setTdfl(Double tdfl) {
		this.tdfl = tdfl;
	}

}