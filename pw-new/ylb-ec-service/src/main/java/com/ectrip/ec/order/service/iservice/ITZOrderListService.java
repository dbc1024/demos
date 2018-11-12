package com.ectrip.ec.order.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.order.TZorderlist;

public interface ITZOrderListService extends IGenericService{

	List<TZorderlist> getTZorderlist(Long orderlistid, String orid, Long iscenicid);
	
	
}
