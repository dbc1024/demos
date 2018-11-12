package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.provider.dao.IVenueareaDAO;
import com.ectrip.ticket.provider.service.IVenueareaService;
@Service
public class VenueareaServiceImpl extends GenericService implements IVenueareaService {
	@Autowired
	private IVenueareaDAO venueareaDAO;

	@Override
	public List getVenueareaByIvenueids(String ivenueids) {
		return venueareaDAO.getVenueareaByIvenueids(ivenueids);
	}

}
