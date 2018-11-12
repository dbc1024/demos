package com.ectrip.sys.syspar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Customlog;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.syspar.dao.ISyslogDao;
import com.ectrip.sys.syspar.service.ISyslogService;

@Service
public class SyslogService implements ISyslogService{
	
	@Autowired
	ISyslogDao syslogDao;
	
	/**
	 * *
	 * Describe:显示后台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getlistsyslog(com.ectrip.model.syspar.Syslog, int, int, java.lang.String)
	 * @param syslog
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	public PaginationSupport getlistsyslog(Long iemployeeid,String stlg,String date,int flag,int pageSize, int startIndex,String url){
		return syslogDao.getlistsyslog(iemployeeid, stlg, date, flag, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:显示前台操作日志
	 * @see com.ectrip.system.syspar.service.iservice.ISyslogService#getlistcustomlog(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int, int, int, java.lang.String)
	 * @param usid
	 * @param stlg
	 * @param start
	 * @param end
	 * @param radio1
	 * @param radio2
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 */
	public PaginationSupport getlistcustomlog(String usid, String date, int flag, int pageSize,
			int startIndex, String url) {
		return syslogDao.getlistcustomlog(usid, date, flag, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:显示订单操作日志
	 * @see com.ectrip.system.syspar.service.iservice.ISyslogService#getlistorderlog(java.lang.String, java.lang.String, java.lang.String, int, int, int, int, java.lang.String)
	 * @param stlg
	 * @param start
	 * @param end
	 * @param radio1
	 * @param radio2
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 */
	public PaginationSupport getlistorderlog(String usid, String rzti,
			String ldti, int flag, int pageSize, int startIndex,
			String url) {
		return syslogDao.getlistorderlog(usid, rzti, ldti, flag, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:添加后台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#insertSyslog(com.ectrip.model.syspar.Syslog)
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void insertSyslog(Syslog syslog) {
		syslogDao.insertSyslog(syslog);
	}
	
	/**
	 * *
	 * Describe:添加前台用户操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#insertTomlog(com.ectrip.model.syspar.Customlog)
	 * @param customlog
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void insertTomlog(Customlog customlog) {
		syslogDao.insertTomlog(customlog);
	}
	
	/**
	 * *
	 * Describe:添加订单操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#insertOrderlog(com.ectrip.sys.model.syspar.model.syspar.Orderlog)
	 * @param orderlog
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void insertOrderlog(Orderlog orderlog) {
		syslogDao.insertOrderlog(orderlog);
	}
	
	/**
	 * *
	 * Describe:显示所有的用户
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getAllshowemployee()
	 * @return
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	public List<Esfemployeetab> getAllshowemployee() {
		return syslogDao.getAllshowemployee();
	}
	
	/**
	 * *
	 * Describe:显示所有的操作功能
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getAllshowsysparv5()
	 * @return
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	public List<Sysparv5> getAllshowsysparv5() {
		return syslogDao.getAllshowsysparv5();
	}
	
	
	/**
	 * *
	 * Describe:显示所有的前台用户
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getAllshowscustom()
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 */
	public List<Custom> getAllshowscustom(){
		return syslogDao.getAllshowscustom();
	}
	/**
	 * *
	 * Describe:查看后台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getlogids(java.lang.Long)
	 * @param logid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	public Syslog getlogids(Long logid)  throws Exception{
		return syslogDao.getlogids(logid);
	}
	
	
	/**
	 * *
	 * Describe:查看前台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getsysidview(java.lang.Long)
	 * @param sysid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 */
	public Customlog getsysidview(Long sysid) throws Exception{
		return syslogDao.getsysidview(sysid);
	}
	
	/**
	 * *
	 * Describe:查看订单操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getlogidorderview(java.lang.Long)
	 * @param logid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 */
	public Orderlog getlogidorderview(Long logid) throws Exception{
		return syslogDao.getlogidorderview(logid);
	}
	
	
	public ISyslogDao getSyslogDao() {
		return syslogDao;
	}

	public void setSyslogDao(ISyslogDao syslogDao) {
		this.syslogDao = syslogDao;
	}
    /**
     * (非 Javadoc) 
    * <p>Title: getListOrderLogByType</p> 
    * <p>Description: 查询订单操作日志 type 0 前台  1 后台</p> 
    * @param type
    * @param usid
    * @param start
    * @param end
    * @param pageSize
    * @param startIndex
    * @param url
    * @return 
    * @see com.ectrip.system.syspar.service.iservice.ISyslogService#getListOrderLogByType(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
     */
	public PaginationSupport getListOrderLogByType(String type, String usid,String orid,
			 int pageSize, int startIndex, String url,String groupId) {
		 return syslogDao.getListOrderLogByType(type, usid,orid, pageSize, startIndex, url,groupId);
	}
/**
 * (非 Javadoc) 
* <p>Title: getListCustomLog</p> 
* <p>Description:前台用户操作日志-用户中心 </p> 
* @param usid
* @param pageSize
* @param startIndex
* @param url
* @return 
* @see com.ectrip.system.syspar.service.iservice.ISyslogService#getListCustomLog(java.lang.String, int, int, java.lang.String)
 */
	public PaginationSupport getListCustomLog(String usid, int pageSize,
			int startIndex, String url) {
		return syslogDao.getListCustomLog(usid, pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:前台用户历史操作日志-用户中心 
	 * @see com.ectrip.system.syspar.service.iservice.ISyslogService#getListHcustomLog(java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-9-11
	 */
	public PaginationSupport getListHcustomLog(String usid, int pageSize,
			int startIndex, String url) {
		return syslogDao.getListHcustomLog(usid, pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:前台查询订单历史操作日志 
	 * @see com.ectrip.system.syspar.service.iservice.ISyslogService#getListhOrderLogByType(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param orid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-9-11
	 */
	public PaginationSupport getListhOrderLogByType(String usid, String orid,
			int pageSize, int startIndex, String url,String groupId) {
		return syslogDao.getListhOrderLogByType(usid, orid, pageSize, startIndex, url,groupId);
	}
	
	
}

