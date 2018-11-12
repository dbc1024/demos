package com.ectrip.ticket.sale.service.card.model;

import java.util.List;

public class GetOrderListResponse extends GetICItemsResponse{
	private List<?>  recharges;
	private List<?> consumes;
	public List<?> getRecharges() {
		return recharges;
	}
	public void setRecharges(List<?> recharges) {
		this.recharges = recharges;
	}
	public List<?> getConsumes() {
		return consumes;
	}
	public void setConsumes(List<?> consumes) {
		this.consumes = consumes;
	}
	
}

