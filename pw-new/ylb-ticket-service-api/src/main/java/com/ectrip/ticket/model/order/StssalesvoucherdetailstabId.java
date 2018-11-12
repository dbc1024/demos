package com.ectrip.ticket.model.order;

import javax.persistence.Embeddable;

/**
 * StssalesvoucherdetailstabId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class StssalesvoucherdetailstabId implements java.io.Serializable {

	// Fields

	private Long isalesvoucherdetailsid;
	private Long isalesvoucherid;
	private Long iticketstationid;

	// Constructors

	/** default constructor */
	public StssalesvoucherdetailstabId() {
	}

	/** full constructor */
	public StssalesvoucherdetailstabId(Long isalesvoucherdetailsid,
			Long isalesvoucherid, Long iticketstationid) {
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
	}

	// Property accessors

	public Long getIsalesvoucherdetailsid() {
		return this.isalesvoucherdetailsid;
	}

	public void setIsalesvoucherdetailsid(Long isalesvoucherdetailsid) {
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
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
		if (!(other instanceof StssalesvoucherdetailstabId))
			return false;
		StssalesvoucherdetailstabId castOther = (StssalesvoucherdetailstabId) other;

		return ((this.getIsalesvoucherdetailsid() == castOther
				.getIsalesvoucherdetailsid()) || (this.getIsalesvoucherdetailsid() != null
				&& castOther.getIsalesvoucherdetailsid() != null && this
				.getIsalesvoucherdetailsid().equals(
						castOther.getIsalesvoucherdetailsid())))
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
				+ (getIsalesvoucherdetailsid() == null ? 0 : this
						.getIsalesvoucherdetailsid().hashCode());
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