package com.ectrip.ticket.warehouse.service;

import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;
import com.ectrip.ticket.warehouse.dao.idao.IHouseDAO;
import com.ectrip.ticket.warehouse.service.iservice.IHouseService;

public class HouseService implements IHouseService {

	IHouseDAO  houseDao;

	public IHouseDAO getHouseDao() {
		return houseDao;
	}

	public void setHouseDao(IHouseDAO houseDao) {
		this.houseDao = houseDao;
	}

	/**
	 * *
	 * Describe:获取票号前缀和票代码
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseService#ticketRuleView(java.lang.Long)
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2012-10-16
	 */
	public String ticketRuleView(Long itickettypeid){
		return houseDao.ticketRuleView(itickettypeid);
	}

	/**
	 * *
	 * Describe:判断票号输入是否正确
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseDAO#getShowtickrule(java.lang.String, java.lang.Long)
	 * @param ticketcode  票号
	 * @param itickettypeid  产品ID
	 * @return
	 * @author lijingrui
	 * Date:2012-10-16
	 */
	public boolean reShowtickrule(String ticketcode, Long itickettypeid) {
		return houseDao.reShowtickrule(ticketcode, itickettypeid);
	}

	/**
	 * *
	 * Describe:判断票号是否存在
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseService#retrieWarehouse(com.ectrip.model.warehouse.Iomstocksbilldetails)
	 * @param stocks
	 * @return
	 * @author lijingrui
	 * Date:2012-10-16
	 */
	public boolean retrieWarehouse(Iomstocksbilldetails stocks){
		return houseDao.retrieWarehouse(stocks);
	}

	/**
	 * *
	 * Describe:获取截止票号
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseService#getviewupendcode(java.lang.String, java.lang.Long)
	 * @param ticketcode
	 * @param iamount
	 * @return
	 * @author lijingrui
	 * Date:2012-10-16
	 */
	public String showViewupendcode(String ticketcode, Long iamount,Long itickettypeid) {
		return houseDao.showViewupendcode(ticketcode, iamount, itickettypeid);
	}
}

