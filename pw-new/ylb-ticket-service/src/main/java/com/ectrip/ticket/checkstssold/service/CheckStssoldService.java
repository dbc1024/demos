package com.ectrip.ticket.checkstssold.service;

import com.ectrip.ticket.checkstssold.dao.idao.ICheckStssoldDAO;
import com.ectrip.ticket.checkstssold.service.iservice.ICheckStssoldService;
import com.ectrip.ticket.model.order.Stssalesvouchertab;

public class CheckStssoldService implements ICheckStssoldService{

	private ICheckStssoldDAO stssoldDao;

	public ICheckStssoldDAO getStssoldDao() {
		return stssoldDao;
	}

	public void setStssoldDao(ICheckStssoldDAO stssoldDao) {
		this.stssoldDao = stssoldDao;
	}

	/**
	 * *
	 * Describe:判断此票印刷号是否存在
	 * @see com.ectrip.checkstssold.dao.idao.ICheckStssoldDAO#getStssoldticketLookview(java.lang.String)
	 * @param szticketprintno
	 * @return
	 * @author lijingrui
	 * Date:Mar 13, 2012
	 */
	public boolean getStssoldticketLookview(String szticketprintno) {
		return stssoldDao.getStssoldticketLookview(szticketprintno);
	}

	/**
	 * *
	 * Describe:判断此票是否检票
	 * @see com.ectrip.checkstssold.dao.idao.ICheckStssoldDAO#getQuerystssold(java.lang.String)
	 * @param szticketprintno
	 * @return
	 * @author lijingrui
	 * Date:Mar 13, 2012
	 */
	public boolean getQuerystssold(String szticketprintno) {
		return stssoldDao.getQuerystssold(szticketprintno);
	}

	/**
	 * *
	 * Describe:显示此票的检票信息
	 * @see com.ectrip.checkstssold.dao.idao.ICheckStssoldDAO#showViewStssoldticket(java.lang.String)
	 * @param szticketprintno
	 * @return
	 * @author lijingrui
	 * Date:Mar 13, 2012
	 */
	public Stssalesvouchertab showViewStssoldticket(String szticketprintno) throws Exception  {
		return stssoldDao.showViewStssoldticket(szticketprintno);
	}

}

