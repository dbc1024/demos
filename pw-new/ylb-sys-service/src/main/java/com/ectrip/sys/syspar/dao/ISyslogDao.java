package com.ectrip.sys.syspar.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Customlog;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;


public interface ISyslogDao extends IGenericDao{
	/**
	 * 
	 * Describe:显示所有的后台日志信息
	 * @auth:lijingrui
	 * @param syslog
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-6
	 */
	public PaginationSupport getlistsyslog(Long iemployeeid,String stlg,String date,int flag,int pageSize, int startIndex,String url);
	
	
	/**
	 * 
	 * Describe:显示前台操作日志
	 * @auth:lijingrui
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
	 * return:PaginationSupport
	 * Date:2011-10-7
	 */
	public PaginationSupport getlistcustomlog(String usid,String date,int flag,int pageSize, int startIndex,String url);
	//前台用户操作日志
	public PaginationSupport getListCustomLog(String usid,int pageSize, int startIndex,String url);
	
	/**
	 * 
	 * Describe:显示订单操作日志
	 * @auth:lijingrui
	 * @param stlg
	 * @param start
	 * @param end
	 * @param radio1
	 * @param radio2
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-7
	 */
	public PaginationSupport getlistorderlog(String usid,String rzti,String ldti,int flag,int pageSize, int startIndex,String url);

	/**
	 * 
	 * Describe:添加后台操作日志
	 * @auth:lijingrui
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void insertSyslog(Syslog syslog);
	
	/**
	 * 
	 * Describe:添加前台用户操作日志
	 * @auth:lijingrui
	 * @param customlog
	 * return:void
	 * Date:2011-10-6
	 */
	public void insertTomlog(Customlog customlog);
	
	/**
	 * 
	 * Describe:添加订单操作日志
	 * @auth:lijingrui
	 * @param orderlog
	 * return:void
	 * Date:2011-10-6
	 */
	public void insertOrderlog(Orderlog orderlog);
	
	/**
	 * 
	 * Describe:显示所有的用户
	 * @auth:lijingrui
	 * @return
	 * return:List<Esfemployeetab>
	 * Date:2011-10-6
	 */
	public List<Esfemployeetab> getAllshowemployee();
	
	/**
	 * 
	 * Describe:显示所有的操作功能
	 * @auth:lijingrui
	 * @return
	 * return:List<Sysparv5>
	 * Date:2011-10-6
	 */
	public List<Sysparv5> getAllshowsysparv5();
	
	/**
	 * 
	 * Describe:显示所有的前台用户
	 * @auth:lijingrui
	 * @return
	 * return:List<Custom>
	 * Date:2011-10-7
	 */
	public List<Custom> getAllshowscustom();
	
	
	/**
	 * 
	 * Describe:显示某个日志的信息
	 * @auth:lijingrui
	 * @param logid
	 * @return
	 * return:Syslog
	 * Date:2011-10-6
	 */
	public Syslog getlogids(Long logid)  throws Exception;
	
	/**
	 * 
	 * Describe:查看前台操作日志
	 * @auth:lijingrui
	 * @param sysid
	 * @return
	 * return:Customlog
	 * Date:2011-10-7
	 */
	public Customlog getsysidview(Long sysid)  throws Exception;
	
	/**
	 * 
	 * Describe:查看订单操作日志
	 * @auth:lijingrui
	 * @param logid
	 * @return
	 * return:Orderlog
	 * Date:2011-10-7
	 */
	public Orderlog getlogidorderview(Long logid)  throws Exception;
	
	public PaginationSupport getListOrderLogByType(String type, String usid,String orid,
			 int pageSize, int startIndex, String url,String groupId);
	/**
	 * 
	 * Describe:前台用户历史操作日志
	 * @author:chenxinhao
	 * @param usid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-9-11
	 */
	public PaginationSupport getListHcustomLog(String usid,int pageSize,int startIndex, String url);
	/**
	 * 
	 * Describe:前台查询订单历史操作日志 
	 * @author:chenxinhao
	 * @param usid
	 * @param orid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-9-11
	 */
	public PaginationSupport getListhOrderLogByType(String usid,String orid,int pageSize, int startIndex, String url,String groupId);
}

