package com.ectrip.ec.report.system.ticketsale.dao.idao;

import com.ectrip.base.util.PaginationSupport;

public interface ITicketSaleTodayDao {
	
	public PaginationSupport querySaleTicketList(String rzti, String ldti,
			String type, Long iemployeeid, int parseInt, int pageSize,
			String string,String manyouno);
}

