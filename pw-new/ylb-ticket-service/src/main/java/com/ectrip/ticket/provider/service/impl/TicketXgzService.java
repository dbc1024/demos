package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.ticket.provider.dao.ITicketXgzDAO;
import com.ectrip.ticket.provider.service.ITicketXgzService;

@Service
public class TicketXgzService extends GenericService implements ITicketXgzService{

	private ITicketXgzDAO ticketxgzDao;
	@Autowired
	public void setTicketxgzDao(ITicketXgzDAO ticketxgzDao) {
		super.setGenericDao(ticketxgzDao); 
		this.ticketxgzDao = ticketxgzDao;
	}
	/**
	 * *
	 * Describe:显示所属服务商的所有退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#showAllticketxgz(java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public PaginationSupport showAllticketxgz(String scenics,int pageSize,int startIndex, String url){
		return ticketxgzDao.showAllticketxgz(scenics, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:添加退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#insertticketxgz(com.ectrip.model.syspar.Ticketxgz, com.ectrip.model.syspar.Syslog)
	 * @param tickxgz
	 * @param syslog
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public void insertticketxgz(Ticketxgz tickxgz,Syslog syslog){
		ticketxgzDao.insertticketxgz(tickxgz, syslog);
	}
	
	/**
	 * *
	 * Describe:修改退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#updateticketxgz(com.ectrip.model.syspar.Ticketxgz, com.ectrip.model.syspar.Syslog)
	 * @param tickxgz
	 * @param syslog
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public void updateticketxgz(Ticketxgz tickxgz,List lst,Syslog syslog){
		ticketxgzDao.updateticketxgz(tickxgz,lst, syslog);
	}
	
	/**
	 * *
	 * Describe:删除退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#deleteticketxgz(com.ectrip.model.syspar.Ticketxgz, com.ectrip.model.syspar.Syslog)
	 * @param tickxgz
	 * @param syslog
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public void deleteticketxgz(Ticketxgz tickxgz,Syslog syslog){
		ticketxgzDao.deleteticketxgz(tickxgz, syslog);
	}
	
	/**
	 * *
	 * Describe:查看退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#getviewticketxgz(java.lang.Long)
	 * @param gzid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public Ticketxgz getviewticketxgz(Long gzid) throws Exception{
		return ticketxgzDao.getviewticketxgz(gzid);
	}
	
	/**
	 * *
	 * Describe:显示出退票信息和退票费率表信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#gettickxgzchangebackview(java.lang.Long)
	 * @param gzid
	 * @return
	 * @author lijingrui
	 * Date:Nov 23, 2011
	 */
	public Ticketxgz gettickxgzchangebackview(Long gzid){
		return ticketxgzDao.gettickxgzchangebackview(gzid);
	}
	
	/**
	 * *
	 * Describe:显示服务商信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#findListesbticket(java.lang.String)
	 * @param scenics
	 * @return
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public List findListesbticket(String scenics){
		return ticketxgzDao.findListesbticket(scenics);
	}
	
	/**
	 * *
	 * Describe:显示所属的票类型
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#getedmtickettypelist(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public String getedmtickettypelist(Long iscenicid){
		return ticketxgzDao.getedmtickettypelist(iscenicid);
	}
	
	/**
	 * *
	 * Describe:显示所属的票类型(包含套票)
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#getedmtickettypelist2(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author chenxinhao
	 * Date:2013-1-23
	 */
	public String getedmtickettypelist2(Long iscenicid){
		return ticketxgzDao.getedmtickettypelist2(iscenicid);
	}
}

