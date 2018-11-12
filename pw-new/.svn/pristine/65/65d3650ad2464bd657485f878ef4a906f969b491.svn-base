package com.ectrip.ec.order.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.order.service.iservice.IPayOrderService;

@RestController
public class PayOrderController {

	@Autowired
	public IPayOrderService payorderService;
	
	@RequestMapping("/payOrder/updateOrderStatus")
	public int updateOrderStatus(String orid, String payid, String mont, String bank, int isok, String ddzt, String orderType, String zffs,
            String note, String zhusid) throws Exception{
		
		return payorderService.updateOrderStatus(orid, payid, mont, bank, isok, ddzt, orderType, zffs, note, zhusid);
	}
	
	@RequestMapping("/payOrder/volidateTicketDayControl")
	public List volidateTicketDayControl(String orid) throws IllegalAccessException, InvocationTargetException{
		
		return payorderService.volidateTicketDayControl(orid);
	}
	
	@RequestMapping("/payOrder/volidateTicketTripControl")
	public List volidateTicketTripControl(String orid) throws IllegalAccessException, InvocationTargetException, ParseException{
		
		return payorderService.volidateTicketTripControl(orid);
	}
}
