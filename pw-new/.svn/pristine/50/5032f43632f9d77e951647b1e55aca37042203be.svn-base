package com.ectrip.ticket.salesmanager.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospsaleslimitstab;
/**
 * 销售权限dao接口
 * @author huangyuqi
 */
public interface ISaleLimitsDAO extends IGenericDao {
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
	public PaginationSupport getSaleLimitsList(Long icompanyinfoid,Long iemployeeid,int page,int pageSize,String url) throws IllegalAccessException, InvocationTargetException;


	/**
	 * 根据销售权限编号得到销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimitsId销售权限编号
	 * @return
	 * return:Ospsaleslimitstab
	 * Date:2011-10-5
	 */
	public List querySaleLimitsList(Long salelimitsId);
	/**
	 * 新增销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void insertSalelimit(Long iemployeeId,String icrowdkindpriceids,Syslog syslog);
	/**
	 * 修改销售权限s
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void updateSalelimit(Long iemployeeId,String icrowdkindpriceids,Syslog syslog);
	/**
	 * 删除销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimitsId销售权限id
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void deleteSalelimit(Long iemployeeId,Long icrowdkindpriceid,Syslog syslog);
}

