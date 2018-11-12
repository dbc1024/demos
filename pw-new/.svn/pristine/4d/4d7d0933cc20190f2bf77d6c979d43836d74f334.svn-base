package com.ectrip.ec.order.common.business.impl;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;

public class OrderBusiness implements IOrderBusiness {
	private ITicketDAO ticketDao;

	public boolean saveOrder(OrderCombin combin) {
		
		if ( ticketDao==null)
		{
			ticketDao  = (ITicketDAO) SpringUtil.getBean("ticketDao");
		 }
		ticketDao.save(combin.getMorder());
		for (TOrder t : combin.getTorders()) {
			
			ticketDao.save(t);
			ticketDao.save(t.getYorder());
			for (TOrderlist tl : t.getTorderlists()) {
				ticketDao.save(tl);
				ticketDao.save(tl.getYorderlist());
				for (TZorderlist tz : tl.getZorderlist()) {
					ticketDao.save(tz);
					ticketDao.save(tz.getYzorderlist());
				}
			}
		}
		return true;
	}

	public ITicketDAO getTicketDao() {
		return ticketDao;
	}

	public void setTicketDao(ITicketDAO ticketDao) {
		this.ticketDao = ticketDao;
	}

}
