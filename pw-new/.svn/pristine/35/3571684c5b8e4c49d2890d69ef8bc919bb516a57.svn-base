package com.ectrip.tourcard.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.model.TourCardOrder;

public interface ITourCardOrderDAO extends IGenericDao{

	PaginationSupport findOrderList(TourCardOrder order, int pageSize, int startIndex, String url);

    TourCardOrder findOrderByID(String orderID);
    
    List<TourCardOrder> getOrderByCode(String code);

    TourCardOrder getUnpayOrder(String userId, String cardCode);
}
