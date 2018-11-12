package com.ectrip.ec.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.model.order.LOrderlist;
import com.ectrip.ec.order.dao.idao.ILorderListDAO;
import com.ectrip.ec.order.service.iservice.ILorderListService;
@Service
public class LorderListServiceImpl extends GenericService implements ILorderListService {
	@Autowired
	private ILorderListDAO lorderListDAO;
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void saveLorderList(LOrderlist lorderList) {
		lorderListDAO.saveLorderList(lorderList);
	}
	@Override
	public List<LOrderlist> getLOrderlistByids(String orid, Long iscenicid) {
		return lorderListDAO.getLOrderlistByids(orid,iscenicid);
	}

}
