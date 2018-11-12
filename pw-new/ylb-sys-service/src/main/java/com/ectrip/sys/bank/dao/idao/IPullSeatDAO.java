package com.ectrip.sys.bank.dao.idao;

import java.util.List;
import java.util.Set;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.sys.bank.service.iservice.IPullSeatService;

public interface IPullSeatDAO extends IGenericDao{
	public Set pullSeat(Long ivenueid,Long ivenueareaid);
	
	public List<TZorderlist> getOrderFilmInfo(String orid);
	
	
	public Set getSaledSeat(String startdate, Long ivenueid, Long ivenueareaid, Long tripid);

}
