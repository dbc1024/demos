package com.ectrip.sys.message.service.iservice;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Webinfotab;

public interface IWebinfotabService {
	
	/**
	 * 
	 * Describe:显示所有的站内短信公告
	 * @auth:lijingrui
	 * @param flag
	 * @param employeeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2013-1-19
	 */
	public PaginationSupport showListwebinfos(int flag,String employeeid,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:添加站内短信公告
	 * @auth:lijingrui
	 * @param webinfo
	 * @param syslog
	 * return:void
	 * Date:2013-1-19
	 */
	public void addWebinfotab(Webinfotab webinfo,Syslog syslog);
	
	/**
	 * 
	 * Describe:修改站内短信公告
	 * @auth:lijingrui
	 * @param webinfo
	 * @param syslog
	 * return:void
	 * Date:2013-1-19
	 */
	public void updateWebinfotab(Webinfotab webinfo,Syslog syslog);
	
	/**
	 * 
	 * Describe:删除站内短信公告
	 * @auth:lijingrui
	 * @param webinfo
	 * @param syslog
	 * return:void
	 * Date:2013-1-19
	 */
	public void delWebinfotab(Webinfotab webinfo,Syslog syslog);
	
	/**
	 * 
	 * Describe:查看站内短信公告
	 * @auth:lijingrui
	 * @param seq
	 * @return
	 * @throws Exception
	 * return:Webinfotab
	 * Date:2013-1-19
	 */
	public Webinfotab viewWebinfotab(Long seq) throws Exception;

}

