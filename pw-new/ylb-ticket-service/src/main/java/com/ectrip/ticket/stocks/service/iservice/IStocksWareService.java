package com.ectrip.ticket.stocks.service.iservice;

import java.util.List;
import java.util.Map;

public interface IStocksWareService {

	/**
	 *
	 * Describe:判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @author:lijingrui
	 * @param list 服务商   产品    数量     开始日期     截止日期
	 * @return
	 * return:int
	 * Date:2013-8-30
	 */
	public int checkTicketware(List<Object[]> list);

	/**
	 *
	 * Describe:保存当日库存销售信息
	 * @author:lijingrui
	 * @param list
	 * @return
	 * Date:2013-9-2
	 */
	public void saveStockvolum(List<Object[]> list);

	/**
	 *
	 * Describe:旅行社用户判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @author:lijingrui
	 * @param list 服务商   产品    数量     开始日期     截止日期
	 * @param usid 下单用户或者操作员所在的分社
	 * @return
	 * return:int
	 * Date:2014-12-26
	 */
	public int checkTicketwareCustom(List<Object[]> list,String usid);

	/**
	 *
	 * Describe:旅行社用户判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @author:lijingrui
	 * @param list 服务商   产品    数量     开始日期     截止日期
	 * @param usid 下单用户或者操作员所在的分社
	 * @return
	 * return:int
	 * Date:2014-12-26
	 */
	public Map checkTicketwareCustom2(List<Object[]> list,String usid);

	/**
	 *
	 * Describe:判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @author:lijingrui
	 * @param list 服务商   产品    数量     开始日期     截止日期
	 * @return
	 * return:int
	 * Date:2013-8-30
	 */
	public Map checkTicketware2(List<Object[]> list);

	/**
	 *
	 * Describe:保存旅行社用户库存销售信息
	 * @author:lijingrui
	 * @param list
	 * @param usid 下单用户或者操作员所在的分社
	 * return:void
	 * Date:2014-12-26
	 */
	public boolean saveStockVolumeCustom(List<Object[]> list,String usid);

}

