package com.ectrip.ticket.warehouse.service.iservice;

import java.util.List;

import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iompersonalticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;


public interface IWarehouseTicketInService {

	/**
	 * Describe:根据经手人来查询某服务商下的产品信息
	 * @param ihandler  经手人
	 * @return
	 * return:List
	 * @author aozhuozu
	 * Date:2012-7-13
	 */
	public List showListticket(Long ihandler);


	/**
	 * Describe:显示个人结存明细表
	 * @param ihandler 经手人    itickettypeid  票信息键值
	 * @auth:aozhuozu
	 * @return
	 * return:List
	 * Date:2012-7-16
	 */
	public List findAllPersonalticket(Long ihandler, Long itickettypeid);


	/**
	 * Describe:界面层判断票号是否在指定票段内
	 * @param ticketid 票信息键值    ticketCode 起始票号     idetailsid 个人明细表主键
	 * @auth:aozhuozu
	 * @return
	 * return:Iomstocksbill
	 * Date:2012-7-18
	 */
	public boolean checkTicketcodeRange(long ticketid,String ticketCode, long idetailsid);


	/**
	 * Describe:界面层获取起始票号修改后的票数量
	 * @param ticketid 票信息键值    startcode 起始票号     endcode 截止票号
	 * @auth:aozhuozu
	 * @return
	 * return:long
	 * Date:2012-7-20
	 */
	public long showiamount(long ticketid,String startcode, String endcode);


	/**
	 * Describe:根据个人明细表主键ID得到对应记录
	 * @param idetailsid 个人明细表主键
	 * @auth:aozhuozu
	 * @return
	 * return:List
	 * Date:2012-7-19
	 */
	public List findPersonalticket(Long idetailsid);


	/**
	 * Describe:判断票号是否已经存在
	 * @param staDetails 一条个人明细记录    ihandler 经手人
	 * @auth:aozhuozu
	 * @return
	 * return:boolean
	 * Date:2012-7-20
	 */
	public boolean retrieWarehouse(Iompersonalticketdetails staDetails,long ihandler);


	/**
	 * Describe:保存
	 * @param stocks 单据信息属性   datailList 单据明细信息属性   syslog 日志    bystockswayid 操作方式  入库
	 * @auth:aozhuozu
	 * @return
	 * return:boolean
	 * Date:2012-7-23
	 */
	public boolean insertstockbill(Iomstocksbill stocks,List<Iompersonalticketdetails> datailList,Syslog syslog,Long bystockswayid) throws Exception;

}