package com.ectrip.ticket.model.order;

import javax.persistence.Embeddable;

/**
 * SeatsaletabId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SeatsaletabId implements java.io.Serializable {

	// Fields

	private Long seq;
	private Long isalesvoucherid;
	private Long iticketstationid;
	private Long isalesvoucherdetailsid;
	private Long szsoldticketid;
	private Long icomticketsalesdetailsid;

	// Constructors

	/** default constructor */
	public SeatsaletabId() {
	}

	/** full constructor */
	public SeatsaletabId(Long seq, Long isalesvoucherid,
			Long iticketstationid, Long isalesvoucherdetailsid,
			Long szsoldticketid, Long icomticketsalesdetailsid) {
		this.seq = seq;
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
		this.szsoldticketid = szsoldticketid;
		this.icomticketsalesdetailsid = icomticketsalesdetailsid;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
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

	public Long getIsalesvoucherdetailsid() {
		return this.isalesvoucherdetailsid;
	}

	public void setIsalesvoucherdetailsid(Long isalesvoucherdetailsid) {
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
	}

	public Long getSzsoldticketid() {
		return this.szsoldticketid;
	}

	public void setSzsoldticketid(Long szsoldticketid) {
		this.szsoldticketid = szsoldticketid;
	}



	public Long getIcomticketsalesdetailsid() {
		return icomticketsalesdetailsid;
	}

	public void setIcomticketsalesdetailsid(Long icomticketsalesdetailsid) {
		this.icomticketsalesdetailsid = icomticketsalesdetailsid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SeatsaletabId))
			return false;
		SeatsaletabId castOther = (SeatsaletabId) other;

		return ((this.getSeq() == castOther.getSeq()) || (this.getSeq() != null
				&& castOther.getSeq() != null && this.getSeq().equals(
				castOther.getSeq())))
				&& ((this.getIsalesvoucherid() == castOther
						.getIsalesvoucherid()) || (this.getIsalesvoucherid() != null
						&& castOther.getIsalesvoucherid() != null && this
						.getIsalesvoucherid().equals(
								castOther.getIsalesvoucherid())))
				&& ((this.getIticketstationid() == castOther
						.getIticketstationid()) || (this.getIticketstationid() != null
						&& castOther.getIticketstationid() != null && this
						.getIticketstationid().equals(
								castOther.getIticketstationid())))
				&& ((this.getIsalesvoucherdetailsid() == castOther
						.getIsalesvoucherdetailsid()) || (this
						.getIsalesvoucherdetailsid() != null
						&& castOther.getIsalesvoucherdetailsid() != null && this
						.getIsalesvoucherdetailsid().equals(
								castOther.getIsalesvoucherdetailsid())))
				&& ((this.getSzsoldticketid() == castOther.getSzsoldticketid()) || (this
						.getSzsoldticketid() != null
						&& castOther.getSzsoldticketid() != null && this
						.getSzsoldticketid().equals(
								castOther.getSzsoldticketid())))
				&& ((this.getIcomticketsalesdetailsid() == castOther
						.getIcomticketsalesdetailsid()) || (this
						.getIcomticketsalesdetailsid() != null
						&& castOther.getIcomticketsalesdetailsid() != null && this
						.getIcomticketsalesdetailsid().equals(
								castOther.getIcomticketsalesdetailsid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSeq() == null ? 0 : this.getSeq().hashCode());
		result = 37
				* result
				+ (getIsalesvoucherid() == null ? 0 : this.getIsalesvoucherid()
						.hashCode());
		result = 37
				* result
				+ (getIticketstationid() == null ? 0 : this
						.getIticketstationid().hashCode());
		result = 37
				* result
				+ (getIsalesvoucherdetailsid() == null ? 0 : this
						.getIsalesvoucherdetailsid().hashCode());
		result = 37
				* result
				+ (getSzsoldticketid() == null ? 0 : this.getSzsoldticketid()
						.hashCode());
		result = 37
				* result
				+ (getIcomticketsalesdetailsid() == null ? 0 : this
						.getIcomticketsalesdetailsid().hashCode());
		return result;
	}

}