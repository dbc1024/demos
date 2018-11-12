package com.ectrip.ec.user.service.iservice;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Syslog;

public interface ICustomViewService {
	/**
	 * 
	 * Describe: ��ʾ�����еĽӴ��û�
	 * @auth:lijingrui
	 * @param pageSize
	 * @param startIndex
	 * @param path
	 * @return
	 * return:PaginationSupport
	 * Date:Feb 6, 2012
	 */
	public PaginationSupport AllListcustom(String lname,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:��ӽӴ��û�
	 * @auth:lijingrui
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:Feb 6, 2012
	 */
	public void insertcustomview(Custom custom,Syslog syslog);
	
	/**
	 * 
	 * Describe:�޸ĽӴ��û�
	 * @auth:lijingrui
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:Feb 6, 2012
	 */
	public void editcustomview(Custom custom,Syslog syslog);
	
	/**
	 * 
	 * Describe:ɾ���Ӵ��û�
	 * @auth:lijingrui
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:Feb 6, 2012
	 */
	public void delcustomview(Custom custom,Syslog syslog);
	
	/**
	 * 
	 * Describe:�鿴�Ӵ��û�
	 * @auth:lijingrui
	 * @param usid
	 * @throws Exception
	 * return:void
	 * Date:Feb 6, 2012
	 */
	public Custom Lookcustomview(String usid) throws Exception;
	
	/**
	 * 
	 * Describe:��ѯ��δ��˵��������û�
	 * @auth:lijingrui
	 * @param custom
	 * @param radiobutton
	 * @param pageSize
	 * @param startIndex
	 * @param path
	 * @return
	 * return:PaginationSupport
	 * Date:2012-10-30
	 */
	public PaginationSupport showChecktomustp(Custom custom,int radiobutton,int pageSize, int startIndex,String path);
	
	/**
	 * 
	 * Describe:��ѯ��ͣ���û�
	 * @auth:lijingrui
	 * @param custom
	 * @param radiobutton
	 * @param pageSize
	 * @param startIndex
	 * @param path
	 * @return
	 * return:PaginationSupport
	 * Date:2012-10-30
	 */
	public PaginationSupport showTycustomustp(Custom custom,int radiobutton,int pageSize, int startIndex,String path);
	
}

