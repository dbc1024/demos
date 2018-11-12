package com.ectrip.ticket.sale.service.card.model;

import com.ectrip.ticket.sale.service.card.core.Request;

public class GetCardsRequest extends Request{
	private String date;//����yyyy-MM-dd
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}

