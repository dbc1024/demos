package com.ectrip.ticket.salesmanager.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospsaleslimitstab;
import com.ectrip.ticket.salesmanager.dao.ISaleLimitsDAO;
import com.ectrip.ticket.salesmanager.service.ISaleLimitsService;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
public class SaleLimitsService implements ISaleLimitsService {
	@Autowired
	private ISaleLimitsDAO salelimitsdao ;
	/**
	 * 查看销售权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-5
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public PaginationSupport getSaleLimitsList(Long icompanyinfoid,Long iemployeeid,int page,int pageSize,String url) throws IllegalAccessException, InvocationTargetException{
		return salelimitsdao.getSaleLimitsList(icompanyinfoid,iemployeeid,page, pageSize, url);
	}


	/**
	 * 根据销售权限编号得到销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimitsId销售权限编号
	 * @return
	 * return:Ospsaleslimitstab
	 * Date:2011-10-5
	 */
	public List querySaleLimitsList(Long salelimitsId){
		return salelimitsdao.querySaleLimitsList(salelimitsId);
	}

	/**
	 * 新增销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void insertSalelimit(Long iemployeeId,String icrowdkindpriceids,Syslog syslog){
		salelimitsdao.insertSalelimit(iemployeeId,icrowdkindpriceids,syslog);

	}
	/**
	 * 修改销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateSalelimit(Long iemployeeId,String icrowdkindpriceids,Syslog syslog){

		salelimitsdao.updateSalelimit(iemployeeId,icrowdkindpriceids, syslog);
	}
	/**
	 * 删除销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimitsId销售权限id
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void deleteSalelimit(Long iemployeeId,Long icrowdkindpriceid,Syslog syslog){
		salelimitsdao.deleteSalelimit(iemployeeId,icrowdkindpriceid, syslog);

	}

}

