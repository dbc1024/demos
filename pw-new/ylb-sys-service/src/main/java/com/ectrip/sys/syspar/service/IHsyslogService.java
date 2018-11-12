package com.ectrip.sys.syspar.service;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Hcustomlog;
import com.ectrip.sys.model.syspar.Horderlog;
import com.ectrip.sys.model.syspar.Hsyslog;

public interface IHsyslogService extends IGenericService {
	/**
	 * 
	 * Describe:显示后台操作日志
	 * @author:chenxinhao
	 * @param iemployeeid
	 * @param stlg
	 * @param rzti
	 * @param ldti
	 * @param flag
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-8-8
	 */
	public PaginationSupport showlisthsyslog(Long iemployeeid,String stlg,String rzti,String ldti,int flag,int pageSize, int startIndex,String url);
	/**
	 * 
	 * Describe:查看后台操作日志
	 * @author:chenxinhao
	 * @param logid
	 * @return
	 * @throws Exception
	 * return:Hsyslog
	 * Date:2012-8-8
	 */
	public Hsyslog getlogids(Long logid) throws Exception;
	/**
	 * 
	 * Describe:显示前台操作日志
	 * @author:chenxinhao
	 * @param usid
	 * @param rzti
	 * @param ldti
	 * @param flag
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-8-8
	 */
	public PaginationSupport showlisthcustomlog(String usid,String rzti,String ldti,int flag,int pageSize, int startIndex,String url);
	/**
	 * 
	 * Describe:查看前台操作日志
	 * @author:chenxinhao
	 * @param sysid
	 * @return
	 * @throws Exception
	 * return:Hcustomlog
	 * Date:2012-8-8
	 */
	public Hcustomlog getsysidview(Long sysid) throws Exception;
	/**
	 * 
	 * Describe:显示订单操作日志
	 * @author:chenxinhao
	 * @param usid
	 * @param rzti
	 * @param ldti
	 * @param flag
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-8-8
	 */
	public PaginationSupport showlisthorderlog(String usid,String rzti,String ldti,int flag,int pageSize, int startIndex,String url);
	/**
	 * 
	 * Describe:查看订单操作日志
	 * @author:chenxinhao
	 * @param logid
	 * @return
	 * @throws Exception
	 * return:Horderlog
	 * Date:2012-8-8
	 */
	public Horderlog getlogidhorderview(Long logid) throws Exception;
}

