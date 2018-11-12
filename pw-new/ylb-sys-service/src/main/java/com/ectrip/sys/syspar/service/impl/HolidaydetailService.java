package com.ectrip.sys.syspar.service.impl;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Holidaydetail;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.IHolidaydetaDao;
import com.ectrip.sys.syspar.service.IHolidaydetaSerivice;

public class HolidaydetailService implements IHolidaydetaSerivice{
	
	IHolidaydetaDao holidaydetaDao;
	
	/**
	 * *
	 * Describe:显示节假日明细信息
	 * @see com.ectrip.system.syspar.service.iservice.IHolidaydetaSerivice#listviewHolidaydeta(java.lang.Long, int, int, java.lang.String)
	 * @param holidayid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public PaginationSupport listviewHolidaydeta(Long holidayid, int pageSize,
			int startIndex, String url) {
		return holidaydetaDao.listviewHolidaydeta(holidayid, pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:添加节假日明细 信息
	 * @see com.ectrip.system.syspar.service.iservice.IHolidaydetaSerivice#insertHolidaydeta(com.ectrip.model.syspar.Holidaydetail, com.ectrip.model.syspar.Syslog)
	 * @param hodaydeta
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public void insertHolidaydeta(Holidaydetail hodaydeta, Syslog syslog) {
		holidaydetaDao.insertHolidaydeta(hodaydeta, syslog);
	}
	/**
	 * *
	 * Describe:修改节假日明细 信息
	 * @see com.ectrip.system.syspar.service.iservice.IHolidaydetaSerivice#updateHolidaydeta(com.ectrip.model.syspar.Holidaydetail, com.ectrip.model.syspar.Syslog)
	 * @param hodaydeta
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public void updateHolidaydeta(Holidaydetail hodaydeta, Syslog syslog) {
		holidaydetaDao.updateHolidaydeta(hodaydeta, syslog);
	}
	/**
	 * *
	 * Describe:删除节假日明细 信息
	 * @see com.ectrip.system.syspar.service.iservice.IHolidaydetaSerivice#deleteHolidaydeta(com.ectrip.model.syspar.Holidaydetail, com.ectrip.model.syspar.Syslog)
	 * @param hodaydeta
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public void deleteHolidaydeta(Holidaydetail hodaydeta, Syslog syslog) {
		holidaydetaDao.deleteHolidaydeta(hodaydeta, syslog);
	}
	
	/**
	 * *
	 * Describe:判断节假日明细是否相同
	 * @see com.ectrip.system.syspar.service.iservice.IHolidaydetaSerivice#getHolidaydetaless(java.lang.Long, com.ectrip.model.syspar.Holidaydetail)
	 * @param holidayid
	 * @param hodaydeta
	 * @return
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public boolean getHolidaydetaless(Long holidayid, Holidaydetail hodaydeta) {
		return holidaydetaDao.getHolidaydetaless(holidayid, hodaydeta);
	}
	
	public IHolidaydetaDao getHolidaydetaDao() {
		return holidaydetaDao;
	}
	
	public void setHolidaydetaDao(IHolidaydetaDao holidaydetaDao) {
		this.holidaydetaDao = holidaydetaDao;
	}
	
}

