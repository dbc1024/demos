package com.ectrip.ticket.provider.dao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Esbprovicerq;

public interface IEsbprovicerqDAO extends IGenericDao {
	
	/**
	 * 显示所有设置信息
	 * @param iscenicid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 */
	public PaginationSupport checkListEsbprovicerq(Long iscenicid,String scenics,int pageSize,int startIndex,String url);
	
	/**
	 * Describe:判断该数据是否存在
	 * @author: huying
	 * @param scenicid
	 * @param businessId
	 * @return
	 * return:boolean
	 * Date:2015-3-27
	 */
	public boolean esbproviceIsuse(Long scenicid,Long businessId);
	
	/**
	 * 
	 * Describe:根据主键查询实体对象
	 * @author: huying
	 * @param seqLong
	 * @return
	 * return:Esbprovicerq
	 * Date:2015-3-28
	 * @throws Exception 
	 */
	public Esbprovicerq queryEsbproviceById(Long seqLong) throws Exception;
	
}
