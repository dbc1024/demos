package com.ectrip.sys.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.sys.bank.dao.idao.IBankDAO;
import com.ectrip.sys.bank.service.iservice.IBankService;

@RestController
public class BankController {
	
	@Autowired
	private IBankDAO bankDao;

	@RequestMapping("/bank/updateOrderStatus")
	public int updateOrderStatus(String orid,String payid,String mont, String bank, int isok, String ddzt,
			String orderType,String zffs,String usid,String note)throws Exception{
		
		return bankDao.updateOrderStatus(orid, payid, mont, bank, isok, ddzt, orderType,zffs, usid,note);
	}
	
	/**
	 * updateOrderStatus
	 */
}
