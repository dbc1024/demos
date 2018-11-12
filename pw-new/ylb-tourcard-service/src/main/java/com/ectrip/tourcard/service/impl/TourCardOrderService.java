package com.ectrip.tourcard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.dao.ITourCardOrderDAO;
import com.ectrip.tourcard.model.TourCardOrder;
import com.ectrip.tourcard.service.ITourCardOrderService;

@Service
public class TourCardOrderService implements ITourCardOrderService {

	@Autowired
	private ITourCardOrderDAO tourCardOrderDAO;

	public PaginationSupport findOrderList(TourCardOrder tourCardOrder, int pageSize, int startIndex, String url) {
		return tourCardOrderDAO.findOrderList(tourCardOrder,pageSize,startIndex,url);
	}

	public TourCardOrder findOrderByID(String orderID){
		return tourCardOrderDAO.findOrderByID(orderID);
	}

	public List<TourCardOrder> getOrderByCode(String code) {
		// TODO Auto-generated method stub
		return tourCardOrderDAO.getOrderByCode(code);
	}


}
