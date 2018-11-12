package com.ectrip.ec.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.model.order.LZorderlist;
import com.ectrip.ec.order.dao.idao.ILZorderlistDAO;
import com.ectrip.ec.order.service.iservice.ILZorderlistService;
@Service
public class LZorderlistServiceImpl extends GenericService implements ILZorderlistService {
	
	private ILZorderlistDAO lZorderlistDAO;
	@Autowired
	public void setlZorderlistDAO(ILZorderlistDAO lZorderlistDAO) {
		this.lZorderlistDAO = lZorderlistDAO;
		super.setGenericDao(lZorderlistDAO);
	}

	public List getLZorderlistByParam(String orid, Long iscenicid, Long orderlistid) {
		return lZorderlistDAO.getLZorderlistByParam(orid,iscenicid,orderlistid);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void saveLZorderlist(LZorderlist lzorderlist) {
		lZorderlistDAO.save(lzorderlist);
	}


}
