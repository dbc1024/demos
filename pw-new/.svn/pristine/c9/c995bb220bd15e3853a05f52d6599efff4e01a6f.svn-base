package com.ectrip.sys.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.message.dao.idao.IWebinfotabDAO;
import com.ectrip.sys.message.service.iservice.IWebinfotabService;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Webinfotab;

@Service
public class WebinfotabService implements IWebinfotabService{
	
	@Autowired
	private IWebinfotabDAO webinfoDao;


	/**
	 * *
	 * Describe:显示所有的站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#showListwebinfos(int, String, int, int, java.lang.String)
	 * @param flag
	 * @param employeeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	public PaginationSupport showListwebinfos(int flag,String employeeid,int pageSize,int startIndex, String url){
		return webinfoDao.showListwebinfos(flag, employeeid, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:添加站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#addWebinfotab(com.ectrip.sys.model.syspar.model.permitenter.Webinfotab, com.ectrip.model.syspar.Syslog)
	 * @param webinfo
	 * @param syslog
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void addWebinfotab(Webinfotab webinfo,Syslog syslog){
		webinfoDao.addWebinfotab(webinfo, syslog);
	}
	
	/**
	 * *
	 * Describe:修改站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#updateWebinfotab(com.ectrip.sys.model.syspar.model.permitenter.Webinfotab, com.ectrip.model.syspar.Syslog)
	 * @param webinfo
	 * @param syslog
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void updateWebinfotab(Webinfotab webinfo,Syslog syslog){
		webinfoDao.updateWebinfotab(webinfo, syslog);
	}
	
	/**
	 * *
	 * Describe:删除站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#delWebinfotab(com.ectrip.sys.model.syspar.model.permitenter.Webinfotab, com.ectrip.model.syspar.Syslog)
	 * @param webinfo
	 * @param syslog
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void delWebinfotab(Webinfotab webinfo,Syslog syslog){
		webinfoDao.delWebinfotab(webinfo, syslog);
	}
	
	/**
	 * *
	 * Describe:查看站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#viewWebinfotab(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	public Webinfotab viewWebinfotab(Long seq) throws Exception{
		return webinfoDao.viewWebinfotab(seq);
	}
	
}

