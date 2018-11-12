package com.ectrip.ticket.model.provider;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Ordercs entity. @author MyEclipse Persistence Tools
 */
@Table
@Entity
public class Ordercs implements java.io.Serializable {

	// Constructors
	@Id
	private Long seq;
	private Long ibusinessid;
	private Long isequence;
	private Long byisuse;
	private String zcs;
	private String ecs;
	private String dtmakedate;
	
	@Transient
	private String szbusinessname;//业务类型名称
	public String getSzbusinessname() {
		return szbusinessname;
	}

	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getIbusinessid() {
		return ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public Long getIsequence() {
		return isequence;
	}

	public void setIsequence(Long isequence) {
		this.isequence = isequence;
	}

	public Long getByisuse() {
		return byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getZcs() {
		return zcs;
	}

	public void setZcs(String zcs) {
		this.zcs = zcs;
	}

	public String getEcs() {
		return ecs;
	}

	public void setEcs(String ecs) {
		this.ecs = ecs;
	}

	/** default constructor */
	public Ordercs() {
	}

	/** minimal constructor */
	public Ordercs(Long seq, Long ibusinessid, String zcs,
			String ecs, Long isequence, Long byisuse) {
		this.seq = seq;
		this.ibusinessid = ibusinessid;
		this.zcs = zcs;
		this.ecs = ecs;
		this.isequence = isequence;
		this.byisuse = byisuse;
	}
}
