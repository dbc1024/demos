package com.ectrip.sys.syspar.service.impl;

import java.util.List;

import com.ectrip.sys.syspar.dao.IQueryJsonDAO;
import com.ectrip.sys.syspar.service.IQueryJsonService;

public class QueryJsonService implements IQueryJsonService {

	private IQueryJsonDAO queryjsonDao;
	
	public void setQueryjsonDao(IQueryJsonDAO queryjsonDao) {
		this.queryjsonDao = queryjsonDao;
	}

	/**
	 *  查询服务商json数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param proname服务商名称 
	 * @param prtp服务商类型
	 * @param isjd是否是景点
	 * @return
	 * return:List
	 * Date:2011-11-3
	 */
	public List getProviderJsonList(String proname,String prtp,String isjd){
		return queryjsonDao.getProviderJsonList( proname, prtp, isjd);
	}

	/**
	 * 查询用户json数据库
	 * Describe:
	 * @auth:huangyuqi
	 * @param username用户名称
	 * @param lgtp用户类别
	 * @param ttlb团体类别（当lgtp为散客时，ttlb可以为空）
	 * @return
	 * return:List
	 * Date:2011-11-3
	 */
	public List getCustomJsionList(String username,String lgtp,String ttlb){
		return queryjsonDao.getCustomJsionList(username, lgtp, ttlb);
	}
	

}

