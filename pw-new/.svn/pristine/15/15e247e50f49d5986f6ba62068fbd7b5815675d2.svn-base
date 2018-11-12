package com.ectrip.ticket.warehouse.service;

import java.util.List;

import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iomstationticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;
import com.ectrip.ticket.warehouse.dao.idao.IWarehouseObsoleteDAO;
import com.ectrip.ticket.warehouse.service.iservice.IWarehouseObsoleteService;

public class WarehouseObsoleteService implements IWarehouseObsoleteService {

	IWarehouseObsoleteDAO warehouseObsoleteDao;

	public void setWarehouseObsoleteDao(IWarehouseObsoleteDAO warehouseObsoleteDao) {
		this.warehouseObsoleteDao = warehouseObsoleteDao;
	}

	public IWarehouseObsoleteDAO getWarehouseObsoleteDao() {
		return warehouseObsoleteDao;
	}

	/**
	 * *
	 * Describe:仓库明细表
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseObsoleteService#addPersonaldetails(java.util.List)
	 * @param stockList
	 * @author wangling
	 * Date:2012-7-23
	 */
	public void addPersonaldetails(List<Iomstocksbilldetails> stockList) {
		warehouseObsoleteDao.addPersonaldetails(stockList);

	}
	/**
	 * *
	 * Describe:保存单据信息操作
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseObsoleteService#insertstockbill(com.ectrip.model.warehouse.Iomstocksbill, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param stocks
	 * @param detailList
	 * @param syslog
	 * @return
	 * @author wangling
	 * Date:2012-7-23
	 */
	public boolean insertstockbill(Iomstocksbill stocks,
								   List<Iomstationticketdetails> detailList, Syslog syslog) {

		return warehouseObsoleteDao.insertstockbill(stocks, detailList, syslog);
	}
	/**
	 * *
	 * Describe:判断仓库结存明细表中是否存在该票段
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseObsoleteService#retriedStadetails(com.ectrip.model.warehouse.Iomstationticketdetails, long)
	 * @param stadeta
	 * @param istationoutid
	 * @return
	 * @author wangling
	 * Date:2012-7-23
	 */
	public boolean retriedStadetails(Iomstationticketdetails stadeta,
									 long istationoutid) {
		return warehouseObsoleteDao.retriedStadetails(stadeta, istationoutid);
	}

	/**
	 * *
	 * Describe:计算票数量
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseObsoleteService#showiamount(long, java.lang.String, java.lang.String)
	 * @param ticketid
	 * @param startcode
	 * @param endcode
	 * @return
	 * @author wangling
	 * Date:2012-7-23
	 */
	public long showiamount(long ticketid, String startcode, String endcode) {

		return warehouseObsoleteDao.showiamount(ticketid, startcode, endcode);
	}



}

