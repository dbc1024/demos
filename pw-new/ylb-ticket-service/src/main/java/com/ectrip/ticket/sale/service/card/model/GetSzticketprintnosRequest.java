package com.ectrip.ticket.sale.service.card.model;

import com.ectrip.ticket.sale.service.card.core.Request;

public class GetSzticketprintnosRequest extends Request{
	private String count;//��������
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}

