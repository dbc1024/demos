package com.ectrip.ticket.salesmanager.service.impl;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospforcedrefundsettab;
import com.ectrip.ticket.salesmanager.dao.impl.OspForcedRefundSetTabDAO;
import com.ectrip.ticket.salesmanager.service.IOspForcedRefundSetTabService;

/**
 * Created By Jzhenhua,Time 2011-10-05 10:37
 *
 * @author lenovo
 */
public class OspForcedRefundSetTabService implements
		IOspForcedRefundSetTabService {

	private OspForcedRefundSetTabDAO ospforcedrefundsettabDao;

	public OspForcedRefundSetTabDAO getOspforcedrefundsettabDao() {
		return ospforcedrefundsettabDao;
	}

	public void setOspforcedrefundsettabDao(
			OspForcedRefundSetTabDAO ospforcedrefundsettabDao) {
		this.ospforcedrefundsettabDao = ospforcedrefundsettabDao;
	}

	/**
	 * 添加
	 */
	public void addOspForcedRefundSet(
			Ospforcedrefundsettab ospforcedrefundsettab,Syslog syslog) {
		this.ospforcedrefundsettabDao
				.addOspForcedRefundSet(ospforcedrefundsettab,syslog);
	}

	/**
	 * 删除
	 */
	public void delOspForcedRefundSet(
			Ospforcedrefundsettab ospforcedrefundsettab,Syslog syslog) {
		this.ospforcedrefundsettabDao
				.delOspForcedRefundSet(ospforcedrefundsettab,syslog);
	}

	/**
	 * 修改
	 */
	public void editOspForcedRefundSet(
			Ospforcedrefundsettab ospforcedrefundsettab,Syslog syslog) {
		this.ospforcedrefundsettabDao
				.editOspForcedRefundSet(ospforcedrefundsettab,syslog);
	}

	/**
	 * 获得所有数据
	 */
	public PaginationSupport getAllOspforcedrefundset(int pageSize,
													  int startIndex, String url) {
		return this.ospforcedrefundsettabDao.getAllOspforcedrefundset(pageSize, startIndex, url);
	}

	/**
	 * 获得当前最大ID
	 */
	public Long getMaxId() {
		return this.ospforcedrefundsettabDao.getMaxId();
	}

	/**
	 * 根据编号查询强制退票信息
	 */
	public Ospforcedrefundsettab getOspforcedrefundsetById(Long id) {
		return this.ospforcedrefundsettabDao.getOspforcedrefundsetById(id);
	}

	/**
	 * 搜索强制退票信息
	 */
	public PaginationSupport searchOspforcedrefundset(
			Ospforcedrefundsettab ospforcedrefundsettab, int pageSize,
			int startIndex, String url) {
		return this.ospforcedrefundsettabDao.searchOspforcedrefundset(ospforcedrefundsettab, pageSize,
				startIndex, url);
	}

}
