package com.ectrip.sys.other.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.sys.model.employee.Esfemployeetab;

public interface IOrderEditService {
	public List findOneList(String pmky, String spmcd) ;
	
	public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url);
	
	
	public int updateOrderStatus(String orid,Long empid,String zffs,String bank,String zfusid) throws Exception;
}
