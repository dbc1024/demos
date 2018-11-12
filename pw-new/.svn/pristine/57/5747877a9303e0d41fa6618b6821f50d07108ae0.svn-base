package com.ectrip.ec.paymentBill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.paymentBill.service.IPaymentBillService;

@RestController
@RequestMapping
public class PaymentBillController {
	
	@Autowired
	private IPaymentBillService paymentBillService;
	
	@GetMapping(value="v1/getPaymentBillByOrid")
	public List getPaymentBillByOrid(@RequestParam("orid") String orid) {
		return paymentBillService.getPaymentBillByOrid(orid);
	}
}
