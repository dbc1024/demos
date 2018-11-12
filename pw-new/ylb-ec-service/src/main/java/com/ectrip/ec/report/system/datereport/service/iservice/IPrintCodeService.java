package com.ectrip.ec.report.system.datereport.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;

public interface IPrintCodeService {
	
	/**
	 * 
	 * Describe:����Ʊ�Ų�ѯ��Ʊ�ŵ�״̬
	 * @auth:lijingrui
	 * @param serialcode
	 * @param scenics
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @throws Exception
	 * return:PaginationSupport
	 * Date:2012-8-22
	 */
	public PaginationSupport showAllListcode(String serialcode,String scenics,int page,int pageSize,String url)throws Exception;
	
	/**
	 * 
	 * Describe:����Ʊ�Ų�ѯ��Ʊ���Ƿ�������
	 * @auth:lijingrui
	 * @param serialcode
	 * @param scenics
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-8-22
	 */
	public PaginationSupport showWarehouse(String serialcode,String scenics,int page,int pageSize,String url);
	
	public List querySaleTicketbyprintno(String ticketno);
	/**
	 * 
	 * Describe:��ѯ�ۼ���Ʊ��Ϣ
	 * @author:chenxinhao
	 * @param ticketno
	 * @param type
	 * @return
	 * return:List
	 * Date:2012-12-27
	 */
	public List querySaleTicketbyprintno(String ticketno,int type);
	/**
	 * 
	 * Describe:����Ʊ�Ų�ѯ��Ʊ���Ƿ�������
	 * @author:chenxinhao
	 * @param serialcode
	 * @param scenics
	 * @return
	 * return:List
	 * Date:2012-12-27
	 */
	public List showWarehouse(String serialcode,String scenics);
}

