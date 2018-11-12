package com.ectrip.ec.order.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.LOrderId;

public interface ILOrderService extends IGenericService{
	public void saveLorder(LOrder lorder);
	public List<LOrder> getLorderByOrid(String orid);
	public Object getLorderByLOrderId(LOrderId lorderId);
	public void updateLOrder(LOrder lorder);
}
