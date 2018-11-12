package com.ectrip.ticket.sale.service.card.model;

import com.ectrip.ticket.sale.service.card.core.Request;

public class PrintReceiptRequest extends Request{
	private String orid;//����yyyy-MM-dd

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}
	
	
}

