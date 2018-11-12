package com.ectrip.sys.bank.service;

import com.ectrip.sys.bank.dao.idao.IBankDAO;
import com.ectrip.sys.bank.service.iservice.IBankService;

public class BankService implements IBankService{
	IBankDAO bankDao;
	
	
	public IBankDAO getBankDao() {
		return bankDao;
	}


	public void setBankDao(IBankDAO bankDao) {
		this.bankDao = bankDao;
	}


	public int updateOrderStatus(String orid,String payid,String mont, String bank, int isok, String ddzt,
			String orderType,String zffs,String zfusid) throws Exception{
		return bankDao.updateOrderStatus(orid, payid, mont, bank, isok, ddzt, orderType,zffs, zfusid,"");
	}
	
}

