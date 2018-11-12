package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Ticketxgz;

public interface ITicketXgzService extends IGenericService{
	
	/**
	 * 
	 * Describe:显示所属服务商的所有退票诚信规则信息
	 * @auth:lijingrui
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:Nov 18, 2011
	 */
	public PaginationSupport showAllticketxgz(String scenics,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:添加退票诚信规则信息
	 * @auth:lijingrui
	 * @param tickxgz
	 * @param syslog
	 * return:void
	 * Date:Nov 18, 2011
	 */
	public void insertticketxgz(Ticketxgz tickxgz,Syslog syslog);
	
	/**
	 * 
	 * Describe:修改退票诚信规则信息
	 * @auth:lijingrui
	 * @param tickxgz
	 * @param syslog
	 * return:void
	 * Date:Nov 18, 2011
	 */
	public void updateticketxgz(Ticketxgz tickxgz,List lst,Syslog syslog);
	
	/**
	 * 
	 * Describe:删除退票诚信规则信息
	 * @auth:lijingrui
	 * @param tickxgz
	 * @param syslog
	 * return:void
	 * Date:Nov 18, 2011
	 */
	public void deleteticketxgz(Ticketxgz tickxgz,Syslog syslog);
	
	/**
	 * 
	 * Describe：查看退票诚信规则信息
	 * @auth:lijingrui
	 * @param gzid
	 * @return
	 * return:Ticketxgz
	 * Date:Nov 18, 2011
	 */
	public Ticketxgz getviewticketxgz(Long gzid) throws Exception;
	
	/**
	 * 
	 * Describe:显示出退票信息和退票费率表信息
	 * @auth:lijingrui
	 * @param gzid
	 * @return
	 * return:Ticketxgz
	 * Date:Nov 23, 2011
	 */
	public Ticketxgz gettickxgzchangebackview(Long gzid);
	
	/**
	 * 
	 * Describe:显示服务商信息
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:Nov 18, 2011
	 */
	public List findListesbticket(String scenics);
	
	/**
	 * 
	 * Describe:显示所属的票类型
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:Nov 18, 2011
	 */
	public String getedmtickettypelist(Long iscenicid);
	/**
	 * 
	 * Describe:显示所属的票类型(包含套票)
	 * @author:chenxinhao
	 * @param iscenicid
	 * @return
	 * return:String
	 * Date:2013-1-23
	 */
	public String getedmtickettypelist2(Long iscenicid);
}

