package com.ectrip.sys.employee.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.sys.model.syspar.Syslog;

public interface IESPDutyTabDao extends IGenericDao {
	public void insertduty(Espdutytab espduty,Espdutytab parentduty,String [] dutypes,Syslog syslog); // 添加职责

	public void updateduty(Espdutytab espduty,Syslog syslog); // 修改职责

	public void deleteduty(Espdutytab espduty,Syslog syslog); // 删除职责

	public List retrivesonduty(Long idutyid); // 读取下级职责

	public Espdutytab retrieve(Long idutyid) throws IllegalAccessException, InvocationTargetException; // 根据编号查看职责基本信息

	public PaginationSupport findPage(Long idutyid, String szdutyname,String dutype, int pageSize, int startIndex, String url); // 分页

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
