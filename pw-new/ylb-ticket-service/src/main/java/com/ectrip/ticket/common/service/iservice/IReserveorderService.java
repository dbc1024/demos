package com.ectrip.ticket.common.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ticket.model.reserveorder.Reserveorderinfo;

public interface IReserveorderService extends IGenericService {

	/**
	 *
	 * Describe:保存预约信息
	 * @author:chenxinhao
	 * @param reserveorderinfo
	 * return:void
	 * Date:2015-11-13
	 */
	public void save(Reserveorderinfo reserveorderinfo);
	/**
	 *
	 * Describe:修改预约信息
	 * @author:chenxinhao
	 * @param reserveorderinfo
	 * return:void
	 * Date:2015-11-13
	 */
	public void update(Reserveorderinfo reserveorderinfo);
	/**
	 *
	 * Describe:根据订单号或者票号查询预约信息
	 * @author:chenxinhao
	 * @param orid 订单号或者票号
	 * @return
	 * return:Reserveorderinfo
	 * Date:2015-11-13
	 */
	public Reserveorderinfo selectOneByOrid(String orid);
	/**
	 *
	 * Describe:
	 * @author:chenxinhao
	 * @param orid 订单号或者票号
	 * return:void
	 * Date:2015-11-19
	 */
	public void delete(String orid);
}

