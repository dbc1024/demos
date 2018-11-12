package com.ectrip.ticket.permitenter.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Iomwarehouse;
import com.ectrip.ticket.permitenter.dao.IIomwareHouseDAO;
import com.ectrip.ticket.permitenter.service.iservice.IIomwareHouseService;

public class IomwareHouseService implements IIomwareHouseService{
	
	private IIomwareHouseDAO warehouseDao;
	
	public IIomwareHouseDAO getWarehouseDao() {
		return warehouseDao;
	}

	public void setWarehouseDao(IIomwareHouseDAO warehouseDao) {
		this.warehouseDao = warehouseDao;
	}

	/**
	 * *
	 * Describe:��ʾ�����еĲֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#getAllListwareHouse(java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public PaginationSupport getAllListwareHouse(String scenics,int pageSize,int startIndex, String url){
		return warehouseDao.getAllListwareHouse(scenics, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:��Ӳֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#insertWareHouse(com.ectrip.model.permitenter.Iomwarehouse, com.ectrip.model.syspar.Syslog)
	 * @param house
	 * @param syslog
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public void insertWareHouse(Iomwarehouse house,Syslog syslog){
		warehouseDao.insertWareHouse(house, syslog);
	}
	
	/**
	 * *
	 * Describe:�޸Ĳֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#updateWareHouse(com.ectrip.model.permitenter.Iomwarehouse, com.ectrip.model.syspar.Syslog)
	 * @param house
	 * @param syslog
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public void updateWareHouse(Iomwarehouse house,Syslog syslog){
		warehouseDao.updateWareHouse(house, syslog);
	}
	
	/**
	 * *
	 * Describe:ɾ���ֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#deleteWareHouse(com.ectrip.model.permitenter.Iomwarehouse, com.ectrip.model.syspar.Syslog)
	 * @param house
	 * @param syslog
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public void deleteWareHouse(Iomwarehouse house,Syslog syslog){
		warehouseDao.deleteWareHouse(house, syslog);
	}
	
	/**
	 * *
	 * Describe:�鿴�ֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#viewWarehouse(java.lang.Long)
	 * @param iwarehouseid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public Iomwarehouse viewWarehouse(Long iwarehouseid)  throws Exception {
		return warehouseDao.viewWarehouse(iwarehouseid);
	}
	
}

