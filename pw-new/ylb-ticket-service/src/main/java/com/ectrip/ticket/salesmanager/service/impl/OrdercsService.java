package com.ectrip.ticket.salesmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Ordercs;
import com.ectrip.ticket.salesmanager.dao.IOrdercsDAO;
import com.ectrip.ticket.salesmanager.service.IOrdercsService;

@Service
public class OrdercsService implements IOrdercsService {
	
	@Autowired
	private IOrdercsDAO ordercsDAO;
	
	
	public List  queryordercsList(Long ibusinessid){
		return ordercsDAO.queryordercsList(ibusinessid);
	}
	public Ordercs  queryone(Long seq){
		return ordercsDAO.queryone(seq);
	}
	public List  queryordercs(Long ibusinessid,String ecs){
		return ordercsDAO.queryordercs(ibusinessid, ecs);
	}
	public List  queryeditordercs(Long ibusinessid,String ecs,Long seq){
		return ordercsDAO.queryeditordercs(ibusinessid, ecs,seq);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void insertordercs(Ordercs ordercs,Syslog syslog){
		Long maxseq = ordercsDAO.getMaxPk("seq", "Ordercs");
		ordercs.setSeq(maxseq + 1);
		ordercsDAO.save(ordercs);
//		syslog.setStlg("0272");
//		syslog.setBrief("订单信息管理：" +ordercs.getSeq()  );
//		syslog.setNote("订单信息管理增加："+ordercs.getZcs()+":"+ordercs.getEcs() );
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = ordercsDAO.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		ordercsDAO.save(syslog);

	}
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void updateordercs(Ordercs ordercs,Syslog syslog){
		ordercsDAO.update(ordercs);
//		syslog.setStlg("0272");
//		syslog.setBrief("订单信息管理修改 seq：" +ordercs.getSeq()  );
//		syslog.setNote("订单信息管理修改："+ordercs.getZcs()+":"+ordercs.getEcs() );
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = ordercsDAO.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		ordercsDAO.save(syslog);

	}
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void deleteordercs(Ordercs ordercs,Syslog syslog){	
		ordercsDAO.delete(ordercs);
//		syslog.setStlg("0272");
//		syslog.setBrief("订单信息管理删除 seq：" +ordercs.getSeq()  );
//		syslog.setNote("订单信息管理删除："+ordercs.getZcs()+":"+ordercs.getEcs() );
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = ordercsDAO.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		ordercsDAO.save(syslog);

	}


	public PaginationSupport getordercsviewlist(int page,int pageSize,String url){
		return ordercsDAO.getordercsviewlist( page, pageSize, url);

	}
	public PaginationSupport getordercsList(Long ibusinessid,int page,int pageSize,String url){
		return ordercsDAO.getordercsList(ibusinessid, page, pageSize, url);
	}

}
