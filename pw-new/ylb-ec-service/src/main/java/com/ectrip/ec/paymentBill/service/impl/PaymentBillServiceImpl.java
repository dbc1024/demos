package com.ectrip.ec.paymentBill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.ec.paymentBill.dao.IPaymentBillDAO;
import com.ectrip.ec.paymentBill.service.IPaymentBillService;
@Service
public class PaymentBillServiceImpl implements IPaymentBillService{
	@Autowired
	private IPaymentBillDAO paymentBillDAO;

	@Override
	public List getPaymentBillByOrid(String orid) {
		return paymentBillDAO.getPaymentBillByOrid(orid);
	}

}
