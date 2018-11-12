package com.ectrip.ticket.permitenter.service;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.permitenter.Reservechannel;
import com.ectrip.ticket.permitenter.dao.IReservechannelDAO;
import com.ectrip.ticket.permitenter.service.iservice.IReservechannelService;


public class ReservechannelService extends GenericService implements IReservechannelService {
  private IReservechannelDAO reservechannelDAO;

public IReservechannelDAO getReservechannelDAO() {
	return reservechannelDAO;
}

public void setReservechannelDAO(IReservechannelDAO reservechannelDAO) {
	this.reservechannelDAO = reservechannelDAO;
}

public List findListesbticket(String scenics) {
	// TODO Auto-generated method stub
	return reservechannelDAO.findListesbticket(scenics);
}

public PaginationSupport getReservechannel(Reservechannel reservechannel,
		String scenics, int pageSize, int page, String url) {
	// TODO Auto-generated method stub
	return reservechannelDAO.getReservechannel(reservechannel, scenics, pageSize, page, url);
}

public List getEsbaccessequip(Long igardengateid) {
	// TODO Auto-generated method stub
	return reservechannelDAO.getEsbaccessequip(igardengateid);
}

public String igetAllEsbaccessequiptabJSON(Long id) throws Exception{
	// TODO Auto-generated method stub
	return reservechannelDAO.igetAllEsbaccessequiptabJSON(id);
}

public Long getMaxId() throws Exception {
	// TODO Auto-generated method stub
	return reservechannelDAO.getMaxId();
}

public String igetGardengateJSON(Long id)throws Exception {
	// TODO Auto-generated method stub
	return reservechannelDAO.igetGardengateJSON(id);
}

public void addReservechannel(Reservechannel reservechannel) {
	// TODO Auto-generated method stub
	reservechannelDAO.addReservechannel(reservechannel);
}

public Reservechannel getReservechannelById(Long id) {
	// TODO Auto-generated method stub
	return reservechannelDAO.getReservechannelById(id);
}

public void delReservechannel(Reservechannel reservechannel) {
	// TODO Auto-generated method stub
	reservechannelDAO.delReservechannel(reservechannel);
}

public boolean checkReservechannel(Reservechannel reservechannel) {
	// TODO Auto-generated method stub
	return reservechannelDAO.checkReservechannel(reservechannel);
}



  
}
