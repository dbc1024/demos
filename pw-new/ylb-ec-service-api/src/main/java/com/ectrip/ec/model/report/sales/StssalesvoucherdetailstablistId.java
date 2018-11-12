package com.ectrip.ec.model.report.sales;

import java.math.BigDecimal;

/**
 * StssalesvoucherdetailstablistId entity. @author MyEclipse Persistence Tools
 */

public class StssalesvoucherdetailstablistId implements java.io.Serializable {

	// Fields

	private Long isalesvoucherdetailsid;
	private Long isalesvoucherid;
	private Long iticketstationid;

	// Constructors

	/** default constructor */
	public StssalesvoucherdetailstablistId() {
	}

	/** full constructor */
	public StssalesvoucherdetailstablistId(Long isalesvoucherdetailsid,
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

	
}