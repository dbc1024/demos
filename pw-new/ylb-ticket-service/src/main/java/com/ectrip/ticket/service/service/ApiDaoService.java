package com.ectrip.ticket.service.service;

import java.util.List;

import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.ticket.service.dao.idao.IApiDao;
import com.ectrip.ticket.service.service.iservice.IApiDaoService;

public class ApiDaoService implements IApiDaoService {

	IApiDao apiDao ;

	public IApiDao getApiDao() {
		return apiDao;
	}

	public void setApiDao(IApiDao apiDao) {
		this.apiDao = apiDao;
	}

	public List<Espdutytab> getEspdutytab() throws Exception {
		// TODO Auto-generated method stub
		return apiDao.getEspdutytab() ;
	}
	
	
	

	
}
