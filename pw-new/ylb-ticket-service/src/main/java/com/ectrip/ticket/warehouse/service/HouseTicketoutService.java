package com.ectrip.ticket.warehouse.service;

import java.util.List;

import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iomstationticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;
import com.ectrip.ticket.warehouse.dao.idao.IHouseTicketoutDao;
import com.ectrip.ticket.warehouse.service.iservice.IHouseTicketoutService;

public class HouseTicketoutService implements IHouseTicketoutService {
	private IHouseTicketoutDao houseTicketoutDao;

	public IHouseTicketoutDao getHouseTicketoutDao() {
		return houseTicketoutDao;
	}

	public void setHouseTicketoutDao(IHouseTicketoutDao houseTicketoutDao) {
		this.houseTicketoutDao = houseTicketoutDao;
	}
	/**
	 *
	 * Describe:所有仓库结存明细信息
	 * @see com.ectrip.system.warehouse.service.iservice.IHouseTicketoutService#findAllStationticket(long, long)
	 * @param istationoutid	仓库ID
	 * @param itickettypeid	票类型ID
	 * @return
	 * @author chenxinhao
	 * Date:2012-7-13
	 */
	public List findAllStationticket(long istationoutid, long itickettypeid) {
		return houseTicketoutDao.findAllStationticket(istationoutid, itickettypeid);
	}
	/**
	 *
	 * Describe:检查票号是否在该票段范围内
	 * @see com.ectrip.system.warehouse.service.iservice.IHouseTicketoutService#checkTicketcodeRange(java.lang.String, java.lang.String)
	 * @param ticketid	   票ID
	 * @param ticketCode 待检查票号
	 * @param idetailsid 仓库结存明细ID
	 * @return
	 * @author chenxinhao
	 * Date:2012-7-17
	 */
	public boolean checkTicketcodeRange(long ticketid,String ticketCode, long idetailsid) {
		return houseTicketoutDao.checkTicketcodeRange(ticketid,ticketCode, idetailsid);
	}
	/**
	 *
	 * Describe:计算票数量
	 * @see com.ectrip.system.warehouse.service.iservice.IHouseTicketoutService#showiamount(long, java.lang.String, java.lang.String)
	 * @param ticketid	起始票号
	 * @param startcode	截止票号
	 * @param endcode
	 * @return
	 * @author chenxinhao
	 * Date:2012-7-19
	 */
	public long showiamount(long ticketid,String startcode, String endcode) {
		return houseTicketoutDao.showiamount(ticketid,startcode, endcode);
	}
	/**
	 *
	 * Describe:判断仓库结存明细表中是否存在该票段
	 * @see com.ectrip.system.warehouse.service.iservice.IHouseTicketoutService#retriedStadetails(com.ectrip.model.warehouse.Iomstationticketdetails, long)
	 * @param stadeta
	 * @param istationoutid
	 * @return
	 * @author chenxinhao
	 * Date:2012-7-20
	 */
	public boolean retriedStadetails(Iomstationticketdetails stadeta,
									 long istationoutid) {
		return houseTicketoutDao.retriedStadetails(stadeta, istationoutid);
	}
	/**
	 *
	 * Describe:领用出库 保存
	 * @see com.ectrip.system.warehouse.service.iservice.IHouseTicketoutService#insertstockbill(com.ectrip.model.warehouse.Iomstocksbill, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param stocks
	 * @param detailList
	 * @param syslog
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-7-20
	 */
	public boolean insertstockbill(Iomstocksbill stocks,
								   List<Iomstationticketdetails> detailList, Syslog syslog) throws Exception{
		return houseTicketoutDao.insertstockbill(stocks, detailList, syslog);
	}
	/**
	 *
	 * Describe:仓库明细表 个人明细表 添加和票段整理
	 * @see com.ectrip.system.warehouse.service.iservice.IHouseTicketoutService#addPersonaldetails(java.util.List)
	 * @param stockList
	 * @author chenxinhao
	 * Date:2012-7-20
	 */
	public void addPersonaldetails(List<Iomstocksbilldetails> stockList) {
		houseTicketoutDao.addPersonaldetails(stockList);
	}
	/**
	 *
	 * Describe:获取截止票号
	 * @see com.ectrip.system.warehouse.service.iservice.IHouseTicketoutService#showViewupendcode(java.lang.String, java.lang.Long, java.lang.Long)
	 * @param ticketcode
	 * @param iamount
	 * @param itickettypeid
	 * @return
	 * @author chenxinhao
	 * Date:2012-7-23
	 */
	public String showViewupendcode(String ticketcode, Long iamount,
									Long itickettypeid) {
		return houseTicketoutDao.showViewupendcode(ticketcode, iamount, itickettypeid);
	}
	/**
	 *
	 * Describe:根据服务商id查找子票信息
	 * @see com.ectrip.system.warehouse.service.iservice.IHouseTicketoutService#showAllzticket(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-1
	 */
	public List showAllzticket(Long iscenicid) {
		return houseTicketoutDao.showAllzticket(iscenicid);
	}

}

