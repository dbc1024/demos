package com.ectrip.ec.paymentBill.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface IPaymentBillDAO extends IGenericDao{

	List getPaymentBillByOrid(String orid);

}
