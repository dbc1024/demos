package com.ectrip.ticket.venuemarketing.service;

import java.util.List;

import com.ectrip.ec.model.order.Seatordertab;


public interface IPullSeatService {
	
	List<Seatordertab> pullSeat(String orid) throws Exception;

}
