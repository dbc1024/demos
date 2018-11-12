package com.ectrip.ticket.model.order;

import javax.persistence.Embeddable;

/**
 * StscomticketsalesdetailstabId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class StscomticketsalesdetailstabId implements java.io.Serializable {

	// Fields

	private Long icomticketsalesdetailsid;
	private Long isalesvoucherdetailsid;
	private Long isalesvoucherid;
	private Long iticketstationid;

	// Constructors

	/** default constructor */
	public StscomticketsalesdetailstabId() {
	}

	/** full constructor */
	public StscomticketsalesdetailstabId(Long icomticketsalesdetailsid,
			Long isalesvoucherdetailsid, Long isalesvoucherid, Long iticketstationid) {
		this.icomticketsalesdetailsid = icomticketsalesdetailsid;
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
	}

	// Property accessors

	public Long getIcomticketsalesdetailsid() {
		return this.icomticketsalesdetailsid;
	}

	public void setIcomticketsalesdetailsid(Long icomticketsalesdetailsid) {
		this.icomticketsalesdetailsid = icomticketsalesdetailsid;
	}

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
		if (!(other instanceof StscomticketsalesdetailstabId))
			return false;
		StscomticketsalesdetailstabId castOther = (StscomticketsalesdetailstabId) other;

		return ((this.getIcomticketsalesdetailsid() == castOther
				.getIcomticketsalesdetailsid()) || (this.getIcomticketsalesdetailsid() != null
				&& castOther.getIcomticketsalesdetailsid() != null && this
				.getIcomticketsalesdetailsid().equals(
						castOther.getIcomticketsalesdetailsid())))
				&& ((this.getIsalesvoucherdetailsid() == castOther
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
				+ (getIcomticketsalesdetailsid() == null ? 0 : this
						.getIcomticketsalesdetailsid().hashCode());
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