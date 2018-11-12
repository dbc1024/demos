package com.ectrip.ticket.salesmanager.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.salesmanager.Ospbankpayeesettab;

public interface IOSPBankPayeeSetTabService {
	//分页
	public PaginationSupport findPage(int pageSize,int startIndex, String url);
	//模糊查询
	public PaginationSupport findPage2(String queryId,String queryMess,int pageSize,int startIndex, String url);
	//添加
	public void addNewBank(Ospbankpayeesettab bank);
	//修改
	public void updateBankInfo(Ospbankpayeesettab bank);

	//查询是否有已存在的帐号
	public List getCountByCou(String count);
}
