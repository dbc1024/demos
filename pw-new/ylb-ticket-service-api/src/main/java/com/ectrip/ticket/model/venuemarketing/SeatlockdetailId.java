package com.ectrip.ticket.model.venuemarketing;

import java.io.Serializable;

public class SeatlockdetailId implements Serializable {

	private Long iseatlockserial;//流水ID
	private Long iseatlockid;//锁定座位ID

	public Long getIseatlockserial() {
		return iseatlockserial;
	}
	public void setIseatlockserial(Long iseatlockserial) {
		this.iseatlockserial = iseatlockserial;
	}
	public Long getIseatlockid() {
		return iseatlockid;
	}
	public void setIseatlockid(Long iseatlockid) {
		this.iseatlockid = iseatlockid;
	}


}
