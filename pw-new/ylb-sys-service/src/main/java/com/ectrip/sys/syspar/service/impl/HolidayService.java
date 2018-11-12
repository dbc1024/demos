package com.ectrip.sys.syspar.service.impl;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Holiday;
import com.ectrip.sys.model.syspar.Holidaydetail;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.IHolidayDao;
import com.ectrip.sys.syspar.service.IHolidayService;

public class HolidayService implements IHolidayService{
	IHolidayDao holidayDao;
	
	/**
	 * *
	 * Describe:删除假期 假期明细表
	 * @see com.ectrip.system.syspar.service.iservice.IHolidayService#deleteHoliday(com.ectrip.model.syspar.Holiday, com.ectrip.model.syspar.Holidaydetail, com.ectrip.model.syspar.Syslog)
	 * @param hoday
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-27
	 */
	public void deleteHoliday(Holiday hoday,Syslog syslog) {
		holidayDao.deleteHoliday(hoday, syslog);
	}
	
	/**
	 * *
	 * Describe:显示所有的假期
	 * @see com.ectrip.system.syspar.service.iservice.IHolidayService#findPage(java.lang.String, int, int, java.lang.String)
	 * @param holidayname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-9-27
	 */
	
	public PaginationSupport getlistholiday(String holidayname, int pageSize,
			int startIndex, String url) {
		return holidayDao.getlistholiday(holidayname, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:添加假期 假期明细表
	 * @see com.ectrip.system.syspar.service.iservice.IHolidayService#insertHoliday(com.ectrip.model.syspar.Holiday, com.ectrip.model.syspar.Holidaydetail, com.ectrip.model.syspar.Syslog)
	 * @param hoday
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-27
	 */
	public void insertHoliday(Holiday hoday,Syslog syslog) {
		holidayDao.insertHoliday(hoday, syslog);
	}
	
	/**
	 * *
	 * Describe:修改假期 假期明细表
	 * @see com.ectrip.system.syspar.service.iservice.IHolidayService#updateHoliday(com.ectrip.model.syspar.Holiday, com.ectrip.model.syspar.Holidaydetail, com.ectrip.model.syspar.Syslog)
	 * @param hoday
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-27
	 */
	public void updateHoliday(Holiday hoday,Syslog syslog) {
		holidayDao.updateHoliday(hoday, syslog);
	}
	
	/**
	 * 
	 * Describe:判断节假日名称是否相同
	 * @auth:lijingrui
	 * @param holidayname
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean genHolidayname(String holidayname) {
		return holidayDao.genHolidayname(holidayname);
	}
	/**
	 * *
	 * Describe:显示某个节假日的明细信息
	 * @see com.ectrip.system.syspar.dao.idao.IHolidayDao#getHolidaydetaliview(java.lang.Long)
	 * @param holidayid
	 * @return
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public List<Holidaydetail> getHolidaydetaliview(Long holidayid) {
		return holidayDao.getHolidaydetaliview(holidayid);
	}

	public IHolidayDao getHolidayDao() {
		return holidayDao;
	}

	public void setHolidayDao(IHolidayDao holidayDao) {
		this.holidayDao = holidayDao;
	}

}

