package com.ectrip.ec.line.service;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.line.dao.idao.ILineOrderDao;
import com.ectrip.ec.line.service.iservice.ILineOrderService;

public class LineOrderService extends GenericDao implements ILineOrderService{
	private ILineOrderDao lineOrderDao;

	public ILineOrderDao getLineOrderDao() {
		return lineOrderDao;
	}

	public void setLineOrderDao(ILineOrderDao lineOrderDao) {
		this.lineOrderDao = lineOrderDao;
	}
}
