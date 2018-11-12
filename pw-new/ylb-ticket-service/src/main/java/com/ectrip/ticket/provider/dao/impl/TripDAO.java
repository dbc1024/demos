package com.ectrip.ticket.provider.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ticket.provider.dao.ITripDAO;
@Repository
public class TripDAO extends GenericDao implements ITripDAO {

	@Override
	public List getTripByTripids(String tripids) {
		String hql="select distinct new map(t.tripid as tripid,t.tripname as tripname) from Trip t where t.tripid in ("+tripids+")";
		List find = this.find(hql);
		return find;
	}
	
}
