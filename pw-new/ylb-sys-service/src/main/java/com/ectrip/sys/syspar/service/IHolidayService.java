package com.ectrip.sys.syspar.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Holiday;
import com.ectrip.sys.model.syspar.Holidaydetail;
import com.ectrip.sys.model.syspar.Syslog;

public interface IHolidayService {
	/**
	 * *
	 * Describe:显示所有的假期
	 * @see com.ectrip.base.dao.idao.IGenericDao#findPage(java.lang.String, int, int, java.lang.String)
	 * @param holidayname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author yangguang
	 * Date:2011-9-27
	 */
	public PaginationSupport getlistholiday(String holidayname,int pageSize,int startIndex, String url); 
	
	/**
	 * 
	 * Describe:添加假期 假期明细表
	 * @auth:lijingrui
	 * @param hoday
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void insertHoliday(Holiday hoday,Syslog syslog);
	
	/**
	 * 
	 * Describe:修改假期 假期明细表
	 * @auth:lijingrui
	 * @param hoday
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void updateHoliday(Holiday hoday,Syslog syslog);
	
	/**
	 * 
	 * Describe:删除假期 假期明细表
	 * @auth:lijingrui
	 * @param hoday
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void deleteHoliday(Holiday hoday,Syslog syslog);
	
	/**
	 * 
	 * Describe:判断节假日名称是否相同
	 * @auth:lijingrui
	 * @param holidayname
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean genHolidayname(String holidayname);
	
	
	/**
	 * 
	 * Describe:显示某个节假日的明细信息
	 * @auth:lijingrui
	 * @return
	 * return:List<Holidaydetail>
	 * Date:2011-9-29
	 */
	public List<Holidaydetail> getHolidaydetaliview(Long holidayid);
}

