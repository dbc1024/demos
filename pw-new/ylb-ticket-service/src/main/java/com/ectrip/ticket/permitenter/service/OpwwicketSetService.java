package com.ectrip.ticket.permitenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Opwwicketsettab;
import com.ectrip.ticket.permitenter.dao.IOpwwicketSetDAO;
import com.ectrip.ticket.permitenter.service.iservice.IOpwwicketSetService;

@Service
public class OpwwicketSetService implements IOpwwicketSetService{
	
	@Autowired
	IOpwwicketSetDAO wicketDao;
	
	
	
	/**
	 * *
	 * Describe:显示所有的检票设置信息
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getlistwicketset(int, int, java.lang.String)
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	public PaginationSupport getlistwicketset(String scenics,int pageSize, int startIndex,
			String url) {
		return wicketDao.getlistwicketset(scenics,pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:添加检票设置信息
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#insertwicketset(com.ectrip.model.permitenter.Opwwicketsettab, com.ectrip.model.syspar.Syslog)
	 * @param wicketset
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void insertwicketset(Opwwicketsettab wicketset, Syslog syslog) {
		wicketDao.insertwicketset(wicketset, syslog);
	}
	
	/**
	 * *
	 * Describe:修改检票设置信息
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#updatewicketset(com.ectrip.model.permitenter.Opwwicketsettab, com.ectrip.model.syspar.Syslog)
	 * @param wicketset
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void updatewicketset(Opwwicketsettab wicketset, Syslog syslog) {
		wicketDao.updatewicketset(wicketset, syslog);
	}
	
	/**
	 * *
	 * Describe:删除检票设置信息
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#deletewicketset(com.ectrip.model.permitenter.Opwwicketsettab, com.ectrip.model.syspar.Syslog)
	 * @param wicketset
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void deletewicketset(Opwwicketsettab wicketset, Syslog syslog) {
		wicketDao.deletewicketset(wicketset, syslog);
	}
	
	/**
	 * *
	 * Describe:根据ID查看检票设置
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getviewOpwicketID(java.lang.Long)
	 * @param iwicketsetid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	public Opwwicketsettab getviewOpwicketID(Long iwicketsetid) throws Exception {
		return wicketDao.getviewOpwicketID(iwicketsetid);
	}
	
	/**
	 * *
	 * Describe:显示圆门信息
	 * @see com.ectrip.system.permitenter.service.iservice.IOpwwicketSetService#getgardengatelist()
	 * @return
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	public List getgardengatelist(String scenics) {
		return wicketDao.getgardengatelist(scenics);
	}
	
	
	/***
	 * Describe: 登录人所属的服务商显示所属的票类型
	 * @see com.ectrip.system.permitenter.service.iservice.IOpwwicketSetService#getedmtickettypelist(java.lang.String)
	 * @param scenic 登录人所属的服务商
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-23
	 */
	public String getedmtickettypelist(String scenic) {
		return wicketDao.getedmtickettypelist(scenic);
	}
	
	/***
	 * Describe: 根据票类型ID获得所有对应的服务商的园门
	 * @see com.ectrip.system.permitenter.service.iservice.IOpwwicketSetService#getGardengate(java.lang.Long)
	 * @param itickettypeid 票类型ID
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-23
	 */
	public List getGardengate(Long itickettypeid){
		return wicketDao.getGardengate(itickettypeid);
	}
	
	/***
	 * Describe: 初始化票类型服务商的所有园门
	 * @see com.ectrip.system.permitenter.service.iservice.IOpwwicketSetService#getInitGardengate(java.lang.String)
	 * @param scenic 登录人所属的服务商
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-23
	 */
	public List getInitGardengate(String scenic){
		return wicketDao.getInitGardengate(scenic);
	}
	
	/**
	 * *
	 * Describe:显示套票的子产品
	 * @see com.ectrip.system.permitenter.service.iservice.IOpwwicketSetService#getviewedmticket(java.lang.Long)
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-22
	 */
	public String getviewedmticket(Long itickettypeid){
		return wicketDao.getviewedmticket(itickettypeid);
	}

	/**
	 * *
	 * Describe:根据园门id、产品id、子产品id判断存在 （添加时候）
	 * @see com.ectrip.system.permitenter.service.iservice.IOpwwicketSetService#showgradeTicket(java.lang.Long, java.lang.Long, java.lang.Long)
	 * @param igardengateid
	 * @param itickettypeid
	 * @param izticktypeid
	 * @return
	 * @author lijingrui
	 * Date:Nov 11, 2011
	 */
	public boolean showgradeTicket(Long igardengateid, Long itickettypeid,
			Long izticktypeid) {
		return wicketDao.showgradeTicket(igardengateid, itickettypeid, izticktypeid);
	}
	
	public IOpwwicketSetDAO getWicketDao() {
		return wicketDao;
	}

	public void setWicketDao(IOpwwicketSetDAO wicketDao) {
		this.wicketDao = wicketDao;
	}

	public List showgradeTicket(Long igardengateid,String bywicketconsumetype){
		return this.wicketDao.showgradeTicket(igardengateid, bywicketconsumetype);
	}
}

