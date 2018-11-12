package com.ectrip.ticket.statistics.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.statistics.dao.idao.ICreditDAO;
import com.ectrip.ticket.statistics.service.iservice.ICreditService;

public class CreditService implements ICreditService{

	private ICreditDAO creditdetailDao;

	public ICreditDAO getCreditdetailDao() {
		return creditdetailDao;
	}

	public void setCreditdetailDao(ICreditDAO creditdetailDao) {
		this.creditdetailDao = creditdetailDao;
	}

	/**
	 * *
	 * Describe:查看用户信誉度
	 * @see com.ectrip.system.statistics.dao.idao.ICreditDAO#showAllcreditList(java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Jun 18, 2012
	 */
	public PaginationSupport showAllcreditList(String usid, int pageSize,
											   int startIndex, String url) {
		return creditdetailDao.showAllcreditList(usid, pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:查看用户信誉度消费明细
	 * @see com.ectrip.system.statistics.dao.idao.ICreditDAO#showAllcreditDetailList(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param orid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Jun 18, 2012
	 */
	public PaginationSupport showAllcreditDetailList(String usid, String orid,
													 int pageSize, int startIndex, String url) {
		return creditdetailDao.showAllcreditDetailList(usid, orid, pageSize, startIndex, url);
	}

}

