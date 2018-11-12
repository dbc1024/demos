package com.ectrip.ticket.warehouse.service;

import java.util.List;

import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Kcpersonalticketdetailstab;
import com.ectrip.ticket.model.warehouse.Kcstationticketdetailstab;
import com.ectrip.ticket.model.warehouse.Kcstocksbilldetailstab;
import com.ectrip.ticket.model.warehouse.Kcstocksbilltab;
import com.ectrip.ticket.warehouse.dao.idao.IKcpersonHouseDAO;
import com.ectrip.ticket.warehouse.service.iservice.IKcpersonHouseService;

public class KcpersonHouseService implements IKcpersonHouseService{

	IKcpersonHouseDAO kcpersonDao;

	public IKcpersonHouseDAO getKcpersonDao() {
		return kcpersonDao;
	}

	public void setKcpersonDao(IKcpersonHouseDAO kcpersonDao) {
		this.kcpersonDao = kcpersonDao;
	}

	/**
	 * *
	 * Describe:显示出所有的票类信息
	 * @see com.ectrip.system.warehouse.dao.idao.IKcpersonHouseDAO#showAlledmticket(java.lang.String)
	 * @param iwarehouseid
	 * @return
	 * @author lijingrui
	 * Date:2012-9-3
	 */
	public List showAlledmticket(Long iwarehouseid) {
		return kcpersonDao.showAlledmticket(iwarehouseid);
	}

	/**
	 * *
	 * Describe:保存 IC卡初始入库
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#insertKcstock(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param stocks
	 * @param datailList
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-9-4
	 */
	public void insertKcstock(Kcstocksbilltab stocks,
							  List<Kcstocksbilldetailstab> datailList, Syslog syslog) {
		kcpersonDao.insertKcstock(stocks, datailList, syslog);
	}

	/**
	 * *
	 * Describe:根据仓库ID查找仓库有的票类型
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#getListTickByihouse(java.lang.Long)
	 * @param iwarehouseid
	 * @return
	 * @author lijingrui
	 * Date:2012-9-4
	 */
	public List getListTickByihouse(Long iwarehouseid) {
		return kcpersonDao.getListTickByihouse(iwarehouseid);
	}

	/**
	 * *
	 * Describe:根据仓库ID、产品ID显示仓库结存明细信息
	 * @see com.ectrip.system.warehouse.dao.idao.IKcpersonHouseDAO#findAllStationticket(long, long)
	 * @param istationoutid
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2012-9-5
	 */
	public List findAllStationticket(long istationoutid, long itickettypeid) {
		return kcpersonDao.findAllStationticket(istationoutid, itickettypeid);
	}

	/**
	 * *
	 * Describe:领用出库保存
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#addKcstation(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param kcstockill
	 * @param list
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-9-5
	 */
	public void addKcstation(Kcstocksbilltab kcstockill,List<Kcstationticketdetailstab> list,Syslog syslog){
		kcpersonDao.addKcstation(kcstockill, list, syslog);
	}

	/**
	 * *
	 * Describe:仓库调拨 保存
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#addKcDbchang(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param kcstockill
	 * @param list
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-9-7
	 */
	public void addKcDbchang(Kcstocksbilltab kcstockill,List<Kcstationticketdetailstab> list,Syslog syslog){
		kcpersonDao.addKcDbchang(kcstockill, list, syslog);
	}

	/**
	 * *
	 * Describe:作废出库 保存
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#addKcDbchang(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param kcstockill
	 * @param list
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-9-7
	 */
	public void addKczfcksonls(Kcstocksbilltab kcstockill,List<Kcstationticketdetailstab> list,Syslog syslog){
		kcpersonDao.addKczfcksonls(kcstockill, list, syslog);
	}


	/**
	 * Describe:余票入库  根据个人明细ID查找个人明细所有的票类型
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#getListTickByperson(java.lang.Long)
	 * @param ihandler
	 * @return
	 * @author aozhuozu
	 * Date:2012-9-10
	 */
	public List getListTickByperson(Long ihandler){
		return kcpersonDao.getListTickByperson(ihandler);
	}

	/**
	 * Describe:余票入库 根据个人结存明细ID、产品ID显示个人结存明细信息
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#findAllPersonalticket(java.lang.Long, java.lang.Long)
	 * @param ihandler
	 * @param itickettypeid
	 * @return
	 * @author aozhuozu
	 * Date:2012-9-10
	 */
	public List findAllPersonalticket(Long ihandler, Long itickettypeid){
		return kcpersonDao.findAllPersonalticket(ihandler, itickettypeid);
	}

	/**
	 * Describe:余票入库  保存
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#addKcpersonal(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param kcstockbill
	 * @param perlist
	 * @param syslog
	 * @author aozhuozu
	 * Date:2012-9-10
	 */
	public void addKcpersonal(Kcstocksbilltab kcstockbill,
							  List<Kcpersonalticketdetailstab> perlist, Syslog syslog){
		kcpersonDao.addKcpersonal(kcstockbill, perlist, syslog);
	}
}

