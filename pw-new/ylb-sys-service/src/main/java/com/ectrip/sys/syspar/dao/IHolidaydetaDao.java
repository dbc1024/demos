package com.ectrip.sys.syspar.dao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Holidaydetail;
import com.ectrip.sys.model.syspar.Syslog;

public interface IHolidaydetaDao extends IGenericDao{

	/**
	 * 
	 * Describe:显示节假日明细信息
	 * @auth:lijingrui
	 * @param holidayid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-29
	 */
	public PaginationSupport listviewHolidaydeta(Long holidayid,int pageSize,int startIndex, String url);
	/**
	 * 
	 * Describe:
	 * @auth:lijingrui
	 * @param hodaydeta
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	public void insertHolidaydeta(Holidaydetail hodaydeta,Syslog syslog);
	/**
	 * 
	 * Describe:修改节假日明细 信息
	 * @auth:lijingrui
	 * @param hodaydeta
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	public void updateHolidaydeta(Holidaydetail hodaydeta,Syslog syslog);
	/**
	 * 
	 * Describe:删除节假日明细 信息
	 * @auth:lijingrui
	 * @param hodaydeta
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	public void deleteHolidaydeta(Holidaydetail hodaydeta,Syslog syslog);
	
	/**
	 * 
	 * Describe:判断节假日明细是否相同
	 * @auth:lijingrui
	 * @param holidayid
	 * @param hodaydeta
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean getHolidaydetaless(Long holidayid,Holidaydetail hodaydeta);
}

