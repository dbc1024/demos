package com.ectrip.ticket.provider.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketequiptab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.provider.dao.IESBTicketEquipTabDAO;
/**
 * @author  yangyang
 * @version 窗口设备管理操作类
 */
@Repository
public class ESBTicketEquipTabDAO extends GenericDao implements IESBTicketEquipTabDAO {

	/**
	 * 功能 ：根据窗口ID显示所有的窗口设备
	 * @param winid   窗口ID
	 */
	public PaginationSupport findPage(Long winid, int pageSize, int startIndex,String url) {
		String sql="select new map(etq.iticketequipid as iticketequipid,etq.szticketequipname as szticketequipname,etq.szfactorycode as szfactorycode,etq.szticketequipcode as szticketequipcode,etq.byisuse as byisuse,etq.szmemo as szmemo," +
				"sc.szscenicname as szscenicname,wd.szticketwinname as szticketwinname,et.szequiptypename as szequiptypename,sta.szstationname as szstationname) " +
				"from Esbticketequiptab etq,Esbticketwintab wd,Esbscenicareatab sc,Esbequiptypetab et,Esbticketstationtab sta where etq.iticketwinid="+winid+
				" and etq.iticketwinid=wd.iticketwinid and etq.iequiptypeid=et.iequiptypeid and etq.iticketstationid=sta.iticketstationid and etq.iscenicid=sc.iscenicid";
		return this.findPage(sql, pageSize, startIndex, url);
	}
	
	/**
	 * 功能 ：根据条件模糊查询
	 * @param queryId  查询编号
	 * @param queryMess  查询的名称中所包含的字符
	 * @param winid    窗口ID
	 * @param equip    窗口设备
	 */
	public PaginationSupport findPage2(String queryId, String queryMess,Long winid,Esbticketequiptab equip,
			int pageSize, int startIndex, String url) {
		String sql="select new map(etq.iticketequipid as iticketequipid,etq.szticketequipname as szticketequipname,etq.szfactorycode as szfactorycode,etq.szticketequipcode as szticketequipcode,etq.byisuse as byisuse,etq.szmemo as szmemo," +
		"sc.szscenicname as szscenicname,wd.szticketwinname as szticketwinname,et.szequiptypename as szequiptypename,sta.szstationname as szstationname) " +
		"from Esbticketequiptab etq,Esbticketwintab wd,Esbscenicareatab sc,Esbequiptypetab et,Esbticketstationtab sta where etq.iticketwinid="+winid+
		" and etq.iticketwinid=wd.iticketwinid and etq.iequiptypeid=et.iequiptypeid and etq.iticketstationid=sta.iticketstationid and etq.iscenicid=sc.iscenicid";
        
		StringBuffer sbf=new StringBuffer(sql);
		if(queryId!=null&&!queryId.equals("")){
			sbf.append(" and iticketequipid='"+queryId+"'");
		}
		if(queryMess!=null&&!queryMess.equals("")){
			sbf.append(" and szticketequipname like '%"+queryMess+"%'");
		}
		return this.findPage(sbf.toString(), pageSize, startIndex, url);
	}
	
	/**
	 * 功能 ：添加新的窗口设备
	 */
	public void addNewEquip(Esbticketequiptab equip,Syslog syslog) {
		equip.setIticketequipid(this.getMaxPk("iticketequipid", "Esbticketequiptab")+1);
		Esbticketwintab win=(Esbticketwintab)this.get(Esbticketwintab.class, equip.getIticketwinid());
		equip.setIscenicid(win.getIscenicid());
		equip.setIticketstationid(win.getIticketstationid());
		equip.setByisuse(new Long(1));
		this.save(equip);
		
		syslog.setStlg("0173");
		syslog.setBrief("窗口设备信息：" +equip.getIticketequipid()+" 名称"+equip.getSzticketequipname());
		syslog.setNote("添加窗口设备信息：" +  equip.getSzticketequipname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * 功能 ：修改设备信息
	 * @param  equip  窗口设备
	 */
	public void updateEquip(Esbticketequiptab equip,Syslog syslog) {
		if(equip!=null){
			Esbticketequiptab qb=(Esbticketequiptab)this.get(Esbticketequiptab.class, equip.getIticketequipid());
			qb.setByisuse(equip.getByisuse());
			qb.setIequiptypeid(equip.getIequiptypeid());
			qb.setSzfactorycode(equip.getSzfactorycode());
			qb.setSzticketequipcode(equip.getSzticketequipcode());
			qb.setSzticketequipname(equip.getSzticketequipname());
			qb.setSzmemo(equip.getSzmemo());
			
			this.update(qb);
		}
		
		syslog.setStlg("0174");
		syslog.setBrief("窗口设备信息：" +equip.getIticketequipid()+" 名称"+ equip.getSzticketequipname());
		syslog.setNote("修改窗口设备信息：" +  equip.getSzticketequipname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
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
		Esbticketequiptab esb=(Esbticketequiptab)this.get(Esbticketequiptab.class, equip.getIticketequipid());
		
		syslog.setStlg("0175");
		syslog.setBrief("窗口设备信息：" + esb.getIticketequipid()+" 名称"+esb.getSzticketequipname());
		syslog.setNote("删除窗口设备信息：" +  esb.getSzticketequipname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
		this.delete(esb);
	}
	
	/**
	 * 功能 ：获取所有的设备类型
	 * @return  List 设备类型列表
	 */
	public List getEquipTypeList() {
		String sql=" from Esbequiptypetab";
		return this.find(sql);
	}
	
	/**
	 * 功能 ：获取所有的服务商
	 * @return List 服务商列表
	 */
	public List getScenics() {
		String sql=" from Esbscenicareatab";
		return this.find(sql);
	}
	
	/**
	 * 功能 ：获取所有的门票站点
	 * @return List 门票站点列表
	 */
	public List getTicketStation() {
		String sql=" from Esbticketstationtab";
		return this.find(sql);
	}
	
	/**
	 * 功能 ：查询是否已存在此设备
	 */
	public List selectEquipByName(String ename) {
		String sql=" from Esbticketequiptab where szticketequipname='"+ename+"'";
		return this.find(sql);
	}

} 
