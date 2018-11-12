package com.ectrip.ec.order.service.iservice;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.sys.model.employee.Esfemployeetab;

public interface ITourcardTicketOrderService {
	
	/**
	 * 描述：旅游卡入园订单分页查询
	 * @param esfemployeetab
	 * @param order
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @throws RuntimeException
	 * @throws Exception
	 */
	public PaginationSupport queryOrderInfoByPage(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url,String cardNumber,String cardName,String profitNum) throws RuntimeException,Exception;
	
	/**
	 * 查询所有订单
	 */
	public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) throws Exception;

}
