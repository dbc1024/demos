package com.ectrip.ec.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.order.dao.idao.ITOrderListDAO;
import com.ectrip.ec.order.dao.idao.IYOrderDAO;
import com.ectrip.ec.order.service.iservice.ITOrderListService;
import com.ectrip.ec.order.service.iservice.IYOrderService;
@Service
public class TOrderListServiceImpl extends GenericService implements ITOrderListService{
	@Autowired
	private ITOrderListDAO tOrderListDAO;

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateTOrderList(TOrderlist tOrderList) {
		tOrderListDAO.update(tOrderList);
		
	}

	@Override
	public List<TOrderlist> findTOrderList( String orid, Long iscenicid) throws Exception {
		return tOrderListDAO.findTOrderList(orid,iscenicid);
	}

}
