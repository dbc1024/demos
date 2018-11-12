package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Edmbusinesstab;
public interface IBusinessDAO extends IGenericDao {
	/**
	 * 业务类型列表 
	 * Describe:
	 * @auth:huangyuqi
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url地址
	 * @param condition查询条件
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-29
	 */
	public PaginationSupport getBusinessAllList(int page,int pageSize,String url,String condition);
	/**
	 * 根据业务类型编号得到业务类型对象
	 * Describe:
	 * @auth:huangyuqi
	 * @param businessId
	 * @return
	 * return:Edmbusinesstab
	 * Date:2011-9-29
	 */
	public Edmbusinesstab queryBusiness(Long businessId);
	/**
	 *根据业务类型判断它是否被售价表用到
	 * Describe:
	 * @auth:huangyuqi
	 * @param businessId
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean businessIsuse(Long businessId);
	/**
	 * 查出所有业务类型
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-30
	 */
	public List businessList();

}

