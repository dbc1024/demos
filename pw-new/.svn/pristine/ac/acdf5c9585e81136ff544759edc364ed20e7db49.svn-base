package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;

public interface IEsbticketWinService extends IGenericService{
	/**
	 * 
	 * Describe:显示所有的售票窗口信息  根据窗口名称查询
	 * @auth:lijingrui
	 * @param szticketwinname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-4
	 */
	public PaginationSupport getlistTicketWin(String szticketwinname,String scenics,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:添加售票窗口
	 * @auth:lijingrui
	 * @param ticketwin
	 * @param syslog
	 * return:void
	 * Date:2011-10-4
	 */
	public void insertTicketWin(Esbticketwintab ticketwin,Syslog syslog);
	
	/**
	 * 
	 * Describe:修改售票窗口
	 * @auth:lijingrui
	 * @param ticketwin
	 * @param syslog
	 * return:void
	 * Date:2011-10-4
	 */
	public void updateTicketWin(Esbticketwintab ticketwin,Syslog syslog);
	
	/**
	 * 
	 * Describe:删除售票窗口
	 * @auth:lijingrui
	 * @param ticketwin
	 * @param syslog
	 * return:void
	 * Date:2011-10-4
	 */
	public void deleteTicketWin(Esbticketwintab ticketwin,Syslog syslog);
	
	/**
	 * 
	 * Describe:根据ID查看售票窗口信息
	 * @auth:lijingrui
	 * @param iticketwinid
	 * @return
	 * return:Esbticketwintab
	 * Date:2011-10-4
	 */
	public Esbticketwintab getviewEsbticketWin(Long iticketwinid) throws Exception;
	
	/**
	 * 
	 * Describe:显示所有的售票点信息
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2011-10-4
	 */
	public List findListesbticket(String scenics);
	
	/**
	 * 
	 * Describe:根据售票ID查询
	 * @auth:lijingrui
	 * @param iticketwinid
	 * @return
	 * return:Esbticketwintab
	 * Date:2011-10-5
	 */
	public Esbticketwintab getszIpassword(String szipaddress);
	
	/**
	 * 查看有效的窗口
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeeId后台登录人id
	 * @return
	 * return:List
	 * Date:2011-10-6
	 */
	public List getAllTicketWinList(Long employeeId);
	
	/**
	 * 
	 * Describe:判断此售票窗口下是否有窗口设置
	 * @auth:lijingrui
	 * @param iticketwinid
	 * @return
	 * return:List
	 * Date:2011-10-14
	 */
	public boolean getlistEsbticketequied(Long iticketwinid);

	/**
	 * Describe:查询窗口拥有销售权限
	 * @param entity  售票窗口
	 * @param syslog  日志
	 */
	public void getTicketWinLimitsList(Long Iticketwinid);

	/**
	 * Describe:获取销售权限列表 根据窗口Id查询权限
	 * @param entity
	 */
	public PaginationSupport getWinPermissionByIticketWinId(Long iticketWinId,int page,int pageSize);

	/**
	 * 添加售票窗口权限
	 * @param Iticketwinid 窗口id
	 * @param Icrowdkindpriceids 价格id字符串
	 * @param syslog
	 */
	public void addWinPermission(Long Iticketwinid,String Icrowdkindpriceids,Syslog syslog);

	/**
	 * 修改售票窗口权限
	 * @param Iticketwinid 窗口id
	 * @param Icrowdkindpriceids 价格id字符串
	 * @param syslog
	 */
	public void updateTicketWinLimits(Long Iticketwinid,String Icrowdkindpriceids,Syslog syslog);

	/**
	 * 删除窗口权限
	 * @param ticketwinlimitsId 窗口权限表主键id
	 * @param syslog 日志
	 */
	public void deleteTicketWinLimits(Long iticketwinlimitsId, Syslog syslog);
}

