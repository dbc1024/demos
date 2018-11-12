package com.ectrip.sys.employee.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.employee.dao.IESPDutyTabDao;
import com.ectrip.sys.employee.service.IESPDutyTabService;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.sys.model.syspar.Syslog;

@Service
public class ESPDutyTabService extends GenericService implements IESPDutyTabService {

	IESPDutyTabDao dutyDao;

	@Autowired
	public void setdutyDao(IESPDutyTabDao dutyDao) {
		super.setGenericDao(dutyDao);
		this.dutyDao = dutyDao;
	}
	/**
	 * lijingrui 删除职责
	 */
	public void deleteduty(Espdutytab espduty,Syslog syslog) {
		dutyDao.deleteduty(espduty,syslog);
	}

	/**
	 * *分页 根据父ID获取子列表 Describe:
	 * 
	 * @see com.ectrip.system.employee.service.iservice.IESPDutyTabService#findPage(java.lang.Long, int, int,
	 *      java.lang.String)
	 * @param idutyid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author yangguang Date:2011-9-17
	 */
	public PaginationSupport findPage(Long idutyid, String szdutyname,String dutype, int pageSize, int startIndex, String url) {
		return this.dutyDao.findPage(idutyid, szdutyname,dutype, pageSize, startIndex, url);
	}

	/**
	 * * Describe:判断是否有子节点
	 * 
	 * @see com.ectrip.system.employee.service.iservice.IESPDutyTabService#ishaveson(java.lang.Long)
	 * @param idutyid
	 * @return true 表示有子集 false表示无
	 * @author yangguang Date:2011-9-17
	 */
	public boolean ishaveson(Long idutyid) {

		return dutyDao.ishaveson(idutyid);
	}

	/**
	 ** 
	 * Describe:增加一个职责对象
	 * 
	 * @see com.ectrip.system.employee.service.iservice.IESPDutyTabService#insertduty(com.ectrip.model.employee.Espdutytab)
	 * @param espduty
	 * @author yangguang Date:2011-9-18
	 */
	public void insertduty(Espdutytab espduty, Espdutytab parentduty,String [] dutypes,Syslog syslog) {
		
		dutyDao.insertduty(espduty,parentduty,dutypes,syslog);
	}

	public Espdutytab retrieve(Long idutyid) throws IllegalAccessException, InvocationTargetException {
		return dutyDao.retrieve(idutyid);
	}

	public void updateduty(Espdutytab espduty,Syslog syslog) {
		dutyDao.updateduty(espduty,syslog);
	}
	/**
	 * 读取下经职责LIST
	 */

	public List retrivesonduty(Long idutyid) {
		return dutyDao.retrivesonduty(idutyid);
	}
	
	/**
	 * *
	 * Describe:查询出1-2层中的职责信息
	 * @see com.ectrip.system.employee.service.iservice.IESPDutyTabService#queryListduty()
	 * @return
	 * @author lijingrui
	 * Date:2013-1-11
	 */
	public List queryListduty(){
		return dutyDao.queryListduty();
	}
	
	/**
	 * *
	 * Describe:解析职责XML信息
	 * @see com.ectrip.system.employee.service.iservice.IESPDutyTabService#showelementList()
	 * @return
	 * @author lijingrui
	 * Date:2013-1-15
	 */
	public Map showelementList(){
		return dutyDao.showelementList();
	}
	
}
