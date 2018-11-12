package com.ectrip.ec.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.order.dao.idao.ITZOrderListDAO;
import com.ectrip.ec.order.dao.idao.IYOrderDAO;
import com.ectrip.ec.order.service.iservice.ITZOrderListService;
import com.ectrip.ec.order.service.iservice.IYOrderService;
@Service
public class TZOrderListServiceImpl extends GenericService implements ITZOrderListService{
	@Autowired
	private ITZOrderListDAO tZOrderListDAO;
	

	public List<TZorderlist> getTZorderlist(Long orderlistid, String orid, Long iscenicid) {
		return tZOrderListDAO.getTZorderlist(orderlistid,orid,iscenicid);
	}

}
