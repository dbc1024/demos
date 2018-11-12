package com.ectrip.ec.report.system.ticketsale.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ITicketSaleTodayDao;
import com.ectrip.ec.report.system.ticketsale.service.iservice.ITicketSaleTodayService;

public class TicketSaleTodayService implements ITicketSaleTodayService{
	private ITicketSaleTodayDao ticketSaleTodayDao;

	public ITicketSaleTodayDao getTicketSaleTodayDao() {
		return ticketSaleTodayDao;
	}

	public void setTicketSaleTodayDao(ITicketSaleTodayDao ticketSaleTodayDao) {
		this.ticketSaleTodayDao = ticketSaleTodayDao;
	}



	/**
	 * Describe:��Ʊ������ʷ��ˮ��ѯ
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleTodayService#querySaleTicketList(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, int, int, java.lang.String)
	 * @param rzti
	 * @param ldti
	 * @param type
	 * @param iemployeeid
	 * @param parseInt
	 * @param pageSize
	 * @param string
	 * @return
	 * @author aozhuozu
	 * Date:2012-8-29
	 */
	public PaginationSupport querySaleTicketList(String rzti, String ldti,
			String type, Long iemployeeid, int parseInt, int pageSize,
			String string,String manyouno){
		return ticketSaleTodayDao.querySaleTicketList(rzti, ldti, type, iemployeeid, parseInt, pageSize, string,manyouno);
	}
}

