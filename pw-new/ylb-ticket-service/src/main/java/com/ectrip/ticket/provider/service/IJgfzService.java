package com.ectrip.ticket.provider.service;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Jgfz;

public interface IJgfzService extends IGenericService{
	
	/**
	 * 
	 * Describe:显示出服务商价格分组
	 * @author:lijingrui
	 * @param iscenicid
	 * @param flag
	 * @param query
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2014-4-14
	 */
	public PaginationSupport checkUpJgfz(Long iscenicid,String flag,String query,int pageSize,int startIndex,String url);

	/**
	 * 
	 * Describe:新增价格分组
	 * @author:lijingrui
	 * @param jgfz
	 * @param syslog
	 * return:void
	 * Date:2014-4-15
	 */
	public void addJgfz(Jgfz jgfz,Syslog syslog);
	
	/**
	 * 
	 * Describe:模糊查询用户
	 * @author:lijingrui
	 * @param query
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2014-4-16
	 */
	public PaginationSupport checkCustominfo(String query,int pageSize,int startIndex,String url);

	/**
	 * 
	 * Describe:修改价格分组
	 * @author:lijingrui
	 * return:void
	 * Date:2014-4-15
	 */
	public void editJgfz(Jgfz jgfz,Syslog syslog);
	
	/**
	 * 
	 * Describe:删除价格分组
	 * @author:lijingrui
	 * @param seq
	 * return:void
	 * Date:2014-4-15
	 */
	public void delJgfz(Long seq,Syslog syslog);
	
	/**
	 * 
	 * Describe:查看价格分组
	 * @author:lijingrui
	 * @param seq
	 * @return
	 * return:Jgfz
	 * Date:2014-4-15
	 */
	public Jgfz viewJgfz(Long seq) throws Exception ;
	
}

