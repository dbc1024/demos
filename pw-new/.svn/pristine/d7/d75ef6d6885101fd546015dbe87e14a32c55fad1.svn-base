package com.ectrip.ticket.provider.service;


import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketequiptab;

public interface IESBTicketEquipTabService  {
	//根据窗口ID列出所有的窗口设备
	public PaginationSupport findPage(Long winid,int pageSize,int startIndex, String url);
	//根据条件模糊查询
	public PaginationSupport findPage2(String queryId,String queryMess,Long winid,Esbticketequiptab equip,int pageSize,int startIndex, String url);
	//修改设备信息
	public void updateEquip(Esbticketequiptab equip,Syslog syslog);
	//添加新的窗口设备
	public void addNewEquip(Esbticketequiptab equip,Syslog syslog);
	
	/**
	 * 
	 * Describe:刪除窗口设备信息
	 * @auth:lijingrui
	 * @param equip
	 * @param syslog
	 * return:void
	 * Date:Dec 1, 2011
	 */
	public void delEquip(Esbticketequiptab equip,Syslog syslog);
	
	//获取所有的门票站点
	public List getTicketStation();
	//获取所有的服务商
	public List getScenics();
	//获取所有的设备类型
	public List getEquipTypeList();
	
	//查询是否存在此设备
	public List selectEquipByName(String ename);
}
 