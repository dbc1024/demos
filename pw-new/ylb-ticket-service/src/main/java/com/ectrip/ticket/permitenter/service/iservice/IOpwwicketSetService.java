package com.ectrip.ticket.permitenter.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Opwwicketsettab;

public interface IOpwwicketSetService {
	/**
	 * 
	 * Describe:显示所有的检票设置信息
	 * @auth:lijingrui
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-5
	 */
	public PaginationSupport getlistwicketset(String scenics,int pageSize,int startIndex, String url); 
	
	/**
	 * 
	 * Describe:添加检票设置信息
	 * @auth:lijingrui
	 * @param wicketset
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void insertwicketset(Opwwicketsettab wicketset,Syslog syslog);
	
	/**
	 * 
	 * Describe:修改检票设置信息
	 * @auth:lijingrui
	 * @param wicketset
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void updatewicketset(Opwwicketsettab wicketset,Syslog syslog);
	
	/**
	 * 
	 * Describe:删除检票设置信息
	 * @auth:lijingrui
	 * @param wicketset
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void deletewicketset(Opwwicketsettab wicketset,Syslog syslog);
	
	/**
	 * 
	 * Describe:根据ID查看检票设置
	 * @auth:lijingrui
	 * @param iwicketsetid
	 * @return
	 * return:Opwwicketsettab
	 * Date:2011-10-5
	 */
	public Opwwicketsettab getviewOpwicketID(Long iwicketsetid) throws Exception;
	
	/**
	 * 
	 * Describe:显示圆门信息
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2011-10-5
	 */
	public List getgardengatelist(String scenics);
	
	
	/**
	 * Describe: 登录人所属的服务商显示所属的票类型
	 * @auth:ChaoYuHang
	 * @param scenic 登录人所属的服务商
	 * @return
	 * return:String
	 * Date:2012-8-23
	 */
	public String getedmtickettypelist(String scenic);
	
	/**
	 * Describe: 根据票类型ID获得所有对应的服务商的园门
	 * @auth:ChaoYuHang
	 * @param itickettypeid 票类型ID
	 * @return
	 * return:String
	 * Date:2012-8-23
	 */
	public List getGardengate(Long itickettypeid);
	
	/**
	 * Describe: 初始化票类型服务商的所有园门
	 * @auth:ChaoYuHang
	 * @param scenic 登录人所属的服务商
	 * @return
	 * return:List
	 * Date:2012-8-23
	 */
	public List getInitGardengate(String scenic);
	
	/**
	 * 
	 * Describe:显示套票的子产品
	 * @auth:lijingrui
	 * @param itickettypeid
	 * @return
	 * return:String
	 * Date:2011-10-22
	 */
	public String getviewedmticket(Long itickettypeid);
	
	/**
	 * 
	 * Describe:根据园门id、产品id、子产品id判断存在 （添加时候）
	 * @auth:lijingrui
	 * @return
	 * return:boolean
	 * Date:Nov 11, 2011
	 */
	public boolean showgradeTicket(Long igardengateid,Long itickettypeid,Long izticktypeid);
	
	public List showgradeTicket(Long igardengateid,String bywicketconsumetype);
}

