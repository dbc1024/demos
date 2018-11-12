package com.ectrip.ec.order.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.LOrderId;
import com.ectrip.ec.order.dao.idao.ILorderDAO;
import com.ectrip.ec.order.service.iservice.ILOrderService;
@Service
public class LorderServiceImpl extends GenericService implements ILOrderService {
	private ILorderDAO lorderDAO;
	@Autowired
	public void setLorderDAO(ILorderDAO lorderDAO) {
		this.lorderDAO = lorderDAO;
		setGenericDao(lorderDAO);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void saveLorder(LOrder lorder) {
		lorderDAO.save(lorder);
	}
	@Override
	public List<LOrder> getLorderByOrid(String orid) {
		return lorderDAO.getLorderByOrid(orid);
	}
	@Override
	public Object getLorderByLOrderId(LOrderId lorderId) {
		return lorderDAO.get(LOrder.class,lorderId);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateLOrder(LOrder lorder) {
		lorderDAO.update(lorder);
		
	}

}
