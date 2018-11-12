
package com.ectrip.ticket.stocks.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.stock.Stocktab;

public interface IStocksUserService {
	public List selectUser();

	public List selectUserStockForF();
	
	

	public PaginationSupport initInfo(int pageSize, int startInt, String url);
	
	public void addUserStock(Syslog syslog, Stocktab stocktab);
	
	public Stocktab selectUserBySeq(Long seq);
	
	public PaginationSupport selectUserStock(Stocktab stocktab, int pageSize,
                                             int page, String url);

	public List searchUserStock(Stocktab stocktab);

	
}
