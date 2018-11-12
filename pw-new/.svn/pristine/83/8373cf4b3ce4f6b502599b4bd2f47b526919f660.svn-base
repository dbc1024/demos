package com.ectrip.tourcard.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.model.TourCardOrder;

public interface ITourCardOrderService {

    public PaginationSupport findOrderList(TourCardOrder tourCardOrder,int pageSize,int startIndex, String url);

    public TourCardOrder findOrderByID(String orderID);
    
    List<TourCardOrder> getOrderByCode(String code);
}
