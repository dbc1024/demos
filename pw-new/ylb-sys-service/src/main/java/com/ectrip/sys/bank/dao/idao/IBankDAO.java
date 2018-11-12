package com.ectrip.sys.bank.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;

public interface IBankDAO extends IGenericDao{
	
	public int updateOrderStatus(String orid,String payid,String mont, String bank, int isok, String ddzt,
			String orderType,String zffs,String usid,String note)throws Exception;
	 
	public int updateOrderStatusWx(String orid,String payid,String mont, String bank, int isok, String ddzt,
			String orderType,String zffs,String usid,String note) throws Exception;
	

}

