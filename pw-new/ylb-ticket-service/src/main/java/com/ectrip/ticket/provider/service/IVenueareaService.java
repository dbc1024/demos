package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;

public interface IVenueareaService extends IGenericService{

	public List getVenueareaByIvenueids(String ivenueids);

}
