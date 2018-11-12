package com.ectrip.ticket.salesmanager.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Osppostsaleslimitstab;

public interface IPostSalesLimitsDAO extends IGenericDao {
	/**
	 *  分页查询岗位权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pagesize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-5
	 */
	public PaginationSupport getPostSaleLimitsList(int page,int pagesize,String url);
	/**
	 * 根据岗销售编号得到岗位销售信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param postsaleId
	 * @return
	 * return:Osppostsaleslimitstab
	 * Date:2011-10-5
	 */
	public Osppostsaleslimitstab queryPostSaleLimits(Long postsaleId);

	/**
	 * 岗位销售权限增加
	 * Describe:
	 * @auth:huangyuqi
	 * @param postsalelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void insertPostSaleLimits(List postsalelimitslist,Syslog syslog);
	/**
	 * 岗位销售权限修改
	 * Describe:
	 * @auth:huangyuqi
	 * @param postsalelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void updatePostSaleLimits(List postsalelimitslist,Syslog syslog);
	/**
	 * 岗位销售权限删除
	 * Describe:
	 * @auth:huangyuqi
	 * @param iosppostsalelimitsid
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void deletePostSaleLimits(Long iosppostsalelimitsid,Syslog syslog);

	/**
	 * 根据岗销售编号得到岗位销售信息列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param postsaleId
	 * @return
	 * return:Osppostsaleslimitstab
	 * Date:2011-10-5
	 */
	public List queryPostSaleLimitsList(Long postsaleId);
}

