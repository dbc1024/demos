package com.ectrip.ticket.salesmanager.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Ordercs;

public interface IOrdercsDAO  extends IGenericDao{
	public List  queryordercsList(Long ibusinessid);
	public Ordercs  queryone(Long seq);
	public List  queryordercs(Long ibusinessid,String ecs);
	public List  queryeditordercs(Long ibusinessid,String ecs,Long seq);
	public PaginationSupport getordercsviewlist(int page,int pageSize,String url);
	public PaginationSupport getordercsList(Long ibusinessid,int page,int pageSize,String url);
	
}
