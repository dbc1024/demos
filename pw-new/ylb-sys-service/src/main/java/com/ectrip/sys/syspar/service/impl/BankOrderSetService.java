package com.ectrip.sys.syspar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Bankorder;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.IBankOrderSetDAO;
import com.ectrip.sys.syspar.service.IBankOrderSetService;

@Service
public class BankOrderSetService implements IBankOrderSetService {
	@Autowired
	private IBankOrderSetDAO bankordersetDao;
	/**
	 * 获取银行参数列表
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2012-3-28
	 */
	public List queryBankList(){
		return bankordersetDao.queryBankList();
	}
	/**
	 * 获取银行排序列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-28
	 */
	public PaginationSupport queryBankOrderList(int page,int pageSize,String url){
		return bankordersetDao.queryBankOrderList(page, pageSize, url);
	}
	
	/**
	 * 增加银行排序管理
	 * Describe:
	 * @auth:huangyuqi
	 * @param bankorder
	 * return:void
	 * Date:2012-3-28
	 */
	public void addBankOrder(Bankorder bankorder,Syslog syslog){
		bankordersetDao.addBankOrder(bankorder,syslog);
	}
	/**
	 * 修改银行排序管理
	 * Describe:
	 * @auth:huangyuqi
	 * @param bankorder
	 * return:void
	 * Date:2012-3-28
	 */
	public void updateBankOrder(Bankorder bankorder,Syslog syslog){
		bankordersetDao.updateBankOrder(bankorder,syslog);
	}
	/**
	 * 删除银行排序管理
	 * Describe:
	 * @auth:huangyuqi
	 * @param bankorder
	 * return:void
	 * Date:2012-3-28
	 */
	public void deleteBankOrder(Bankorder bankorder,Syslog syslog){
		bankordersetDao.deleteBankOrder(bankorder,syslog);
	}
	/**
	 * 查看银行排序管理
	 * Describe:
	 * @auth:huangyuqi
	 * @param bankid
	 * return:void
	 * Date:2012-3-28
	 */
	public Bankorder getBankOrder(String bankid){
		return bankordersetDao.getBankOrder(bankid);
	}
	
	/**
	 * 判断银行排序管理是事已经存在
	 * Describe:
	 * @auth:huangyuqi
	 * @param rowid行号
	 * @param colsid列号
	 * return:boolean
	 * Date:2012-3-28
	 */
	public boolean queryBankOrderIsUse(Long rowid,Long colsid){
		return bankordersetDao.queryBankOrderIsUse(rowid, colsid);
	}

}

