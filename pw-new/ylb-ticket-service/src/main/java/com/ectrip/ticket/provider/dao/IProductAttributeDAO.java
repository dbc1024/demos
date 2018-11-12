package com.ectrip.ticket.provider.dao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.model.provider.Hotelproduct;
import com.ectrip.ticket.model.provider.Netusermessage;

public interface IProductAttributeDAO extends IGenericDao{
	
	//---------------------------产品属性--------------------------------
	
	/**
	 * 获取产品属性
	 * @param pid 产品ID
	 * @return
	 * @author  YangYang
	 * @date  2012-7-2
	 *
	 */
	public List getProductAllAttributes(Long pid);
	
	/**
	 * 保存产品属性
	 * @param product
	 * @author  YangYang
	 * @date  2012-7-3
	 *
	 */
	public void saveProductAttribute(Hotelproduct  product);
	
	//------------------------------留言-----------------------------------
	
	/**
	 * 获取已/未审合留言列表
	 * @param status   状态
	 * @return
	 * @author  YangYang
	 * @date  2012-7-4
	 *
	 */
	public PaginationSupport getIsCheckedNetmessList(String status,int pageSize,int page,String url);
	
	/**
	 * 保存网友留言
	 * @param mess
	 * @author  YangYang
	 * @date  2012-7-6
	 *
	 */
	public void saveNetMessage(Netusermessage mess);
	
	/**
	 * 删除网友留言
	 * @param mess
	 * @author  YangYang
	 * @date  2012-7-6
	 *
	 */
	public void deleteNetMessage(Netusermessage mess);
	
	/**
	 * 查询即将过期的产品
	 * findExpire(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth hejiahua
	 * @return 
	 * List<Map>
	 * @exception 
	 * @date:2014-5-19 上午10:11:56
	 * @since  1.0.0
	 */
	public List<Map> findExpire(Esfemployeetab esfemployeetab);
}
