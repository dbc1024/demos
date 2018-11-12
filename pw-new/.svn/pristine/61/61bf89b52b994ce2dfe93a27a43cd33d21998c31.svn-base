package com.ectrip.ticket.model.order;

import javax.persistence.Embeddable;

/**
 * StssalessettlementtabId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class StssalessettlementtabId implements java.io.Serializable {

	// Fields

	private Long isalessettlementid;
	private Long isalesvoucherid;
	private Long iticketstationid;

	// Constructors

	/** default constructor */
	public StssalessettlementtabId() {
	}

	/** full constructor */
	public StssalessettlementtabId(Long isalessettlementid, Long isalesvoucherid,
			Long iticketstationid) {
		this.isalessettlementid = isalessettlementid;
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
	}

	// Property accessors

	public Long getIsalessettlementid() {
		return this.isalessettlementid;
	}

	public void setIsalessettlementid(Long isalessettlementid) {
		this.isalessettlementid = isalessettlementid;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StssalessettlementtabId))
			return false;
		StssalessettlementtabId castOther = (StssalessettlementtabId) other;

		return ((this.getIsalessettlementid() == castOther.getIsalessettlementid()) || (this
				.getIsalessettlementid() != null
				&& castOther.getIsalessettlementid() != null && this
				.getIsalessettlementid().equals(castOther.getIsalessettlementid())))
				&& ((this.getIsalesvoucherid() == castOther.getIsalesvoucherid()) || (this
						.getIsalesvoucherid() != null
						&& castOther.getIsalesvoucherid() != null && this
						.getIsalesvoucherid().equals(castOther.getIsalesvoucherid())))
				&& ((this.getIticketstationid() == castOther.getIticketstationid()) || (this
						.getIticketstationid() != null
						&& castOther.getIticketstationid() != null && this
						.getIticketstationid().equals(castOther.getIticketstationid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIsalessettlementid() == null ? 0 : this.getIsalessettlementid()
						.hashCode());
		result = 37
				* result
				+ (getIsalesvoucherid() == null ? 0 : this.getIsalesvoucherid()
						.hashCode());
		result = 37
				* result
				+ (getIticketstationid() == null ? 0 : this.getIticketstationid()
						.hashCode());
		return result;
	}

}