package com.ectrip.ticket.sale.service.card.model;

import java.util.List;

import com.ectrip.ticket.sale.service.card.core.Response;

public class SaleResponse extends Response{
	private List<?> cards;

	public List<?> getCards() {
		return cards;
	}

	public void setCards(List<?> cards) {
		this.cards = cards;
	}
	
}

