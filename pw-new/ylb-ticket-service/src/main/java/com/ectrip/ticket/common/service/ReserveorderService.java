package com.ectrip.ticket.common.service;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.common.dao.idao.IReserveorderDao;
import com.ectrip.ticket.common.service.iservice.IReserveorderService;
import com.ectrip.ticket.model.reserveorder.ReserveInfo;
import com.ectrip.ticket.model.reserveorder.Reserveorderinfo;

public class ReserveorderService extends GenericService implements
		IReserveorderService {

	private IReserveorderDao reserveorderDao;

	public IReserveorderDao getReserveorderDao() {
		return reserveorderDao;
	}

	public void setReserveorderDao(IReserveorderDao reserveorderDao) {
		this.reserveorderDao = reserveorderDao;
	}

	/**
	 *
	 * Describe:保存预约信息
	 * @author:chenxinhao
	 * @param reserveorderinfo
	 * return:void
	 * Date:2015-11-13
	 */
	public void save(Reserveorderinfo reserveorderinfo){
		this.reserveorderDao.saveReserveorderinfo(reserveorderinfo);
	}
	/**
	 *
	 * Describe:修改预约信息
	 * @author:chenxinhao
	 * @param reserveorderinfo
	 * return:void
	 * Date:2015-11-13
	 */
	public void update(Reserveorderinfo reserveorderinfo){
		this.reserveorderDao.updateReserveorderinfo(reserveorderinfo);
	}
	/**
	 *
	 * Describe:根据订单号或者票号查询预约信息
	 * @author:chenxinhao
	 * @param orid 订单号或者票号
	 * @return
	 * return:Reserveorderinfo
	 * Date:2015-11-13
	 */
	public Reserveorderinfo selectOneByOrid(String orid){
		Reserveorderinfo reserveorderinfo = this.reserveorderDao.selectOneByOrid(orid);
		if(reserveorderinfo != null){
			String dataJson = reserveorderinfo.getDatajson();
			ReserveInfo reserveInfo = JSON.parseObject(dataJson, ReserveInfo.class);
			reserveorderinfo.setReserveInfo(reserveInfo);
		}
		return reserveorderinfo;
	}
	/**
	 *
	 * Describe:
	 * @author:chenxinhao
	 * @param orid 订单号或者票号
	 * return:void
	 * Date:2015-11-19
	 */
	public void delete(String orid){
		this.reserveorderDao.deleteReserveorderinfo(orid);
	}
}

