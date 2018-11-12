package com.ectrip.ticket.model.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * StssoldticketsubtabId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class StssoldticketsubtabId implements java.io.Serializable {

	// Fields
	@Column
	private Long szsoldticketsubid;
	@Column
	private Long szsoldticketid;
	@Column
	private Long isalesvoucherdetailsid;
	@Column
	private Long isalesvoucherid;
	@Column
	private Long iticketstationid;

	// Constructors

	/** default constructor */
	public StssoldticketsubtabId() {
	}

	/** full constructor */
	public StssoldticketsubtabId(Long szsoldticketsubid, Long szsoldticketid,
			Long isalesvoucherdetailsid, Long isalesvoucherid, Long iticketstationid) {
		this.szsoldticketsubid = szsoldticketsubid;
		this.szsoldticketid = szsoldticketid;
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
	}

	// Property accessors

	public Long getSzsoldticketsubid() {
		return this.szsoldticketsubid;
	}

	public void setSzsoldticketsubid(Long szsoldticketsubid) {
		this.szsoldticketsubid = szsoldticketsubid;
	}

	public Long getSzsoldticketid() {
		return this.szsoldticketid;
	}

	public void setSzsoldticketid(Long szsoldticketid) {
		this.szsoldticketid = szsoldticketid;
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
		if (!(other instanceof StssoldticketsubtabId))
			return false;
		StssoldticketsubtabId castOther = (StssoldticketsubtabId) other;

		return ((this.getSzsoldticketsubid() == castOther.getSzsoldticketsubid()) || (this
				.getSzsoldticketsubid() != null
				&& castOther.getSzsoldticketsubid() != null && this
				.getSzsoldticketsubid().equals(castOther.getSzsoldticketsubid())))
				&& ((this.getSzsoldticketid() == castOther.getSzsoldticketid()) || (this
						.getSzsoldticketid() != null
						&& castOther.getSzsoldticketid() != null && this
						.getSzsoldticketid().equals(castOther.getSzsoldticketid())))
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
				+ (getSzsoldticketsubid() == null ? 0 : this.getSzsoldticketsubid()
						.hashCode());
		result = 37
				* result
				+ (getSzsoldticketid() == null ? 0 : this.getSzsoldticketid()
						.hashCode());
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