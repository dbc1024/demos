package com.ectrip.sys.employee.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.sys.model.syspar.Syslog;

public interface IESPDutyTabService extends IGenericService{
	/**
	 * 
	 * Describe:添加职责
	 * 
	 * @auth:lijingrui
	 * @param espduty
	 *            return:void Date:2011-9-17
	 */
	public void insertduty(Espdutytab espduty, Espdutytab parentduty,String [] dutypes,Syslog syslog);

	/**
	 * 
	 * Describe:修改职责
	 * 
	 * @auth:yangguang
	 * @param espduty
	 *            return:void Date:2011-9-17
	 */
	public void updateduty(Espdutytab espduty,Syslog syslog);

	/**
	 * 
	 * Describe:删除职责
	 * 
	 * @auth:yangguang
	 * @param idutyid
	 *            return:void Date:2011-9-17
	 */
	public void deleteduty(Espdutytab espduty,Syslog syslog);

	/**
	 * 
	 * Describe:根据编号查看职责基本信息
	 * 
	 * @auth:yangguang
	 * @param idutyid
	 * @return return:Espdutytab Date:2011-9-17
	 */
	public Espdutytab retrieve(Long idutyid) throws IllegalAccessException, InvocationTargetException;

	/**
	 * 
	 * Describe:分页显示
	 * 
	 * @auth:yangguang
	 * @param idutyid
	 * @param szdutyname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return return:PaginationSupport Date:2011-9-17
	 */
	public PaginationSupport findPage(Long idutyid, String szdutyname,String dutype, int pageSize, int startIndex, String url);

	/**
	 * 
	 * Describe:判断是否有子节点
	 * 
	 * @auth:yangguang
	 * @param idutyid
	 * @return true 表示有子集 false表示无 return:boolean Date:2011-9-17
	 */
	public boolean ishaveson(Long idutyid);

	/**
	 * 读取下级职责
	 * 
	 * @auth:李进
	 * @param idutyid
	 *            职责编号
	 * @return
	 */
	public List retrivesonduty(Long idutyid); // 读取下集职责
	
	/**
	 * 
	 * Describe:查询出1-2层中的职责信息
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2013-1-11
	 */
	public List queryListduty();
	
	/**
	 * 
	 * Describe:解析职责XML信息
	 * @auth:lijingrui
	 * @return
	 * return:Map
	 * Date:2013-1-15
	 */
	public Map showelementList();
	
}
