package com.ectrip.ticket.permitenter.service.iservice;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Esbrefundrules;

public interface IEsbrefundrulesService {
	
	/**
	 * 
	 * Describe:��ȡ���з�������Ʊ����
	 * @author:lijingrui
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-3-17
	 */
	public PaginationSupport checkListRefundrule(String scenics,int pageSize,int startIndex, String url);

	/**
	 * 
	 * Describe:���� ��������Ʊ����
	 * @author:lijingrui
	 * @param refundrule
	 * @param syslog
	 * return:void
	 * Date:2015-3-17
	 */
	public void insertRefundrule(Esbrefundrules refundrule,Syslog syslog);
	
	/**
	 * 
	 * Describe:�޸� ��������Ʊ����
	 * @author:lijingrui
	 * @param refundrule
	 * @param syslog
	 * return:void
	 * Date:2015-3-17
	 */
	public void updateRefundrule(Esbrefundrules refundrule,Syslog syslog);
	
	/**
	 * 
	 * Describe:ɾ�� ��������Ʊ����
	 * @author:lijingrui
	 * @param refundrule
	 * @param syslog
	 * return:void
	 * Date:2015-3-17
	 */
	public void delRefundrule(Esbrefundrules refundrule,Syslog syslog);
	
	/**
	 * 
	 * Describe:�鿴 ��������Ʊ����
	 * @author:lijingrui
	 * @param seq
	 * @return
	 * return:Esbrefundrules
	 * Date:2015-3-17
	 */
	public Esbrefundrules viewRefundrule(Long seq)throws Exception;
	
}

