package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface ITripDAO extends IGenericDao{

	public List getTripByTripids(String tripids);

}
