package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketequiptab;
import com.ectrip.ticket.provider.dao.impl.ESBTicketEquipTabDAO;
import com.ectrip.ticket.provider.service.IESBTicketEquipTabService;


/**
 * @author  yangyang
 * @version 窗口设备管理操作类
 * 
 */
@Service
public class ESBTicketEquipTabService  implements IESBTicketEquipTabService {

	ESBTicketEquipTabDAO ticketEquipDao;
	
	
	public ESBTicketEquipTabDAO getTicketEquipDao() {
		return ticketEquipDao;
	}
	public void setTicketEquipDao(ESBTicketEquipTabDAO ticketEquipDao) {
		this.ticketEquipDao = ticketEquipDao;
	}
 
	/**
	 * 功能 ：根据窗口ID显示所有的窗口设备
	 * @param winid   窗口ID
	 */
	public PaginationSupport findPage(Long winid, int pageSize, int startIndex,String url) {
		return ticketEquipDao.findPage(winid, pageSize, startIndex, url);
	}
	
	/**
	 * 功能 ：根据条件模糊查询
	 * @param queryId  查询编号
	 * @param queryMess  查询的名称中所包含的字符
	 * @param winid    窗口ID
	 * @param equip    窗口设备
	 */
	public PaginationSupport findPage2(String queryId, String queryMess,Long winid,
			Esbticketequiptab equip, int pageSize, int startIndex, String url) {
		return ticketEquipDao.findPage2(queryId, queryMess,winid, equip, pageSize, startIndex, url);
	}
	
	/**
	 * 功能 ：添加新的窗口设备
	 */
	public void addNewEquip(Esbticketequiptab equip,Syslog syslog) {
		ticketEquipDao.addNewEquip(equip,syslog);
	}
	
	/**
	 * 功能 ：修改设备信息
	 * @param  equip  窗口设备
	 */
	public void updateEquip(Esbticketequiptab equip,Syslog syslog) {
		ticketEquipDao.updateEquip(equip,syslog);
	}
	
	/**
	 * *
	 * Describe:刪除窗口设备信息
	 * @see com.ectrip.system.provider.dao.idao.IESBTicketEquipTabDAO#delEquip(com.ectrip.model.provider.Esbticketequiptab, com.ectrip.model.syspar.Syslog)
	 * @param equip
	 * @param syslog
	 * @author lijingrui
	 * Date:Dec 1, 2011
	 */
	public void delEquip(Esbticketequiptab equip,Syslog syslog){
		ticketEquipDao.delEquip(equip, syslog);
	}
	
	/**
	 * 功能 ：获取所有的设备类型
	 * @return  List 设备类型列表
	 */
	public List getEquipTypeList() {
		return ticketEquipDao.getEquipTypeList();
	}
	
	/**
	 * 功能 ：获取所有的服务商
	 * @return List 服务商列表
	 */
	public List getScenics() {
		return ticketEquipDao.getScenics();
	}
	
	/**
	 * 功能 ：获取所有的门票站点
	 * @return List 门票站点列表
	 */
	public List getTicketStation() {
		return ticketEquipDao.getTicketStation();
	}
	
	/**
	 * 功能 ：查询是否已存在此设备
	 */
	public List selectEquipByName(String ename) {
		return ticketEquipDao.selectEquipByName(ename);
	}
}
