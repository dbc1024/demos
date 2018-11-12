package com.ectrip.ticket.provider.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.model.provider.Hotelproduct;
import com.ectrip.ticket.model.provider.Netusermessage;
import com.ectrip.ticket.provider.dao.IProductAttributeDAO;
import com.ectrip.ticket.provider.service.IProductAttributeService;

@Service
public class ProducteAttributeService implements IProductAttributeService{
	
	private IProductAttributeDAO productAttributeDao;
	
	
	
	public IProductAttributeDAO getProductAttributeDao() {
		return productAttributeDao;
	}
	public void setProductAttributeDao(IProductAttributeDAO productAttributeDao) {
		this.productAttributeDao = productAttributeDao;
	}

	/**
	 * 获取产品属性列表
	 * @param pid 产品ID
	 * @return
	 * @author  YangYang
	 * @date  2012-7-2
	 *
	 */
	public List getProductAllAttributes(Long pid) {
		// TODO Auto-generated method stub
		return productAttributeDao.getProductAllAttributes(pid);
	}
	
	/**
	 * 保存产品属性
	 * @param product
	 * @author  YangYang
	 * @date  2012-7-3
	 *
	 */
	public void saveProductAttribute(Hotelproduct product) {
		// TODO Auto-generated method stub
		productAttributeDao.saveProductAttribute(product);
	}
	
	/**
	 * 获取已/未审合留言列表
	 * @param status 状态
	 * @return
	 * @author  YangYang
	 * @date  2012-7-4
	 *
	 */
	public PaginationSupport getIsCheckedNetmessList(String status,
			int pageSize, int page, String url) {
		// TODO Auto-generated method stub
		return productAttributeDao.getIsCheckedNetmessList(status, pageSize, page, url);
	}
	
	/**
	 * 保存网友留言
	 * @param mess
	 * @author  YangYang
	 * @date  2012-7-6
	 *
	 */
	public void saveNetMessage(Netusermessage mess) {
		// TODO Auto-generated method stub
		productAttributeDao.saveNetMessage(mess);
	}
	
	/**
	 * 删除网友留言
	 * @param mess
	 * @author  YangYang
	 * @date  2012-7-6
	 *
	 */
	public void deleteNetMessage(Netusermessage mess) {
		// TODO Auto-generated method stub
		productAttributeDao.deleteNetMessage(mess);
	}
	public List<Map> findExpire(Esfemployeetab esfemployeetab) {
		return productAttributeDao.findExpire(esfemployeetab);
	}

	
}
