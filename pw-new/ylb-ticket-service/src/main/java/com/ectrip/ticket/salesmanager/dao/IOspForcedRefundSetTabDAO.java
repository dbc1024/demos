package com.ectrip.ticket.salesmanager.dao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospforcedrefundsettab;
/**
 * Created by Jzhenhua,Time 2011-10-05 10:03 <br>
 * 强制退票
 *
 * @author lenovo
 */
public interface IOspForcedRefundSetTabDAO extends IGenericDao {

	/**
	 * 增加强制退票信息
	 */
	public void addOspForcedRefundSet(
			Ospforcedrefundsettab ospforcedrefundsettab,Syslog syslog);

	/**
	 * 删除强制退票信息
	 */
	public void delOspForcedRefundSet(
			Ospforcedrefundsettab ospforcedrefundsettab,Syslog syslog);

	/**
	 * 修改强制退票信息
	 */
	public void editOspForcedRefundSet(
			Ospforcedrefundsettab ospforcedrefundsettab,Syslog syslog);

	/**
	 * 根据ID查询强制退票信息
	 *
	 * @param id
	 * @return
	 */
	public Ospforcedrefundsettab getOspforcedrefundsetById(Long id)
			throws Exception;

	/**
	 * 获取所有强制退票信息
	 *
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 */
	public PaginationSupport getAllOspforcedrefundset(int pageSize,
													  int startIndex, String url) throws Exception;

	/**
	 * 搜索强制退票信息
	 *
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 */
	public PaginationSupport searchOspforcedrefundset(
			Ospforcedrefundsettab ospforcedrefundsettab, int pageSize,
			int startIndex, String url) throws Exception;

	/**
	 * 获取当前最大ID
	 *
	 * @return
	 */
	public Long getMaxId();
}
