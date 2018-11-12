package com.ectrip.ec.model.report.sales;

import java.math.BigDecimal;

/**
 * StssalesvouchertablistId entity. @author MyEclipse Persistence Tools
 */

public class StssalesvouchertablistId implements java.io.Serializable {

	// Fields

	private Long isalesvoucherid;//����ƾ֤���
	private Long iticketstationid;//վ����

	// Constructors

	/** default constructor */
	public StssalesvouchertablistId() {
	}

	/** full constructor */
	public StssalesvouchertablistId(Long isalesvoucherid,
			Long iticketstationid) {
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
	}

	// Property accessors

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
		if (!(other instanceof StssalesvouchertablistId))
			return false;
		StssalesvouchertablistId castOther = (StssalesvouchertablistId) other;

		return ((this.getIsalesvoucherid() == castOther.getIsalesvoucherid()) || (this
				.getIsalesvoucherid() != null
				&& castOther.getIsalesvoucherid() != null && this
				.getIsalesvoucherid().equals(castOther.getIsalesvoucherid())))
				&& ((this.getIticketstationid() == castOther
						.getIticketstationid()) || (this.getIticketstationid() != null
						&& castOther.getIticketstationid() != null && this
						.getIticketstationid().equals(
								castOther.getIticketstationid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIsalesvoucherid() == null ? 0 : this.getIsalesvoucherid()
						.hashCode());
		result = 37
				* result
				+ (getIticketstationid() == null ? 0 : this
						.getIticketstationid().hashCode());
		return result;
	}

}