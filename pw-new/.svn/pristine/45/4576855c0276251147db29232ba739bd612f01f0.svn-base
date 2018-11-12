package com.ectrip.ec.report.system.datereport.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.datereport.dao.idao.IPrintCodeDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IPrintCodeService;

public class PrintCodeService implements IPrintCodeService{
	
	IPrintCodeDAO printCodeDao;
	
	public IPrintCodeDAO getPrintCodeDao() {
		return printCodeDao;
	}

	public void setPrintCodeDao(IPrintCodeDAO printCodeDao) {
		this.printCodeDao = printCodeDao;
	}

	/**
	 * *
	 * Describe:����Ʊ�Ų�ѯ��Ʊ�ŵ�״̬
	 * @see com.ectrip.report.system.datereport.service.iservice.IPrintCodeService#showAllListcode(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param serialcode
	 * @param scenics
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2012-8-22
	 */
	public PaginationSupport showAllListcode(String serialcode,String scenics,int page,int pageSize,String url)throws Exception{
		return printCodeDao.showAllListcode(serialcode,scenics, page, pageSize, url);
	}
	
	
	/**
	 * *
	 * Describe:����Ʊ�Ų�ѯ��Ʊ���Ƿ�������
	 * @see com.ectrip.report.system.datereport.service.iservice.IPrintCodeService#showWarehouse(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param serialcode
	 * @param scenics
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-8-22
	 */
	public PaginationSupport showWarehouse(String serialcode,String scenics,int page,int pageSize,String url){
		return printCodeDao.showWarehouse(serialcode, scenics, page, pageSize, url);
	}
	
	public List querySaleTicketbyprintno(String ticketno){
		return printCodeDao.querySaleTicketbyprintno(ticketno);
	}
	/**
	 * *
	 * Describe:��ѯ�ۼ���Ʊ��Ϣ
	 * @see com.ectrip.report.system.datereport.service.iservice.IPrintCodeService#querySaleTicketbyprintno(java.lang.String, int)
	 * @param ticketno
	 * @param type
	 * @return
	 * @author chenxinhao
	 * Date:2012-12-27
	 */
	public List querySaleTicketbyprintno(String ticketno,int type){
		return printCodeDao.querySaleTicketbyprintno(ticketno,type);
	}
	/**
	 * *
	 * Describe:����Ʊ�Ų�ѯ��Ʊ���Ƿ�������
	 * @see com.ectrip.report.system.datereport.service.iservice.IPrintCodeService#showWarehouse(java.lang.String, java.lang.String)
	 * @param serialcode
	 * @param scenics
	 * @return
	 * @author chenxinhao
	 * Date:2012-12-27
	 */
	public List showWarehouse(String serialcode,String scenics){
		return printCodeDao.showWarehouse(serialcode, scenics);
	}
}

