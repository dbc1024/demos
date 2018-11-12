package com.ectrip.ticket.permitenter.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.permitenter.dao.ITicketvenuesDAO;
import com.ectrip.ticket.permitenter.service.iservice.ITicketvenuesService;

public class TicketvenuesService implements ITicketvenuesService{

	private ITicketvenuesDAO ticketVenueDAO;

	public ITicketvenuesDAO getTicketVenueDAO() {
		return ticketVenueDAO;
	}

	public void setTicketVenueDAO(ITicketvenuesDAO ticketVenueDAO) {
		this.ticketVenueDAO = ticketVenueDAO;
	}
	
	/**
	 * *
	 * Describe:��ѯ��ͷƱ������
	 * @see com.ectrip.system.permitenter.service.iservice.ITicketvenuesService#searchListTicket(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pmcd
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2014-7-24
	 */
	public PaginationSupport searchListTicket(String scenics,String pmcd,int pageSize,int startIndex, String url){
		return ticketVenueDAO.searchListTicket(scenics, pmcd, pageSize, startIndex, url);
	} 
	
	/**
	 * *
	 * Describe:����
	 * @see com.ectrip.system.permitenter.service.iservice.ITicketvenuesService#insertTicketvenues(java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param ticketList
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-7-25
	 */
	public void insertTicketvenues(List ticketList,Syslog syslog){
		ticketVenueDAO.insertTicketvenues(ticketList, syslog);
	}
	
	/**
	 * *
	 * Describe:�޸�
	 * @see com.ectrip.system.permitenter.service.iservice.ITicketvenuesService#updateTicketvenues(java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param ticketList
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-7-25
	 */
	public void updateTicketvenues(List ticketList,Syslog syslog){
		ticketVenueDAO.updateTicketvenues(ticketList, syslog);
	}
	
	/**
	 * *
	 * Describe:ɾ��
	 * @see com.ectrip.system.permitenter.service.iservice.ITicketvenuesService#delTicketvenues(java.lang.Long, com.ectrip.model.syspar.Syslog)
	 * @param seq
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-7-25
	 */
	public void delTicketvenues(Long seq,Syslog syslog){
		ticketVenueDAO.delTicketvenues(seq, syslog);
	}
	
	/**
	 * *
	 * Describe:��ѯ���Ѵ��ڵ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.ITicketvenuesService#checkListTicket(java.lang.String)
	 * @param pmcd
	 * @return
	 * @author lijingrui
	 * Date:2014-7-24
	 */
	public List checkListTicket(String pmcd){
		return ticketVenueDAO.checkListTicket(pmcd);
	}
	
	/**
	 * *
	 * Describe:��ȡ���е�Ʊ����Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.ITicketvenuesService#checkListedmTicket(java.lang.String, java.lang.String)
	 * @param scenicids
	 * @param scentype
	 * @return
	 * @author lijingrui
	 * Date:2014-7-24
	 */
	public List checkListedmTicket(String scenicids,String scentype){
		return ticketVenueDAO.checkListedmTicket(scenicids, scentype);
	}
	
}

