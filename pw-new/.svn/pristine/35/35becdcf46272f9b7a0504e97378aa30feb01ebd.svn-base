package com.ectrip.ec.articlemanager.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.articlemanager.Columnmanagertab;
import com.ectrip.sys.model.syspar.Syslog;

public interface IColumnManagerService extends IGenericService {
	/**
	 * 
	 * Describe:显示所有栏目
	 * @author:chenxinhao
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-8-16
	 */
	public PaginationSupport showlistColumns(int pageSize, int startInt, String url);
	/**
	 * 
	 * Describe:添加栏目
	 * @author:chenxinhao
	 * @param column
	 * @param syslog
	 * return:void
	 * Date:2012-8-16
	 */
	public void insertColumn(Columnmanagertab column,Syslog syslog);
	/**
	 * 
	 * Describe:修改栏目
	 * @author:chenxinhao
	 * @param column
	 * @param syslog
	 * return:void
	 * Date:2012-8-16
	 */
	public void updateColumn(Columnmanagertab column,Syslog syslog);
	/**
	 * 
	 * Describe:删除栏目
	 * @author:chenxinhao
	 * @param column
	 * @param syslog
	 * return:void
	 * Date:2012-8-16
	 */
	public void deleteColumn(Columnmanagertab column,Syslog syslog);
	/**
	 * 
	 * Describe:查看栏目
	 * @author:chenxinhao
	 * @param cmid
	 * @return
	 * return:Columnmanagertab
	 * Date:2012-8-16
	 */
	public Columnmanagertab viewColumn(Long cmid);
	
	/**
	 * 获取栏目
	 * @return
	 */
	public Columnmanagertab getcolum(String topic);
	
}

