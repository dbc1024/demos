package com.ectrip.ec.model.report;

import java.math.BigDecimal;

/**
 * TicketsalecountId entity. @author MyEclipse Persistence Tools
 */

public class TicketsalecountId implements java.io.Serializable {

	// Fields

	private Long isalesvoucherid;
	private Long iticketstationid;

	// Constructors

	/** default constructor */
	public TicketsalecountId() {
	}

	/** full constructor */
	public TicketsalecountId(Long isalesvoucherid,
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
	
}