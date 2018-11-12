package com.ectrip.ticket.salesmanager.service.impl;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.ticket.salesmanager.dao.ITicketWinLimitsDAO;
import com.ectrip.ticket.salesmanager.service.ITicketWinLimitsService;

public class TicketWinLimitsService implements ITicketWinLimitsService {
	private ITicketWinLimitsDAO ticketwinlimitsDao;

	public ITicketWinLimitsDAO getTicketwinlimitsDao() {
		return ticketwinlimitsDao;
	}
	public void setTicketwinlimitsDao(ITicketWinLimitsDAO ticketwinlimitsDao) {
		this.ticketwinlimitsDao = ticketwinlimitsDao;
	}
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
	public PaginationSupport getTicketWinLimitsList(int page,int pageSize,String url){
		return ticketwinlimitsDao.getTicketWinLimitsList(page, pageSize, url);
	}
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
	public PaginationSupport getTicketWinLimitsList(Long winid,int page,int pageSize,String url){
		return ticketwinlimitsDao.getTicketWinLimitsList(winid,page, pageSize, url);
	}
	/**
	 * 根据编号查询窗口销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinId
	 * @return
	 * return:Ospticketwinlimitstab
	 * Date:2011-10-6
	 */
	public Ospticketwinlimitstab queryTicketWinLimits(Long ticketwinId){
		return ticketwinlimitsDao.queryTicketWinLimits(ticketwinId);
	}
	/**
	 * 根据窗口编号查询窗口销售权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinId窗口编号
	 * @return
	 * return:Ospticketwinlimitstab
	 * Date:2011-10-6
	 */
	public List queryTicketWinLimitsList(Long ticketwinId){
		return ticketwinlimitsDao.queryTicketWinLimitsList(ticketwinId);
	}
	/**
	 * 窗口销售权限增加
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinlimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void insertTicketWinLimits(List ticketwinlimits,Syslog syslog){

		ticketwinlimitsDao.insertTicketWinLimits(ticketwinlimits, syslog);
	}
	/**
	 * 窗口销售权限修改
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinlimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void updateTicketWinLimits(List ticketwinlimits,Syslog syslog){
		ticketwinlimitsDao.updateTicketWinLimits(ticketwinlimits, syslog);
	}
	/**
	 * 窗口销售权限删除
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinlimitsId
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void deleteTicketWinLimits(Long ticketwinlimitsId,Syslog syslog){
		ticketwinlimitsDao.deleteTicketWinLimits(ticketwinlimitsId, syslog);
	}

}

