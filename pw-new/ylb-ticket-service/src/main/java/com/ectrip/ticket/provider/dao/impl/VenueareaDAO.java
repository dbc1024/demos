package com.ectrip.ticket.provider.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ticket.provider.dao.IVenueareaDAO;
@Repository
public class VenueareaDAO extends GenericDao implements IVenueareaDAO {

	@Override
	public List getVenueareaByIvenueids(String ivenueids) {
		String hql="select distinct new map(v1.ivenueid as ivenueid,v1.ivenueareaid as ivenueareaid,v1.ivenueareaname as ivenueareaname) from Venuearea v1,Venue v2 where v1.ivenueid=v2.ivenueid and v1.ivenueid in("+ivenueids+")";
		List find = this.find(hql);
		return find;
	}
	
	
}
