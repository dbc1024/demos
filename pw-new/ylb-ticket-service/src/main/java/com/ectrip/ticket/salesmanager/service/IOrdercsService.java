package com.ectrip.ticket.salesmanager.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Ordercs;

public interface IOrdercsService {
	public List  queryordercsList(Long ibusinessid);
	public Ordercs  queryone(Long seq);
	public List  queryordercs(Long ibusinessid,String ecs);
	public List  queryeditordercs(Long ibusinessid,String ecs,Long seq);
	public void insertordercs(Ordercs ordercs,Syslog syslog);
	public void updateordercs(Ordercs ordercs,Syslog syslog);
	public void deleteordercs(Ordercs ordercs,Syslog syslog);
	public PaginationSupport getordercsviewlist(int page,int pageSize,String url);
	public PaginationSupport getordercsList(Long ibusinessid,int page,int pageSize,String url);
}
