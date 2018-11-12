package com.ectrip.ticket.salesmanager.service.impl;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.salesmanager.Ospbankpayeesettab;
import com.ectrip.ticket.salesmanager.dao.IOSPBankPayeeSetTabDAO;
import com.ectrip.ticket.salesmanager.service.IOSPBankPayeeSetTabService;

/**
 * @author  yangyang
 * @version 银行缴款设置操作类
 */
public class OSPBankPayeeSetTabService implements IOSPBankPayeeSetTabService{

	IOSPBankPayeeSetTabDAO bankPayeeDao;



	public IOSPBankPayeeSetTabDAO getBankPayeeDao() {
		return bankPayeeDao;
	}

	public void setBankPayeeDao(IOSPBankPayeeSetTabDAO bankPayeeDao) {
		this.bankPayeeDao = bankPayeeDao;
	}

	/**
	 * 功能 ：显示缴款银行列表
	 */
	public PaginationSupport findPage(int pageSize,int startIndex, String url) {
		// TODO Auto-generated method stub
		return bankPayeeDao.findPage(pageSize, startIndex, url);
	}

	/**
	 * 功能 ：按条件模糊查询
	 * @param  queryId  查询银行ID
	 * @param  queryMess 查询名称所包含的字符
	 */
	public PaginationSupport findPage2(String queryId, String queryMess,
									   int pageSize, int startIndex, String url) {
		// TODO Auto-generated method stub
		return bankPayeeDao.findPage2(queryId, queryMess, pageSize, startIndex, url);
	}

	/**
	 * 功能 : 添加新的银行信息
	 * @param bank  缴款银行
	 */
	public void addNewBank(Ospbankpayeesettab bank) {
		// TODO Auto-generated method stub
		if(bank!=null){
			bankPayeeDao.addNewBank(bank);
		}
	}

	/**
	 * 功能 ：修改银行信息
	 * @param bank 银行信息
	 */
	public void updateBankInfo(Ospbankpayeesettab bank) {
		// TODO Auto-generated method stub
		if(bank!=null){
			bankPayeeDao.updateBankInfo(bank);
		}
	}

	//判断是否有已存在帐号
	public List getCountByCou(String count) {
		// TODO Auto-generated method stub
		//	String sql=" from Ospbankpayeesettab where szbankaccount='"+count+"'";
		return bankPayeeDao.getCountByCou(count);
	}
}
