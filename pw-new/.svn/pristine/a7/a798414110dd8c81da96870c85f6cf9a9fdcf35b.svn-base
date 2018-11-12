package com.ectrip.ticket.stocks.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.ticket.stocks.dao.idao.IStocksWareDAO;
import com.ectrip.ticket.stocks.service.iservice.IStocksWareService;

@Service("stocksWareService")
public class StocksWareService implements IStocksWareService{

	@Autowired
	private IStocksWareDAO stockswareDao;
	/**
	 * *
	 * Describe:判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @see com.ectrip.system.stocks.service.iservice.IStocksWareService#checkTicketware(java.util.List)
	 * @param list
	 * @return
	 * @author lijingrui
	 * Date:2013-8-30
	 */
	public int checkTicketware(List<Object[]> list){
		return stockswareDao.checkTicketware(list);
	}

	/**
	 * *
	 * Describe:保存当日库存销售信息
	 * @see com.ectrip.system.stocks.service.iservice.IStocksWareService#saveStockvolum(java.util.List)
	 * @param list
	 * @return
	 * @author lijingrui
	 * Date:2013-9-3
	 */
	public void saveStockvolum(List<Object[]> list){
		stockswareDao.saveStockvolum(list);
	}

	/**
	 * *
	 * Describe:旅行社用户判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @see com.ectrip.system.stocks.service.iservice.IStocksWareService#checkTicketwareCustom(java.util.List, java.lang.String)
	 * @param list  服务商   产品    数量     开始日期     截止日期
	 * @param usid  下单用户或者操作员所在的分社
	 * @return
	 * @author lijingrui
	 * Date:2014-12-26
	 */
	public int checkTicketwareCustom(List<Object[]> list,String usid){
		return stockswareDao.checkTicketwareCustom(list, usid);
	}

	/**
	 * *
	 * Describe:保存旅行社用户库存销售信息
	 * @see com.ectrip.system.stocks.service.iservice.IStocksWareService#saveStockVolumeCustom(java.util.List, java.lang.String)
	 * @param list
	 * @param usid  下单用户或者操作员所在的分社
	 * @author lijingrui
	 * Date:2014-12-26
	 */
	public boolean saveStockVolumeCustom(List<Object[]> list,String usid){
		return stockswareDao.saveStockVolumeCustom(list, usid);
	}


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
	public Map checkTicketwareCustom2(List<Object[]> list,String usid){
		return stockswareDao.checkTicketwareCustom2(list, usid);
	}

	/**
	 *
	 * Describe:判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @author:lijingrui
	 * @param list 服务商   产品    数量     开始日期     截止日期
	 * @return
	 * return:int
	 * Date:2013-8-30
	 */
	public Map checkTicketware2(List<Object[]> list){
		return stockswareDao.checkTicketware2(list);
	}

}

