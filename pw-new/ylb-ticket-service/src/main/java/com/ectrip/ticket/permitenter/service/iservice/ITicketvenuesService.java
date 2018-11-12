package com.ectrip.ticket.permitenter.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;

public interface ITicketvenuesService {
	
	/**
	 * 
	 * Describe:��ѯ��ͷƱ������
	 * @author:lijingrui
	 * @param scenics
	 * @param pmcd
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2014-7-24
	 */
	public PaginationSupport searchListTicket(String scenics,String pmcd,int pageSize,int startIndex, String url); 
	
	/**
	 * 
	 * Describe:����
	 * @author:lijingrui
	 * @param ticketList
	 * @param syslog
	 * return:void
	 * Date:2014-7-24
	 */
	public void insertTicketvenues(List ticketList,Syslog syslog);
	
	/**
	 * 
	 * Describe:�޸�
	 * @author:lijingrui
	 * @param ticketList
	 * @param syslog
	 * return:void
	 * Date:2014-7-24
	 */
	public void updateTicketvenues(List ticketList,Syslog syslog);
	
	/**
	 * 
	 * Describe:ɾ��
	 * @author:lijingrui
	 * @param ticketList
	 * @param syslog
	 * return:void
	 * Date:2014-7-24
	 */
	public void delTicketvenues(Long seq,Syslog syslog);
	
	/**
	 * 
	 * Describe:��ѯ���Ѵ��ڵ���Ϣ
	 * @author:lijingrui
	 * @param pmcd
	 * @return
	 * return:List
	 * Date:2014-7-24
	 */
	public List checkListTicket(String pmcd);
	
	/**
	 * 
	 * Describe:��ȡ���е�Ʊ����Ϣ
	 * @author:lijingrui
	 * @param scenicids
	 * @param scentype  ���������
	 * @return
	 * return:List
	 * Date:2014-7-24
	 */
	public List checkListedmTicket(String scenicids,String scentype);
	
}

