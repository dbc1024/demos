package com.ectrip.sys.syspar.service.impl;

import com.ectrip.sys.syspar.dao.IReceiptDAO;
import com.ectrip.sys.syspar.service.IReceiptService;

public class ReceiptService implements  IReceiptService{
	IReceiptDAO receiptDao;

	public String getSalesVoucher(String isalevoucherid, String iscenicid,int type) {
		return receiptDao.getSalesVoucher(isalevoucherid, iscenicid,type);
	}
	public String getordermessage(String orid, String iscenicid,int type) {
		return receiptDao.getordermessage(orid, iscenicid,type);
	}
	public IReceiptDAO getReceiptDao() {
		return receiptDao;
	}

	public void setReceiptDao(IReceiptDAO receiptDao) {
		this.receiptDao = receiptDao;
	}

}
