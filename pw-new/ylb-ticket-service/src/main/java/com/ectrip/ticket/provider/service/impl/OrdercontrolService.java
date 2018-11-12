package com.ectrip.ticket.provider.service.impl;

import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.model.provider.Proordercontroltab;
import com.ectrip.ticket.provider.dao.IOrdercontrolDao;
import com.ectrip.ticket.provider.service.IOrdercontrolServcie;

@Service
public class OrdercontrolService extends GenericService implements IOrdercontrolServcie{

	private IOrdercontrolDao ordercontrolDao;

	public IOrdercontrolDao getOrdercontrolDao() {
		return ordercontrolDao;
	}

	public void setOrdercontrolDao(IOrdercontrolDao ordercontrolDao) {
		this.ordercontrolDao = ordercontrolDao;
	}

	public Proordercontroltab showProordercontroltab(Long iscenicid) {
		// TODO Auto-generated method stub
		return this.ordercontrolDao.showProordercontroltab(iscenicid);
	}

	public void saveOrupdateProordercontroltab(Proordercontroltab ordercontrol) {
		// TODO Auto-generated method stub
		this.ordercontrolDao.saveOrupdateProordercontroltab(ordercontrol);
	}
	
	
}
