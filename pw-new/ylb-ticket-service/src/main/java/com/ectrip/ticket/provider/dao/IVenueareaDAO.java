package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface IVenueareaDAO extends IGenericDao {

	public List getVenueareaByIvenueids(String ivenueids);

}
