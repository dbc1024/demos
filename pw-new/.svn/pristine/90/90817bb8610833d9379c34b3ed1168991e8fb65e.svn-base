package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.provider.dao.ITripDAO;
import com.ectrip.ticket.provider.service.ITripService;

@Service
public class TripServiceImpl extends GenericService implements ITripService {
	@Autowired
	private ITripDAO tripDAO;
	
	@Override
	public List getTripByTripids(String tripids) {
		return tripDAO.getTripByTripids(tripids);

	}

}
