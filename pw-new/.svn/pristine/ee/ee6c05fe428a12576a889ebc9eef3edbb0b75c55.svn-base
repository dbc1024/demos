package com.ectrip.ec.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.order.common.transaction.inter.IOrderSaveTrans;

@RestController
public class OrderSaveTransController {
	
	@Autowired
	private IOrderSaveTrans ordersaveTrans;
	
	@RequestMapping("/orderSaveTrans/saveOrder")
	public boolean SaveOrder(OrderCombinDTO dto){
		
		return ordersaveTrans.SaveOrder(dto);
	}
}
