package com.ectrip.ec.user.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.user.dao.idao.ICustomViewDAO;
import com.ectrip.ec.user.service.iservice.ICustomViewService;
import com.ectrip.sys.model.syspar.Syslog;

public class CustomViewService implements ICustomViewService{
	private ICustomViewDAO customviewDao;

	public ICustomViewDAO getCustomviewDao() {
		return customviewDao;
	}

	public void setCustomviewDao(ICustomViewDAO customviewDao) {
		this.customviewDao = customviewDao;
	}
	
	/**
	 * *
	 * Describe:��ʾ�����еĽӴ��û�
	 * @see com.ectrip.system.user.service.iservice.ICustomViewService#AllListcustom(int, int, java.lang.String)
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public PaginationSupport AllListcustom(String lname,int pageSize, int startIndex,
			String url) {
		return customviewDao.AllListcustom(lname,pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:�鿴�Ӵ��û�
	 * @see com.ectrip.system.user.service.iservice.ICustomViewService#Lookcustomview(java.lang.String)
	 * @param usid
	 * @throws Exception
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public Custom Lookcustomview(String usid) throws Exception {
		return customviewDao.Lookcustomview(usid);
	}

	/**
	 * *
	 * Describe:ɾ���Ӵ��û�
	 * @see com.ectrip.system.user.service.iservice.ICustomViewService#delcustomview(com.ectrip.model.user.Custom, com.ectrip.model.syspar.Syslog)
	 * @param custom
	 * @param syslog
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public void delcustomview(Custom custom, Syslog syslog) {
		customviewDao.delcustomview(custom, syslog);
	}

	/**
	 * *
	 * Describe:�޸ĽӴ��û�
	 * @see com.ectrip.system.user.service.iservice.ICustomViewService#editcustomview(com.ectrip.model.user.Custom, com.ectrip.model.syspar.Syslog)
	 * @param custom
	 * @param syslog
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public void editcustomview(Custom custom, Syslog syslog) {
		customviewDao.editcustomview(custom, syslog);
	}

	/**
	 * *
	 * Describe:��Ӳ鿴�Ӵ��û�
	 * @see com.ectrip.system.user.service.iservice.ICustomViewService#insertcustomview(com.ectrip.model.user.Custom, com.ectrip.model.syspar.Syslog)
	 * @param custom
	 * @param syslog
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public void insertcustomview(Custom custom, Syslog syslog) {
		customviewDao.insertcustomview(custom, syslog);
	}
	
	/**
	 * *
	 * Describe:��ѯ��δ��˵��������û�
	 * @see com.ectrip.system.user.dao.idao.ICustomViewDAO#showChecktomustp(com.ectrip.model.user.Custom, int, int, int, java.lang.String)
	 * @param custom
	 * @param radiobutton
	 * @param pageSize
	 * @param startIndex
	 * @param path
	 * @return
	 * @author lijingrui
	 * Date:2012-10-30
	 */
	public PaginationSupport showChecktomustp(Custom custom,int radiobutton,int pageSize, int startIndex,String path){
		return customviewDao.showChecktomustp(custom, radiobutton, pageSize, startIndex, path);
	}
	
	/**
	 * *
	 * Describe:��ѯ��ͣ���û�
	 * @see com.ectrip.system.user.dao.idao.ICustomViewDAO#showTycustomustp(com.ectrip.model.user.Custom, int, int, int, java.lang.String)
	 * @param custom
	 * @param radiobutton
	 * @param pageSize
	 * @param startIndex
	 * @param path
	 * @return
	 * @author lijingrui
	 * Date:2012-10-30
	 */
	public PaginationSupport showTycustomustp(Custom custom,int radiobutton,int pageSize, int startIndex,String path){
		return customviewDao.showTycustomustp(custom, radiobutton, pageSize, startIndex, path);
	}
	
}

