package com.ectrip.ticket.model.afcset;

/**
 * Fingerprintrandom entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Fingerprintrandom implements java.io.Serializable {

	// Fields

	private Long rdseq;
	private Long ibusinessid;
	private Long igardengateid;
	private Long isalesvoucherid;
	private Long iticketstationid;
	private String starttime;
	private String endtime;
	private String randomnumber;
	private Long jnumber;

	// Constructors

	/** default constructor */
	public Fingerprintrandom() {
	}

	/** full constructor */
	public Fingerprintrandom(Long rdseq, Long ibusinessid, Long igardengateid,
			Long isalesvoucherid, Long iticketstationid, String starttime,
			String endtime, String randomnumber, Long jnumber) {
		this.rdseq = rdseq;
		this.ibusinessid = ibusinessid;
		this.igardengateid = igardengateid;
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
		this.starttime = starttime;
		this.endtime = endtime;
		this.randomnumber = randomnumber;
		this.jnumber = jnumber;
	}

	// Property accessors

	public Long getRdseq() {
		return this.rdseq;
	}

	public void setRdseq(Long rdseq) {
		this.rdseq = rdseq;
	}

	public Long getIbusinessid() {
		return this.ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public Long getIgardengateid() {
		return this.igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getIsalesvoucherid() {
		return this.isalesvoucherid;
	}

	public void setIsalesvoucherid(Long isalesvoucherid) {
		this.isalesvoucherid = isalesvoucherid;
	}

	public Long getIticketstationid() {
		return this.iticketstationid;
	}

	public void setIticketstationid(Long iticketstationid) {
		this.iticketstationid = iticketstationid;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getRandomnumber() {
		return this.randomnumber;
	}

	public void setRandomnumber(String randomnumber) {
		this.randomnumber = randomnumber;
	}

	public Long getJnumber() {
		return this.jnumber;
	}

	public void setJnumber(Long jnumber) {
		this.jnumber = jnumber;
	}

}