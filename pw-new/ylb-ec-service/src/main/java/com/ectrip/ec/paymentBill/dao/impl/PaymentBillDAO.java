package com.ectrip.ec.paymentBill.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.order.PaymentBill;
import com.ectrip.ec.paymentBill.dao.IPaymentBillDAO;

@Repository
public class PaymentBillDAO extends GenericDao implements IPaymentBillDAO {

	@Override
	public List<PaymentBill> getPaymentBillByOrid(String orid) {
		if (null != orid && !("").equals(orid)) {
			String hql = "from  PaymentBill pb where pb.orid = " + orid;
			List result = this.find(hql);
			return result;
		}
		return null;
	}

}
