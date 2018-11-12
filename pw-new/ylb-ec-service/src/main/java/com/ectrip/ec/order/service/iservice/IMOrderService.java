package com.ectrip.ec.order.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.order.MOrder;

public interface IMOrderService extends IGenericService{

	void updateMOrder(MOrder mOrder);
	
}
