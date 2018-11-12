package com.ectrip.ticket.warehouse.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.warehouse.dao.idao.IStocksbillQueryDao;
import com.ectrip.ticket.warehouse.service.iservice.IStocksbillQueryService;


public class StocksbillQueryService implements IStocksbillQueryService{
	private IStocksbillQueryDao stocksbillQueryDao;

	/**
	 * Describe:根据输入条件进行单据查询
	 * @see com.ectrip.system.warehouse.service.iservice.IStocksbillQueryService#showStocksbill(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param bystockswayid
	 * @param ihandler
	 * @param istationinid
	 * @param istationoutid
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author aozhuozu
	 * Date:2012-8-28
	 */
	public PaginationSupport showStocksbill(Long bystockswayid,
											Long ihandler, Long istationinid, Long istationoutid, String startDate, String endDate,int page,int pageSize,String url){
		return stocksbillQueryDao.showStocksbill(bystockswayid,ihandler,istationinid,istationoutid,startDate,endDate,page,pageSize,url);
	}



	/**
	 * Describe:保存仓库初始化
	 * @auth:aozhuozu
	 * @param syslog
	 * return:void
	 * Date:2012-8-26
	 */
	public void saveInit(Syslog syslog,String initseason){
		stocksbillQueryDao.saveInit(syslog,initseason);
	}


	public IStocksbillQueryDao getStocksbillQueryDao() {
		return stocksbillQueryDao;
	}

	public void setStocksbillQueryDao(IStocksbillQueryDao stocksbillQueryDao) {
		this.stocksbillQueryDao = stocksbillQueryDao;
	}
}

