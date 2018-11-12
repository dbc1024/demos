package com.ectrip.ticket.warehouse.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.warehouse.dao.idao.IKcStocksbillQueryDao;
import com.ectrip.ticket.warehouse.service.iservice.IKcStocksbillQueryService;



public class KcStocksbillQueryService implements IKcStocksbillQueryService{
	private IKcStocksbillQueryDao kcstocksbillQueryDao;

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
	 * Date:2012-9-12
	 */
	public PaginationSupport showStocksbill(Long bystockswayid,
											Long ihandler, Long istationinid, Long istationoutid, String startDate, String endDate,int page,int pageSize,String url){
		return kcstocksbillQueryDao.showStocksbill(bystockswayid,ihandler,istationinid,istationoutid,startDate,endDate,page,pageSize,url);
	}



	/**
	 * Describe:保存仓库初始化
	 * @auth:aozhuozu
	 * @param syslog
	 * return:void
	 * Date:2012-9-12
	 */
	public void saveInit(Syslog syslog,String initseason){
		kcstocksbillQueryDao.saveInit(syslog,initseason);
	}



	public IKcStocksbillQueryDao getKcstocksbillQueryDao() {
		return kcstocksbillQueryDao;
	}

	public void setKcstocksbillQueryDao(IKcStocksbillQueryDao kcstocksbillQueryDao) {
		this.kcstocksbillQueryDao = kcstocksbillQueryDao;
	}

}

