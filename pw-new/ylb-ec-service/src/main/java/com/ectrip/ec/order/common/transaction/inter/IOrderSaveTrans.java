package com.ectrip.ec.order.common.transaction.inter;

import com.ectrip.ec.model.order.common.OrderCombinDTO;

public interface IOrderSaveTrans {
	public boolean SaveOrder(OrderCombinDTO dto);


}
