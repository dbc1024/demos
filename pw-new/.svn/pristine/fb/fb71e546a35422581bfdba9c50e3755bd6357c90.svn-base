package com.ectrip.ticket.warehouse.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Kcpersonalticketdetailstab;
import com.ectrip.ticket.model.warehouse.Kcstationticketdetailstab;
import com.ectrip.ticket.model.warehouse.Kcstocksbilldetailstab;
import com.ectrip.ticket.model.warehouse.Kcstocksbilltab;

public interface IKcpersonHouseDAO extends IGenericDao{

	/**
	 *
	 * Describe:显示出所有的票类信息
	 * @auth:lijingrui
	 * @param iwarehouseid
	 * @return
	 * return:List
	 * Date:2012-9-3
	 */
	public List showAlledmticket(Long iwarehouseid);

	/**
	 *
	 * Describe:IC卡初始入库保存
	 * @auth:lijingrui
	 * @param stocks
	 * @param datailList
	 * @param syslog
	 * return:void
	 * Date:2012-9-4
	 */
	public void insertKcstock(Kcstocksbilltab stocks,List<Kcstocksbilldetailstab> datailList,Syslog syslog);

	/**
	 *
	 * Describe:根据仓库ID查找仓库有的票类型
	 * @auth:lijingrui
	 * @param iwarehouseid
	 * @return
	 * return:List
	 * Date:2012-9-4
	 */
	public List getListTickByihouse(Long iwarehouseid);

	/**
	 *
	 * Describe:根据仓库ID、产品ID显示仓库结存明细信息
	 * @auth:lijingrui
	 * @param istationoutid
	 * @param itickettypeid
	 * @return
	 * return:List
	 * Date:2012-9-5
	 */
	public List findAllStationticket(long istationoutid, long itickettypeid);

	/**
	 *
	 * Describe:领用出库保存
	 * @auth:lijingrui
	 * @param kcstockill
	 * @param list
	 * @param syslog
	 * return:void
	 * Date:2012-9-5
	 */
	public void addKcstation(Kcstocksbilltab kcstockill,List<Kcstationticketdetailstab> list,Syslog syslog);

	/**
	 *
	 * Describe:仓库调拨 保存
	 * @auth:lijingrui
	 * @param kcstockill
	 * @param list
	 * @param syslog
	 * return:void
	 * Date:2012-9-7
	 */
	public void addKcDbchang(Kcstocksbilltab kcstockill,List<Kcstationticketdetailstab> list,Syslog syslog);

	/**
	 *
	 * Describe:作废出库 保存
	 * @auth:lijingrui
	 * @param kcstockill
	 * @param list
	 * @param syslog
	 * return:void
	 * Date:2012-9-8
	 */
	public void addKczfcksonls(Kcstocksbilltab kcstockill,List<Kcstationticketdetailstab> list,Syslog syslog);

	/**
	 *
	 * Describe:根据个人明细ID查找个人明细所有的票类型
	 * @auth:aozhuozu
	 * @param ihandler
	 * @return
	 * return:List
	 * Date:2012-9-10
	 */
	public List getListTickByperson(Long ihandler);

	/**
	 * Describe:根据个人结存明细ID、产品ID显示个人结存明细信息
	 * @auth:aozhuozu
	 * @param ihandler
	 * @param itickettypeid
	 * @return
	 * return:List
	 * Date:2012-9-10
	 */
	public List findAllPersonalticket(Long ihandler, Long itickettypeid);

	/**
	 * Describe:余票入库  保存
	 * @auth:aozhuozu
	 * @param kcstockbill
	 * @param perlist
	 * @param syslog
	 * return:void
	 * Date:2012-9-10
	 */
	public void addKcpersonal(Kcstocksbilltab kcstockbill,
							  List<Kcpersonalticketdetailstab> perlist, Syslog syslog);

}

