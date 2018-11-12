package com.ectrip.sys.other.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.sys.model.employee.Esfemployeetab;

public interface IOrderEditDAO  extends IGenericDao{
	
	
	// ����
	public List findOneList(String pmky, String spmcd);
	
	public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab,QueryOrder order,int page,int pageSize,String url);
	
}
