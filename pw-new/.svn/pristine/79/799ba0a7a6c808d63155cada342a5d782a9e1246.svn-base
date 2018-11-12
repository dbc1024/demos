package com.ectrip.ticket.salesmanager.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;

public interface ITicketWinLimitsDAO extends IGenericDao {
	/**
	 * 查询所有窗口销售权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-6
	 */
	public PaginationSupport getTicketWinLimitsList(int page,int pageSize,String url);
	/**
	 * 查询所有窗口销售权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-6
	 */
	public PaginationSupport getTicketWinLimitsList(Long winid,int page,int pageSize,String url);
	/**
	 * 根据编号查询窗口销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinId
	 * @return
	 * return:Ospticketwinlimitstab
	 * Date:2011-10-6
	 */
	public Ospticketwinlimitstab queryTicketWinLimits(Long ticketwinId);
	/**
	 * 根据窗口编号查询窗口销售权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinId窗口编号
	 * @return
	 * return:Ospticketwinlimitstab
	 * Date:2011-10-6
	 */
	public List queryTicketWinLimitsList(Long ticketwinId);
	/**
	 * 窗口销售权限增加
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinlimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void insertTicketWinLimits(List ticketwinlimitslist,Syslog syslog);
	/**
	 * 窗口销售权限修改
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinlimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void updateTicketWinLimits(List ticketwinlimitslist,Syslog syslog);
	/**
	 * 窗口销售权限删除
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinlimitsId
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void deleteTicketWinLimits(Long ticketwinlimitsId,Syslog syslog);
}


